package soft.gen.expensemanager.UpdateEdit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import soft.gen.expensemanager.ViewExpenses.ViewExpType;
import soft.gen.expensemanager.ViewExpenses.ViewExpense;
import soft.gen.expensemanager.Welcome.WelcomeLogin;

public class DeleteType extends AppCompatActivity {
    TextView delete_id;
    ImageView delete_type_btn,cancel_type_btn;
    SQLiteDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_type);
        //Back Action Button On Header//////
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Delete Type");

        db=new SQLiteDataBase(this);

        Bundle extras = getIntent().getExtras();
        String id = extras.getString("typeID");

        delete_id=findViewById(R.id.delete_id);
        cancel_type_btn=findViewById(R.id.cancel_type_btn);
        delete_type_btn=findViewById(R.id.delete_type_btn);
        delete_id.setText(id);

        cancel_type_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DeleteType.this, ViewExpType.class);
                startActivity(intent);
                finish();
            }
        });

        DeleteData();

    }
    public void DeleteData(){
        delete_type_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRows=db.deleteTYPE(delete_id.getText().toString());
                if(deletedRows > 0){
                    Toast.makeText(DeleteType.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(DeleteType.this, ViewExpType.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(DeleteType.this, "Data Not Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //code to implement for Back press Button on Header//
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        finish();

        return super.onOptionsItemSelected(item);
    }
}
