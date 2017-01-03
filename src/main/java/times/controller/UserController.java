package times.controller;

import org.springframework.web.bind.annotation.*;
import times.ViewModel.FutureDescriptionViewModel;
import times.ViewModel.LoginViewMoldel;
import times.ViewModel.ProfileViewModel;
import times.model.User;
import times.repository.UserRepository;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by ocean on 16-12-31.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    UserRepository userRepository = new UserRepository();

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
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

    @CrossOrigin(origins = "*")
    @RequestMapping("/add")
    public boolean add(@RequestParam(name = "identify") String identify,
                       @RequestParam(name = "password") String password,
                       @RequestParam(name = "sex") Boolean sex,
                       @RequestParam(name = "name") String name,
                       @RequestParam(name = "tel") String tel) throws SQLException {
        User user = new User();
        user.setName(name);
        String str = "男";
        if (sex) {
            str = "女";
        }
        user.setGender(str);
        user.setIdentify(identify);
        user.setPassword(password);
        user.setTelephone(tel);
        try {
            System.out.println(userRepository.save(user));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public ProfileViewModel profile(@RequestParam(name = "id") String user_id) throws SQLException {
        Long userId = Long.parseLong(user_id);
        User user = userRepository.findById(userId);

        ProfileViewModel profileViewModel = userRepository.contractInfor(userId);
        profileViewModel.setName(user.getName());
        profileViewModel.setLeft_money(user.getFund());
        return profileViewModel;
    }

}
