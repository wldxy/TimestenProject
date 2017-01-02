package times.controller;

import org.springframework.web.bind.annotation.*;
import times.ViewModel.*;
import times.repository.FutureRepository;
import times.repository.UserRepository;

import java.sql.SQLException;

/**
 * Created by 13987 on 2017/1/2.
 */
@RestController
public class BuyController {
    private FutureRepository futureRepository = new FutureRepository();
    private UserRepository userRepository = new UserRepository();

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public int profile(@RequestBody BuyViewModel buyViewModel) throws SQLException {
        Long futureId = buyViewModel.getF_Id();
        Long userId = buyViewModel.getU_Id();
        Integer number = buyViewModel.getNumber();
        Float price = buyViewModel.getPrice();
        String pwd = buyViewModel.getPwd();
        int result = futureRepository.buy(futureId, userId, number, pwd, price);
        return result;
    }

    @RequestMapping(value = "/sell", method = RequestMethod.POST)
    public int sellFuture(@RequestBody BuyViewModel buyViewModel) throws SQLException {
        Long futureId = buyViewModel.getF_Id();
        Long userId = buyViewModel.getU_Id();
        Integer number = buyViewModel.getNumber();
        Float price = buyViewModel.getPrice();
        String pwd = buyViewModel.getPwd();
        int result = futureRepository.sell(futureId, userId, number, pwd, price);
        return result;
    }
}
