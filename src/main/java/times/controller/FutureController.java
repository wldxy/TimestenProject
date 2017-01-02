package times.controller;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import com.sun.xml.internal.messaging.saaj.util.FinalArrayList;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import times.ViewModel.*;
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

    @RequestMapping(value = "/fresh",method = RequestMethod.GET)
    public FreshViewModel fresh(@RequestParam(name = "id") String future_id) throws SQLException {
        Long fuuteId = Long.valueOf(future_id);
        return futureRepository.fresh(fuuteId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public FutureInfoViewModle profile(@RequestParam(name = "id") String future_id) throws SQLException {
        Long fuuteId = Long.valueOf(future_id);
        return futureRepository.getRate(fuuteId);
    }

    @RequestMapping(value = "/futureList", method = RequestMethod.GET)
    public ArrayList<FutureViewModle> futureList() throws SQLException {
        return futureRepository.futureList();
    }

    @RequestMapping(value = "/yesterday", method = RequestMethod.GET)
    public YesterViewModel yesterday(@RequestParam(name = "f_id") String future_id,
                                                @RequestParam(name = "u_id") String user_id) throws SQLException {
       return futureRepository.yesterday(Long.valueOf(future_id),Long.valueOf(user_id));
    }

}
