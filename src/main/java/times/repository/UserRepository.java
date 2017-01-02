package times.repository;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import oracle.sql.NUMBER;
import times.ViewModel.BounsAmountView;
import times.ViewModel.FutureDescriptionViewModel;
import times.ViewModel.ProfileViewModel;
import times.model.User;
import times.util.DatabaseConnection;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ocean on 16-12-31.
 */
public class UserRepository {


    private static final String SELECT_USER_CONTRACT_INFOR = "{call SELECT_USER_CONTRACT_INFOR(?, ?, ?, ?)}";

    private static final String createUser = "{call CREATE_USER(?, ?, ?, ?, ?, ?)}";

    private static final String selectUserByIdentify = "{call select_User_By_Identify(?, ?)}";

    private static final String selectUserById = "{call select_User_By_Id(?, ?)}";

    private static final String selectUserFutures = "{call select_future_by_user_id(?, ?)}";

    private static final String addUserAcount = "{call RECHARGE(?, ?, ?)}";

    private static final String decUserAcount = "{call TRANSFER(?, ?, ?)}";

    public void save(User user) throws SQLException {
        Connection connection = DatabaseConnection.getOracleConnection();
        OracleCallableStatement callableStatement = (OracleCallableStatement) connection.prepareCall(createUser);
        callableStatement.setString(1, user.name);
        callableStatement.setString(2, user.identify);
        callableStatement.setString(3, user.getGender());
        callableStatement.setString(4, user.getTelephone());
        callableStatement.setString(5, user.getTelephone());
        callableStatement.registerOutParameter(6, OracleTypes.NUMBER);
        callableStatement.executeUpdate();
        int result = callableStatement.getInt(6);
        connection.close();
    }

//    public ProfileViewModel contractInfor(Long user_id) throws SQLException {
//        ProfileViewModel profileViewModel = new ProfileViewModel();
//        Connection connection = DatabaseConnection.getOracleConnection();
//        OracleCallableStatement callableStatement = (OracleCallableStatement) connection.prepareCall(TEST);
//        callableStatement.registerOutParameter(1, OracleTypes.NUMBER);
//        callableStatement.executeUpdate();
//        return profileViewModel;
//    }
    public ProfileViewModel contractInfor(Long user_id) throws SQLException {
        ProfileViewModel profileViewModel = new ProfileViewModel();
        Connection connection = DatabaseConnection.getOracleConnection();
        OracleCallableStatement callableStatement = (OracleCallableStatement) connection.prepareCall(SELECT_USER_CONTRACT_INFOR);
        callableStatement.setLong(1, user_id);
        callableStatement.registerOutParameter(2, OracleTypes.NUMBER);
        callableStatement.registerOutParameter(3, OracleTypes.NUMBER);
        callableStatement.registerOutParameter(4, OracleTypes.CURSOR);

        callableStatement.executeUpdate();
        float bonus = callableStatement.getFloat(2);
        float anount = callableStatement.getFloat(3);
        ResultSet rs = callableStatement.getCursor(4);

        ArrayList<FutureDescriptionViewModel> futureDescriptionViewModels = new ArrayList<FutureDescriptionViewModel>();
        while (rs.next()) {
            FutureDescriptionViewModel futureDescriptionViewModel = new FutureDescriptionViewModel();
            Long futureId = rs.getLong(1);
            String futureName = rs.getString(2);
            int amount = rs.getInt(3);
            futureDescriptionViewModel.setFuture_name(futureName);
            futureDescriptionViewModel.setFuture_id(futureId);
            futureDescriptionViewModel.setHave(amount);
            futureDescriptionViewModels.add(futureDescriptionViewModel);
        }
        connection.close();
        profileViewModel.setBonus(bonus);
        BigDecimal bigDecimal = new BigDecimal(anount);
        profileViewModel.setAmount(bigDecimal);
        profileViewModel.setMy_future(futureDescriptionViewModels);
        return profileViewModel;
    }


    public User findByIdentify(String identify) throws SQLException {
        Connection connection = DatabaseConnection.getOracleConnection();

        OracleCallableStatement callableStatement = (OracleCallableStatement) connection.prepareCall(selectUserByIdentify);
        callableStatement.setString(1, identify);
        callableStatement.registerOutParameter(2, OracleTypes.CURSOR);

        callableStatement.executeUpdate();

        ResultSet rs = callableStatement.getCursor(2);
        rs.next();

        User user = new User();
        user.setId(rs.getLong(1));
        user.setName(rs.getString(2));
        user.setIdentify(rs.getString(3));
        user.setGender(rs.getString(4));
        user.setTelephone(rs.getString(5));
        user.setPassword(rs.getString(6));
        user.setFund(rs.getBigDecimal(7));
        connection.close();
        return user;
    }

    public User findById(Long id) throws SQLException {
        Connection connection = DatabaseConnection.getOracleConnection();

        OracleCallableStatement callableStatement = (OracleCallableStatement) connection.prepareCall(selectUserById);

        callableStatement.setLong(1, id);
        callableStatement.registerOutParameter(2, OracleTypes.CURSOR);

        callableStatement.executeUpdate();

        ResultSet rs = callableStatement.getCursor(2);
        rs.next();

        User user = new User();
        user.setId(rs.getLong(1));
        user.setName(rs.getString(2));
        user.setIdentify(rs.getString(3));
        user.setGender(rs.getString(4));
        user.setTelephone(rs.getString(5));
        user.setPassword(rs.getString(6));
        user.setFund(rs.getBigDecimal(7));
        connection.close();
        return user;
    }

    public ArrayList<FutureDescriptionViewModel> findUserFuture(Long userId) throws SQLException {
        Connection connection = DatabaseConnection.getOracleConnection();
        OracleCallableStatement callableStatement = (OracleCallableStatement) connection.prepareCall(selectUserFutures);
        callableStatement.setLong(1, userId);
        callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
        callableStatement.executeUpdate();
        ResultSet rs = callableStatement.getCursor(2);
        ArrayList<FutureDescriptionViewModel> userFutures = new ArrayList<FutureDescriptionViewModel>();

        FutureDescriptionViewModel futureDescriptionViewModel = new FutureDescriptionViewModel();
        while (rs.next()) {
            Long future_id = rs.getLong(1);
            String future_name = rs.getString(2);
            futureDescriptionViewModel.setFuture_id(future_id);
            futureDescriptionViewModel.setFuture_name(future_name);
            userFutures.add(futureDescriptionViewModel);
        }
        connection.close();
        return userFutures;
    }

    public Boolean addMoney(Long userId, Float number) throws SQLException {
        Connection connection = DatabaseConnection.getOracleConnection();

        OracleCallableStatement callableStatement = (OracleCallableStatement) connection.prepareCall(addUserAcount);
        callableStatement.setLong(1, userId);
        callableStatement.setFloat(2, number);
        callableStatement.registerOutParameter(3, OracleTypes.INTEGER);

        callableStatement.executeUpdate();
        Integer flag = callableStatement.getInt(3);
        connection.close();
        if (flag > 0)
            return true;
        else
            return false;

//        return (Boolean) flag;
    }

    public Boolean desMoney(Long userId, Float number) throws SQLException {
        Connection connection = DatabaseConnection.getOracleConnection();

        OracleCallableStatement callableStatement = (OracleCallableStatement) connection.prepareCall(decUserAcount);
        callableStatement.setLong(1, userId);
        callableStatement.setFloat(2, number);
        callableStatement.registerOutParameter(3, OracleTypes.INTEGER);

        callableStatement.executeUpdate();
        Integer flag = callableStatement.getInt(3);
        connection.close();
        if (flag > 0)
            return true;
        else
            return false;
//        return flag;
    }
}
