package soft.gen.expensemanager.UpdateEdit;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import soft.gen.expensemanager.R;
import soft.gen.expensemanager.SQLiteHelper.SQLiteDataBase;
import soft.gen.expensemanager.Welcome.WelcomeLogin;

public class EditType extends AppCompatActivity {
    EditText edit_typename;
    TextView edit_exp_type_id;
    Spinner edit_spin_status;
    ImageView update_type;
    SQLiteDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_type);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Edit Type");
        db=new SQLiteDataBase(this);

        Bundle extras = getIntent().getExtras();
        String id = extras.getString("typeID");

        edit_exp_type_id=findViewById(R.id.edit_exp_type_id);
        edit_typename= findViewById(R.id.edit_typename);
        update_type=findViewById(R.id.update_type);
        edit_exp_type_id.setText(id);

        UpdateDataTYPE();
    }
    public void UpdateDataTYPE(){
        update_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate=db.updateDataTYPE(
                        edit_exp_type_id.getText().toString(),
                        edit_typename.getText().toString());

                        if(isUpdate == true){
                            Toast.makeText(EditType.this, "Data Updated Done", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(EditType.this,WelcomeLogin.class);
                            startActivity(intent);

                }else {
                            Toast.makeText(EditType.this, "Data Not Updated", Toast.LENGTH_SHORT).show();
                        }
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        finish();

        return super.onOptionsItemSelected(item);
    }
}