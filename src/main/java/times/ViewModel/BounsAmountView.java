package times.ViewModel;

/**
 * Created by 13987 on 2017/1/2.
 */
public class BounsAmountView {
    private float bonus;
    private float anount;

    public BounsAmountView() {
    }

    public float getBonus() {
        return bonus;
    }

    public void setBonus(float bonus) {
        this.bonus = bonus;
    }

    public float getAnount() {
        return anount;
    }

    public void setAnount(float anount) {
        this.anount = anount;
    }

    public BounsAmountView(float bonus, float anount) {
        this.bonus = bonus;
        this.anount = anount;
    }
}
