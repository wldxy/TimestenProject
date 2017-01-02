package times.ViewModel;

import java.math.BigDecimal;

/**
 * Created by 13987 on 2017/1/2.
 */
public class FutureDescriptionViewModel {
    private String future_name;
    private Long future_id;
    private int have;

    public FutureDescriptionViewModel(String future_name, Long future_id, int have) {
        this.future_name = future_name;
        this.future_id = future_id;
        this.have = have;
    }

    public int getHave() {
        return have;
    }

    public void setHave(int have) {
        this.have = have;
    }

    public FutureDescriptionViewModel() {
    }

    public FutureDescriptionViewModel(String future_name, Long future_id) {
        this.future_name = future_name;
        this.future_id = future_id;
    }

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
}
