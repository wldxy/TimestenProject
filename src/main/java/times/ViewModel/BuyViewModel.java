package times.ViewModel;

/**
 * Created by 13987 on 2017/1/2.
 */
public class BuyViewModel {
    private Long f_Id;
    private Long u_Id;
    private Integer number;
    private Float price;
    private String pwd;

    public BuyViewModel(Long f_Id, Long u_Id, Integer number, Float price, String pwd) {
        this.f_Id = f_Id;
        this.u_Id = u_Id;
        this.number = number;
        this.price = price;
        this.pwd = pwd;
    }

    public BuyViewModel() {
    }

    public Long getF_Id() {
        return f_Id;
    }

    public void setF_Id(Long f_Id) {
        this.f_Id = f_Id;
    }

    public Long getU_Id() {
        return u_Id;
    }

    public void setU_Id(Long u_Id) {
        this.u_Id = u_Id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
