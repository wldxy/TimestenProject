package times.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import times.ViewModel.FutureDescriptionViewModel;
import times.ViewModel.FutureInfoViewModle;
import times.ViewModel.LoginViewMoldel;
import times.ViewModel.ProfileViewModel;
import times.model.User;
import times.repository.FutureRepository;
import times.repository.UserRepository;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * Created by 13987 on 2017/1/2.
 */
@RestController
@RequestMapping("/future")
public class FutureController {
    private FutureRepository futureRepository = new FutureRepository();


//    @RequestMapping( method = RequestMethod.GET)
//    public FutureInfoViewModle profile(@RequestParam(name = "id") String future_id) throws SQLException {
//        Long futureId = Long.parseLong(future_id);
//        FutureInfoViewModle futureInfoViewModle = new FutureInfoViewModle();
//        futureInfoViewModle.setName("aaa");
//        futureInfoViewModle.setId((long)1);
//        for(int i =0;i!=3;i++){
//
//        }
//        return futureInfoViewModle;
//    }



}
