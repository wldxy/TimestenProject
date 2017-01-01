package times.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import times.model.User;
import times.repository.UserRepository;

import java.sql.SQLException;

/**
 * Created by ocean on 16-12-31.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    UserRepository userRepository = new UserRepository();

    @RequestMapping("/login")
    public Long login(@RequestParam(name = "identity") String identity,
                      @RequestParam(name = "password") String password) throws SQLException {
        User user = userRepository.findByIdentify(identity);
        if (user.password == password) {
            return user.id;
        } else {
            return (long) -1;
        }
    }

    @RequestMapping("/add")
    public void add(@RequestParam(name = "identify") String identify,
                    @RequestParam(name = "password") String password,
                    @RequestParam(name = "sex") String sex,
                    @RequestParam(name = "name") String name) throws SQLException {
        User user = new User();
        user.setName(name);
        user.setGender(sex);
        user.setIdentify(identify);
        user.setPassword(password);
        user.setTelephone("");

        userRepository.save(user);
    }
}
