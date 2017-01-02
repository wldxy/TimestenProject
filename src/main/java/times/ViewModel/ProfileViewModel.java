package times.ViewModel;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by 13987 on 2017/1/2.
 */
public class ProfileViewModel {
    private Long id;
    private String name;
    private String count_id;
    private float bonus;
    private BigDecimal amount;
    private BigDecimal left_money;
    public ProfileViewModel(Long id, String name, String count_id, float bonus, BigDecimal amount, BigDecimal left_money, ArrayList<FutureDescriptionViewModel> my_future) {
        this.id = id;
        this.name = name;
        this.count_id = count_id;
        this.bonus = bonus;
        this.amount = amount;
        this.left_money = left_money;
        this.my_future = my_future;
    }

    public BigDecimal getLeft_money() {
        return left_money;
    }

    public void setLeft_money(BigDecimal left_money) {
        this.left_money = left_money;
    }

    private ArrayList<FutureDescriptionViewModel> my_future;

    public ProfileViewModel(Long id, String name, String count_id, float bonus, BigDecimal amount, ArrayList<FutureDescriptionViewModel> my_future) {
        this.id = id;
        this.name = name;
        this.count_id = count_id;
        this.bonus = bonus;
        this.amount = amount;
        this.my_future = my_future;
    }

    public ProfileViewModel() {
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

    public String getCount_id() {
        return count_id;
    }

    public void setCount_id(String count_id) {
        this.count_id = count_id;
    }

    public float getBonus() {
        return bonus;
    }

    public void setBonus(float bonus) {
        this.bonus = bonus;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public ArrayList<FutureDescriptionViewModel> getMy_future() {
        return my_future;
    }

    public void setMy_future(ArrayList<FutureDescriptionViewModel> my_future) {
        this.my_future = my_future;
    }
}
