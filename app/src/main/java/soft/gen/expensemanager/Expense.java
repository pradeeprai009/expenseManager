package soft.gen.expensemanager;

import androidx.appcompat.app.AppCompatActivity;

public class Expense extends AppCompatActivity {

    String id;
    String nameexp, transexp, amountexp, typeexp,dateexp,descexp;
    public Expense(String id, String nameexp, String transexp, String amountexp, String typeexp, String dateexp, String descexp){
        this.id=id;
        this.nameexp=nameexp;
        this.transexp=transexp;
        this.amountexp=amountexp;
        this.typeexp=typeexp;
        this.dateexp=dateexp;
        this.descexp=descexp;

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameexp() {
        return nameexp;
    }

    public void setNameexp(String nameexp) {
        this.nameexp = nameexp;
    }

    public String getTransexp() {
        return transexp;
    }

    public void setTransexp(String transexp) {
        this.transexp = transexp;
    }

    public String getAmountexp() {
        return amountexp;
    }

    public void setAmountexp(String amountexp) {
        this.amountexp = amountexp;
    }

    public String getTypeexp() {
        return typeexp;
    }

    public void setTypeexp(String typeexp) {
        this.typeexp = typeexp;
    }

    public String getDateexp() {
        return dateexp;
    }

    public void setDateexp(String dateexp) {
        this.dateexp = dateexp;
    }

    public String getDescexp() {
        return descexp;
    }

    public void setDescexp(String descexp) {
        this.descexp = descexp;
    }
}
