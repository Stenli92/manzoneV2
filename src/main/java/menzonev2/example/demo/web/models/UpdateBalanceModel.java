package menzonev2.example.demo.web.models;

public class UpdateBalanceModel {

    private Integer moneyToInsert;
    private String password;
    private String confirmPass;

    public UpdateBalanceModel() {
    }

    public Integer getMoneyToInsert() {
        return moneyToInsert;
    }

    public void setMoneyToInsert(Integer moneyToInsert) {
        this.moneyToInsert = moneyToInsert;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }
}
