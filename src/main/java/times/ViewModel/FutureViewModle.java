package times.ViewModel;

import java.util.ArrayList;

/**
 * Created by 13987 on 2017/1/2.
 */
public class FutureViewModle {
    private String future_name;
    private Long future_id;
    private float rate;
    private float have;

    public String getFuture_name() {
        return future_name;
    }

    public void setFuture_name(String future_name) {
        this.future_name = future_name;
    }

    public Long getFuture_id() {
        return future_id;
    }

    public void setFuture_id(Long future_id) {
        this.future_id = future_id;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public float getHave() {
        return have;
    }

    public void setHave(float have) {
        this.have = have;
    }
}
