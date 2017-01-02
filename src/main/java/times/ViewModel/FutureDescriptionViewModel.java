package times.ViewModel;

/**
 * Created by 13987 on 2017/1/2.
 */
public class FutureDescriptionViewModel {
    private String future_name;
    private Long future_id;

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
