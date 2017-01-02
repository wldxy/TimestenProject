package times.ViewModel;

import java.util.Date;

/**
 * Created by 13987 on 2017/1/2.
 */
public class RateViewModle {
    private Date date;
    private float open;
    private float high;
    private float low;
    private float close;

    public RateViewModle() {
    }

    public RateViewModle(Date date, float open, float high, float low, float close) {
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getOpen() {
        return open;
    }

    public void setOpen(float open) {
        this.open = open;
    }

    public float getHigh() {
        return high;
    }

    public void setHigh(float high) {
        this.high = high;
    }

    public float getLow() {
        return low;
    }

    public void setLow(float low) {
        this.low = low;
    }

    public float getClose() {
        return close;
    }

    public void setClose(float close) {
        this.close = close;
    }
}
