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

public class EditExpense extends AppCompatActivity {

    EditText edit_exp_name,edit_exp_amount,edit_description;
    TextView edit_exp_id;
    Spinner edit_spin_trans;
    ImageView edit_add_details;
    SQLiteDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_expense);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Edit Expense");
        db=new SQLiteDataBase(this);

        Bundle extras = getIntent().getExtras();
        final String id = extras.getString("Exp_del_id");

        edit_exp_id=findViewById(R.id.edit_exp_id);
        edit_exp_name=findViewById(R.id.edit_exp_name);
        edit_exp_amount=findViewById(R.id.edit_exp_amount);
        edit_description=findViewById(R.id.edit_description);
        edit_add_details=findViewById(R.id.edit_add_details);
        edit_spin_trans=findViewById(R.id.edit_spin_trans);

        edit_exp_id.setText(id);

        UpdateData();

    }
    public void UpdateData(){
        edit_add_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isupdated=db.updateData(
                        edit_exp_id.getText().toString(),
                        edit_exp_name.getText().toString(),
                        edit_exp_amount.getText().toString(),
                        edit_description.getText().toString(),
                        edit_spin_trans.getSelectedItem().toString());
                if(isupdated == true){
                    Toast.makeText(EditExpense.this, "Data Updated Done", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditExpense.this,WelcomeLogin.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(EditExpense.this, "Data Not Updated ", Toast.LENGTH_SHORT).show();
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