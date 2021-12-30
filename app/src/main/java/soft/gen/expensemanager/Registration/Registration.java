package soft.gen.expensemanager.Registration;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import soft.gen.expensemanager.Login.LoginPage;
import soft.gen.expensemanager.R;
import soft.gen.expensemanager.SQLiteHelper.SQLiteDataBase;
import soft.gen.expensemanager.SharedPreferences.SharedP;

public class Registration extends AppCompatActivity {
    EditText name_reg,email_reg,phone_reg,password_reg;
    ImageView cancel,register;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    String names, emails, phones, passwords;
    String dates;
    private SharedP sharedpref;
    String mpin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().setTitle("Registration");
        openHelper=new SQLiteDataBase(this);
        sharedpref = new SharedP(this);

        name_reg=findViewById(R.id.name_reg);
        email_reg=findViewById(R.id.email_reg);
        phone_reg=findViewById(R.id.phone_reg);
        password_reg=findViewById(R.id.pass_reg);

        cancel=findViewById(R.id.cancel_reg);
        register=findViewById(R.id.register);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Registration.this,LoginPage.class);
                startActivity(intent);
            }
        });

         register.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 db=openHelper.getReadableDatabase();
                 names=name_reg.getText().toString();
                 emails=email_reg.getText().toString();
                 phones=phone_reg.getText().toString();
                 passwords=password_reg.getText().toString();

                 valid();

                 if(valid()==true){
                     mpin=passwords;

                     insertData(names,emails,phones,passwords,dates);
                     sharedpref.saveLoginDetails(mpin);

                     Toast.makeText(Registration.this, "Registration is Done", Toast.LENGTH_SHORT).show();
                     Intent intent = new Intent(Registration.this, LoginPage.class);
                     startActivity(intent);
                     finish();
                 }
             }
         });
    }

    public boolean valid() {
        boolean prog = true;
        String name = name_reg.getText().toString();
        String email = email_reg.getText().toString();
        String phone = phone_reg.getText().toString();
        String password = password_reg.getText().toString();


        if (name.equals("")) {
            prog = false;
            name_reg.setError("Enter Your First name");
        }
        if (email.equals("")) {
            prog = false;
            email_reg.setError("Enter Email");
        }
        if (phone.equals("")) {
            prog = false;
            phone_reg.setError("Enter Contact No");
        }
        if (password.equals("")) {
            prog = false;
            password_reg.setError("Enter Address");
        }

        return prog;
    }
    public void insertData (String name, String email, String mobile, String password, String date) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLiteDataBase.NAME, name);
        contentValues.put(SQLiteDataBase.EMAIL, email);
        contentValues.put(SQLiteDataBase.MOBILE, mobile);
        contentValues.put(SQLiteDataBase.PASSWORD, password);
        contentValues.put(SQLiteDataBase.DATE, date);
       db.insert(SQLiteDataBase.TABLE_NAME_REGISTRATION, null, contentValues);

    }
}
