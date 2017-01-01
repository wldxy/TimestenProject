package times.repository;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import oracle.sql.NUMBER;
import times.model.User;
import times.util.DatabaseConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ocean on 16-12-31.
 */
public class UserRepository {

    private static final String createUser = "{call CREATE_USER(?, ?, ?, ?, ?, ?)}";

    private static final String selectUserByIdentify = "{call select_User_By_Identify(?, ?)}";

    private static final String selectUserById = "{call selectUserById(?, ?)}";

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
        System.out.println(result);
        connection.close();
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

        CallableStatement callableStatement = connection.prepareCall(selectUserById);

        callableStatement.setLong(1, id);

        callableStatement.executeUpdate();

        User user = new User();

        connection.close();

        return user;
    }
}
