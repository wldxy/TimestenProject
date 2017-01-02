package times.ViewModel;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by 13987 on 2017/1/2.
 */
public class FutureInfoViewModle {
    private Long id;
    private String name;
    private ArrayList<PackageViewModel> packageViewModels = new ArrayList<PackageViewModel>();
    private ArrayList<RateViewModle> rateViewModle = new ArrayList<RateViewModle>();

    public FutureInfoViewModle(Long id, String name, ArrayList<PackageViewModel> packageViewModels, ArrayList<RateViewModle> rateViewModle) {
        this.id = id;
        this.name = name;
        this.packageViewModels = packageViewModels;
        this.rateViewModle = rateViewModle;
    }

    public ArrayList<RateViewModle> getRateViewModle() {
        return rateViewModle;
    }

    public void setRateViewModle(ArrayList<RateViewModle> rateViewModle) {
        this.rateViewModle = rateViewModle;
    }

    public FutureInfoViewModle(Long id, String name, ArrayList<PackageViewModel> packageViewModels) {
        this.id = id;
        this.name = name;
        this.packageViewModels = packageViewModels;
    }

    public ArrayList<PackageViewModel> getPackageViewModels() {
        return packageViewModels;
    }

    public void setPackageViewModels(ArrayList<PackageViewModel> packageViewModels) {
        this.packageViewModels = packageViewModels;
    }

    public FutureInfoViewModle() {
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


}
