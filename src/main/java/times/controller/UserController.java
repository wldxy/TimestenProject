package times.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import times.ViewModel.LoginViewMoldel;
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

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public LoginViewMoldel login(@RequestParam(name = "id") String identity,
                      @RequestParam(name = "pwd") String password) throws SQLException {
        User user = userRepository.findByIdentify(identity);
        String userPassWord = user.password.toString().trim();
        LoginViewMoldel loginViewMoldel = new LoginViewMoldel();
        if (userPassWord.equals(password)) {
            loginViewMoldel.setFlag("true");
            loginViewMoldel.setId(user.getId().toString());
        } else {
            loginViewMoldel.setFlag("false");
        }
        return loginViewMoldel;
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
