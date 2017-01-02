package times.repository;

import com.sun.org.apache.xpath.internal.operations.Bool;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import oracle.sql.NUMBER;
import times.ViewModel.FutureDescriptionViewModel;
import times.ViewModel.FutureInfoViewModle;
import times.ViewModel.PackageViewModel;
import times.ViewModel.RateViewModle;
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
    private static final String buyFuture = "{call BUY(?, ?, ?, ?, ?)}";
    private static final String todayPrice = "{call SELECT_TODAY_PRICE_BY_ID(?, ?, ?, ?)}";

    private static final String sellFuture = "{call SELL(?, ?, ?, ?, ?)}";

    private UserRepository userRepository = new UserRepository();

    public int buy(Long futureId, Long userId, Integer number, String pwd, Float price) throws SQLException {
        Connection connection = DatabaseConnection.getOracleConnection();

        User user = userRepository.findById(userId);
        if (user == null) {
            return 4;
        }
        if (!user.getPassword().equals(pwd)) {
            return 4;
        }

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

    public int sell(Long futureId, Long userId, Integer number, String pwd, Float price) throws SQLException {
        Connection connection = DatabaseConnection.getOracleConnection();

        User user = userRepository.findById(userId);
        if (user == null) {
            return 4;
        }
        if (!user.getPassword().equals(pwd)) {
            return 4;
        }

        OracleCallableStatement callableStatement = (OracleCallableStatement) connection.prepareCall(sellFuture);
        callableStatement.setLong(1, userId);
        callableStatement.setLong(2, futureId);
        callableStatement.setInt(3, number);
        callableStatement.setFloat(4, price);
        callableStatement.registerOutParameter(5, OracleTypes.NUMBER);

        callableStatement.executeUpdate();
        int result = callableStatement.getInt(5);
        connection.close();
        return result;
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
