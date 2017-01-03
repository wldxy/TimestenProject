package times.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import times.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by ocean on 16-12-31.
 */
@RestController
public class TestController {
    @CrossOrigin(origins = "*")
    @RequestMapping("/test")
    public void test() throws SQLException {
        Connection connection = DatabaseConnection.getOracleConnection();
        connection.close();
        System.out.print("HAHAH");
    }
}
