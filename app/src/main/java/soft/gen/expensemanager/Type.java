package soft.gen.expensemanager;

import androidx.appcompat.app.AppCompatActivity;

public class Type extends AppCompatActivity {
    String id2;
    String name_type, date_type, status_type;

    public Type(String id2, String name_type, String date_type, String status_type) {
        this.id2 = id2;
        this.name_type = name_type;
        this.date_type = date_type;
        this.status_type = status_type;
    }

    public String getId2() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }

    public String getName_type() {
        return name_type;
    }

    public void setName_type(String name_type) {
        this.name_type = name_type;
    }

    public String getDate_type() {
        return date_type;
    }

    public void setDate_type(String date_type) {
        this.date_type = date_type;
    }

    public String getStatus_type() {
        return status_type;
    }

    public void setStatus_type(String status_type) {
        this.status_type = status_type;
    }
}
