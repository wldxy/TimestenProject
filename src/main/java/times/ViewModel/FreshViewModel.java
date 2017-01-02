package times.ViewModel;

/**
 * Created by 13987 on 2017/1/2.
 */
public class FreshViewModel {
    private Long future_id;
    private float price;
    private int state;

    public FreshViewModel(Long future_id, float price, int state) {
        this.future_id = future_id;
        this.price = price;
        this.state = state;
    }

    public FreshViewModel() {
    }

    public Long getFuture_id() {
        return future_id;
    }

    public void setFuture_id(Long future_id) {
        this.future_id = future_id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
