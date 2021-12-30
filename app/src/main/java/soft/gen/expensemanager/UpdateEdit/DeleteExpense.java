package soft.gen.expensemanager.UpdateEdit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import soft.gen.expensemanager.R;
import soft.gen.expensemanager.SQLiteHelper.SQLiteDataBase;
import soft.gen.expensemanager.ViewExpenses.ViewExpense;
import soft.gen.expensemanager.Welcome.WelcomeLogin;

public class DeleteExpense extends AppCompatActivity {
    TextView delete_exp_id;
    ImageView delete_exp_btn,cancel_type_btn;
    SQLiteDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_expense);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Delete Expense");
        db=new SQLiteDataBase(this);
        Bundle extras = getIntent().getExtras();
        final String id = extras.getString("Exp_del_id");

        delete_exp_id=findViewById(R.id.delete_exp_id);
        delete_exp_btn=findViewById(R.id.delete_exp_btn);
        cancel_type_btn=findViewById(R.id.cancel_type_btn);
        delete_exp_id.setText(id);
        DeleteExp();
        cancel_type_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(DeleteExpense.this, ViewExpense.class);
                startActivity(intent);
                finish();

            }
        });
    }

    public void DeleteExp(){
        delete_exp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer DeleteRow=db.deleteExpense(delete_exp_id.getText().toString());
                if (DeleteRow >0){
                    Toast.makeText(DeleteExpense.this, "Row Deleted", Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(DeleteExpense.this, ViewExpense.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(DeleteExpense.this, "Row Did Not Deleted", Toast.LENGTH_SHORT).show();
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
