package times.ViewModel;

/**
 * Created by 13987 on 2017/1/2.
 */
public class LoginViewMoldel {
    private String flag;
    private String id;

    public LoginViewMoldel(String flag, String id) {
        this.flag = flag;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LoginViewMoldel() {
    }

    public LoginViewMoldel(String flag) {
        this.flag = flag;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
