package times.controller;

import org.springframework.web.bind.annotation.*;
import times.repository.UserRepository;

import java.sql.SQLException;

/**
 * Created by ocean on 17-1-2.
 */
@RestController
public class AcountController {

    UserRepository userRepository = new UserRepository();
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/up", method = RequestMethod.POST)
    public Boolean addMoney(@RequestParam(name = "id") Long id,
                            @RequestParam(name = "number") Float number) throws SQLException {
        return userRepository.addMoney(id, number);
    }
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/out", method = RequestMethod.POST)
    public Boolean decMoney(@RequestParam(name = "id") Long id,
                            @RequestParam(name = "number") Float number) throws SQLException {
        return userRepository.desMoney(id, number);
    }


}
