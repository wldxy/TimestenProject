package times.repository;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import oracle.sql.NUMBER;
import times.ViewModel.FutureDescriptionViewModel;
import times.model.User;
import times.util.DatabaseConnection;

import javax.jws.soap.SOAPBinding;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by 13987 on 2017/1/2.
 */
public class FutureRepository {
    private static final String buyFuture = "{call BUY(?, ?, ?, ?, ?)}";

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
}
