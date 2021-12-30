package soft.gen.expensemanager.AddExpense;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.Time;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import soft.gen.expensemanager.Adapter.Report_Adapter;
import soft.gen.expensemanager.Expense;
import soft.gen.expensemanager.R;
import soft.gen.expensemanager.Registration.Registration;
import soft.gen.expensemanager.SQLiteHelper.SQLiteDataBase;
import soft.gen.expensemanager.SharedPreferences.SharedP;
import soft.gen.expensemanager.Type;
import soft.gen.expensemanager.ViewExpenses.ViewExpense;
import soft.gen.expensemanager.Welcome.WelcomeLogin;

public class AddExpense extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText exp_amount, description;
    ImageView add_details;
    TextView exp_name, exp_tvdate;
    DatePickerDialog.OnDateSetListener mDateSetListner;
    SQLiteDataBase db;
    ArrayList<Type> list3;
    String name, amount, dates, des, spinner_trans, spinner_exp = "";
    Spinner spin_trans, spin_exp;
    List<String> labels1;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Expense");

        builder = new AlertDialog.Builder(this);
        exp_tvdate = findViewById(R.id.exp_tvdate);
        exp_name = findViewById(R.id.exp_name);
        exp_amount = findViewById(R.id.exp_amount);
        description = findViewById(R.id.description);
        add_details = findViewById(R.id.add_details);
        spin_trans = findViewById(R.id.spin_trans);
        spin_exp = findViewById(R.id.spin_exptype);
        list3 = new ArrayList();
        labels1 = new ArrayList();
        db = new SQLiteDataBase(this);
        list3 = db.getTypeData();
        // Loading spinner data from database
        loadSpinnerData();

        exp_tvdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(AddExpense.this,
                        android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth,
                        mDateSetListner, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListner = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                exp_tvdate.setText(date);
            }
        };

        add_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation();
                if (validation() == true) {
                    db.insertData(name = exp_name.getText().toString(),
                            spinner_trans = spin_trans.getSelectedItem().toString(),
                            dates = exp_tvdate.getText().toString(),
                            spinner_exp = spin_exp.getSelectedItem().toString(),
                            amount = exp_amount.getText().toString(),
                            des = description.getText().toString());
                    if(spinner_trans.isEmpty()){
                        Toast.makeText(AddExpense.this, "Please Select Type", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(AddExpense.this, "Added Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddExpense.this, ViewExpense.class);
                        startActivity(intent);
                        finish();
                    }
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

    @Override
    public void onBackPressed() {
        //this is only needed if you have specific things
        //that you want to do when the user presses the back button.
        /* your specific things...*/

        finish();
    }

    public boolean validation() {
        boolean check = true;
        String addname = exp_name.getText().toString();
        String addamount = exp_amount.getText().toString();
        String adddes = description.getText().toString();
        String datess = exp_tvdate.getText().toString();
        spinner_trans = spin_trans.getSelectedItem().toString();
//        spinner_exp = spin_exp.getSelectedItem().toString();
        if (datess.equals("")) {
            check = false;
            exp_tvdate.setError("Enter Date");
        }
        if (addname.equals("")) {
            check = false;
            exp_name.setError("Please Enter Expense Name");
        }
        if (addamount.equals("")) {
            check = false;
            exp_amount.setError("Please Enter Amount");
        }
        if (adddes.equals("")) {
            check = false;
            description.setError("Please Enter Description");
        }
        if (spinner_trans.equals("Select Transaction Type")) {
            check = false;
            Toast.makeText(this, "Please Choose Transaction type", Toast.LENGTH_SHORT).show();
        }
        if(spinner_trans.equals("Options:-")){
            check=false;
            Toast.makeText(this, "Please Select Option", Toast.LENGTH_SHORT).show();

        }
        if (list3.size() == 0) {

            //Uncomment the below code to Set the message and title from the strings.xml file
            builder.setMessage("Expense Type").setTitle("Want to add new Expense type");

            //Setting message manually and performing action on button click
            builder.setMessage("No expense type available. Want to add new expense type?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(AddExpense.this, ExpenseType.class);
                            startActivity(intent);
                            finish();
                          /*  Toast.makeText(getApplicationContext(), "you choose yes action for alertbox",
                                    Toast.LENGTH_SHORT).show();*/
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //  Action for 'NO' Button
                            dialog.cancel();
                          /*  Toast.makeText(getApplicationContext(), "you choose no action for alertbox",
                                    Toast.LENGTH_SHORT).show();*/
                        }
                    });
            //Creating dialog box
            AlertDialog alert = builder.create();
            //Setting the title manually
            alert.setTitle("Alert!");
            alert.show();
            return false;
        }
        /*Toast.makeText(this, "pls select typ[e first or add type", Toast.LENGTH_SHORT).show();
        return false;*/

        return check;
    }

    private void loadSpinnerData() {
        // DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        List<Type> labels = db.getTypeData();

        for (int i = 0; i < labels.size(); i++) {
            labels1.add(i, labels.get(i).getName_type());
        }

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, labels1);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spin_exp.setAdapter(dataAdapter);
        spin_exp.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String label = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        if (label.equals("")) {
            Toast.makeText(parent.getContext(), "You selected: " + label,
                    Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
