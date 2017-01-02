package times.ViewModel;

import java.math.BigDecimal;

/**
 * Created by 13987 on 2017/1/2.
 */
public class PackageViewModel {
    private int index;
    private BigDecimal price;

    public PackageViewModel() {
    }

    public PackageViewModel(int index, BigDecimal price) {
        this.index = index;
        this.price = price;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
