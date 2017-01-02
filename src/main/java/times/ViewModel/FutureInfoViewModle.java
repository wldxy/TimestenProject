package times.ViewModel;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by 13987 on 2017/1/2.
 */
public class FutureInfoViewModle {
    private Long id;
    private String name;
    private ArrayList<BigDecimal> today_rate;

    public FutureInfoViewModle() {
    }

    public FutureInfoViewModle(Long id, String name, ArrayList<BigDecimal> today_rate) {
        this.id = id;
        this.name = name;
        this.today_rate = today_rate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<BigDecimal> getToday_rate() {
        return today_rate;
    }

    public void setToday_rate(ArrayList<BigDecimal> today_rate) {
        this.today_rate = today_rate;
    }
}
