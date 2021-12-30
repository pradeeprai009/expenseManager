package soft.gen.expensemanager.AddExpense;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import soft.gen.expensemanager.R;
import soft.gen.expensemanager.SQLiteHelper.SQLiteDataBase;
import soft.gen.expensemanager.ViewExpenses.ViewExpType;
import soft.gen.expensemanager.Welcome.WelcomeLogin;

public class ExpenseType extends AppCompatActivity {
    EditText typename;
    Spinner spin_status;
    ImageView submit;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    String id_type, ex_type_name, ex_type_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_type);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Expense Type");
        openHelper = new SQLiteDataBase(this);
        typename = findViewById(R.id.typename);
        spin_status = findViewById(R.id.spin_status);

        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db = openHelper.getReadableDatabase();
                ex_type_name = typename.getText().toString();
                vali();

                if (vali() == true) {
                    insertData(id_type, ex_type_name, ex_type_status);
                    Toast.makeText(ExpenseType.this, "Added Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ExpenseType.this, ViewExpType.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(ExpenseType.this, "Please Fill All the Filled", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public boolean vali() {
        boolean ck = true;
        String name = typename.getText().toString();

        if (name.equals("")) {
            ck = false;
            typename.setError("Enter Name");
        }
        return ck;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        finish();

        return super.onOptionsItemSelected(item);
    }

    public void insertData(String id, String name, String status) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLiteDataBase.NAME_TYPE, name);
        contentValues.put(SQLiteDataBase.ID_TYPE, id);
        contentValues.put(SQLiteDataBase.STATUS, status);
        db.insert(SQLiteDataBase.TABLE_NAME_TYPE, null, contentValues);
    }
}
