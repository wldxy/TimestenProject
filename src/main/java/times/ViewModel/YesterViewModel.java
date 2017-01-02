package times.ViewModel;

import java.util.ArrayList;

/**
 * Created by 13987 on 2017/1/2.
 */
public class YesterViewModel {
    private String future_name;
    private Long future_id;
    private float rate;
    private int have;
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    private ArrayList<YesterdayListView> yesterdayListView= new ArrayList<YesterdayListView>();
    public YesterViewModel(String future_name, Long future_id, float rate, int have, ArrayList<YesterdayListView> yesterdayListView) {
        this.future_name = future_name;
        this.future_id = future_id;
        this.rate = rate;
        this.have = have;
        this.yesterdayListView = yesterdayListView;
    }

    public ArrayList<YesterdayListView> getYesterdayListView() {
        return yesterdayListView;
    }

    public void setYesterdayListView(ArrayList<YesterdayListView> yesterdayListView) {
        this.yesterdayListView = yesterdayListView;
    }

    public YesterViewModel() {
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

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public int getHave() {
        return have;
    }

    public void setHave(int have) {
        this.have = have;
    }


}
