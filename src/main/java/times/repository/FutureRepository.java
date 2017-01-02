package times.repository;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.sun.xml.internal.messaging.saaj.util.FinalArrayList;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import oracle.sql.NUMBER;
import times.ViewModel.*;
import times.model.User;
import times.util.DatabaseConnection;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by 13987 on 2017/1/2.
 */
public class FutureRepository {
    private static final String SELECT_FUTURE = "{call SELECT_FUTURE(?)}";
    private static final String SELECT_RECENT_FUTURE_PRICE = "{call SELECT_RECENT_FUTURE_PRICE(?, ?, ?, ?, ?)}";
    private static final String fresh = "{call UPDARE_STATE(?, ?,?)}";

    private static final String buyFuture = "{call BUY(?, ?, ?, ?, ?)}";
    private static final String todayPrice = "{call SELECT_TODAY_PRICE_BY_ID(?, ?, ?, ?)}";

    public int buy(Long futureId, Long userId, Integer number, String pwd, Float price) throws SQLException {
        Connection connection = DatabaseConnection.getOracleConnection();
        OracleCallableStatement callableStatement = (OracleCallableStatement) connection.prepareCall(buyFuture);
        callableStatement.setLong(1, userId);
        callableStatement.setLong(2, futureId);
        callableStatement.setInt(3, number);
        callableStatement.setFloat(4, price);
        callableStatement.registerOutParameter(5, OracleTypes.NUMBER);
        callableStatement.executeUpdate();
        int result = callableStatement.getInt(5);
        System.out.println(result);
        connection.close();
        return result;
    }

    public YesterViewModel yesterday(Long future_id, Long user_id) throws SQLException {
        YesterViewModel yesterViewModel = new YesterViewModel();
        Connection connection = DatabaseConnection.getOracleConnection();
        OracleCallableStatement callableStatement = (OracleCallableStatement) connection.prepareCall(SELECT_RECENT_FUTURE_PRICE);
        callableStatement.setLong(1, user_id);
        callableStatement.setLong(2, future_id);
        callableStatement.registerOutParameter(3, OracleTypes.VARCHAR);
        callableStatement.registerOutParameter(4, OracleTypes.NUMBER);
        callableStatement.registerOutParameter(5, OracleTypes.CURSOR);
        callableStatement.executeUpdate();
        ResultSet rs = callableStatement.getCursor(5);
        ArrayList<YesterdayListView> yesterdayListView = new ArrayList<YesterdayListView>();
        int counter = 0;
        float rate;
        while (rs.next()) {
            YesterdayListView yesterdayListView1 = new YesterdayListView();
            yesterdayListView1.setIndex(counter++);
            yesterdayListView1.setPrice(rs.getBigDecimal(2));
            yesterdayListView.add(yesterdayListView1);
        }
        float first = yesterdayListView.get(0).getPrice().floatValue();
        float last = yesterdayListView.get(yesterdayListView.size()-1).getPrice().floatValue();
        rate = (last - first) / first;
        yesterViewModel.setYesterdayListView(yesterdayListView);
        yesterViewModel.setFuture_name(callableStatement.getString(3));
        yesterViewModel.setFuture_id(future_id);
        yesterViewModel.setHave(callableStatement.getInt(4));
        yesterViewModel.setUserId(user_id);
        yesterViewModel.setRate(rate);
        return yesterViewModel;

    }

    public ArrayList<FutureViewModle> futureList() throws SQLException {
        Connection connection = DatabaseConnection.getOracleConnection();
        OracleCallableStatement callableStatement = (OracleCallableStatement) connection.prepareCall(SELECT_FUTURE);
        callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
        callableStatement.executeUpdate();
        ArrayList<FutureViewModle> futureViewModles = new FinalArrayList();
        ResultSet rs = callableStatement.getCursor(1);
        while (rs.next()) {
            FutureViewModle futureViewModle = new FutureViewModle();
            futureViewModle.setFuture_id(rs.getLong(1));
            futureViewModle.setFuture_name(rs.getString(2));
            futureViewModles.add(futureViewModle);
        }
        connection.close();
        return futureViewModles;
    }

    public FreshViewModel fresh(Long future_id) throws SQLException{
        Connection connection = DatabaseConnection.getOracleConnection();
        OracleCallableStatement callableStatement = (OracleCallableStatement) connection.prepareCall(fresh);
        callableStatement.setLong(1, future_id);
        callableStatement.registerOutParameter(2, OracleTypes.NUMBER);
        callableStatement.registerOutParameter(3, OracleTypes.NUMBER);
        callableStatement.executeUpdate();
        //0跌
        //1涨
        float pirce = callableStatement.getFloat(2);
        int state = callableStatement.getInt(3);
        FreshViewModel freshViewModel = new FreshViewModel();
        freshViewModel.setFuture_id(future_id);
        freshViewModel.setPrice(pirce);
        freshViewModel.setState(state);
        connection.close();
        return freshViewModel;
    }
    public FutureInfoViewModle getRate(Long future_id) throws SQLException {
        FutureInfoViewModle futureInfoViewModle = new FutureInfoViewModle();
        ArrayList<PackageViewModel> today_rate = new ArrayList<PackageViewModel>();
        futureInfoViewModle.setId(future_id);
        Connection connection = DatabaseConnection.getOracleConnection();
        OracleCallableStatement callableStatement = (OracleCallableStatement) connection.prepareCall(todayPrice);
        callableStatement.setLong(1, future_id);
        callableStatement.registerOutParameter(2, OracleTypes.VARCHAR);
        callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
        callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
        callableStatement.executeUpdate();

        futureInfoViewModle.setName(callableStatement.getString(2));
        ResultSet rs = callableStatement.getCursor(4);
        ArrayList<RateViewModle> rateViewModles = new ArrayList<RateViewModle>();
        Date temp = new Date();
        float min = 999999999;
        float max = -9;
        float before = 0;
        Boolean flag = false;
        float close = 0;
        Date date = new Date();
        while (rs.next()) {
            if (!flag) {
                close = rs.getFloat(2);
                flag = true;
            }

            date = rs.getDate(1);
            if (date.getDate() != temp.getDate()) {
                RateViewModle rateViewModle = new RateViewModle();
                rateViewModle.setDate(date);
                rateViewModle.setClose(close);
                rateViewModle.setHigh(max);
                rateViewModle.setLow(min);
                rateViewModle.setOpen(before);
                rateViewModles.add(rateViewModle);
                temp = date;
                close = rs.getFloat(2);
            } else {
                min = Math.min(min, rs.getFloat(2));
                max = Math.max(max, rs.getFloat(2));
                before = rs.getFloat(2);
            }
        }

        RateViewModle rateViewModle = new RateViewModle();
        rateViewModle.setDate(date);
        rateViewModle.setClose(close);
        rateViewModle.setHigh(max);
        rateViewModle.setLow(min);
        rateViewModle.setOpen(before);
        rateViewModles.add(rateViewModle);
        ResultSet rs2 = callableStatement.getCursor(3);
        int counter = 0;
        while (rs2.next()) {
            float now_Pirce = rs2.getFloat(1);
            PackageViewModel packageViewModel = new PackageViewModel();
            packageViewModel.setIndex(counter++);
            packageViewModel.setPrice(BigDecimal.valueOf(now_Pirce));
            today_rate.add(packageViewModel);
        }
        futureInfoViewModle.setRateViewModle(rateViewModles);
        futureInfoViewModle.setPackageViewModels(today_rate);
        connection.close();
        return futureInfoViewModle;
    }
}
