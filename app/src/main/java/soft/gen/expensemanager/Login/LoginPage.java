package soft.gen.expensemanager.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.goodiebag.pinview.Pinview;

import soft.gen.expensemanager.ForgetPassword.ForgetPassword;
import soft.gen.expensemanager.R;
import soft.gen.expensemanager.Registration.Registration;
import soft.gen.expensemanager.SQLiteHelper.SQLiteDataBase;
import soft.gen.expensemanager.SharedPreferences.SharedP;
import soft.gen.expensemanager.Welcome.WelcomeLogin;

public class LoginPage extends AppCompatActivity {
    PinEntryEditText txt_pin_entry;
    PinEntryEditText pinEntry;
    TextView create_account, login_forget_password;
    ImageView login;
    Cursor cursor;
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    private SharedP sharedpref;
    String logmPin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Login");

        //Getting Database Data.

        openHelper=new SQLiteDataBase(this);
        db=openHelper.getReadableDatabase();
        sharedpref = new SharedP(this);

        //getting id from xml layout
        txt_pin_entry=findViewById(R.id.txt_pin_entry);

        create_account=findViewById(R.id.create_account);
        login_forget_password=findViewById(R.id.login_forget_password);
        login=findViewById(R.id.login);

        final String is_mpin = sharedpref.getMpin();
        if(TextUtils.isEmpty(is_mpin)) {
            Intent i = new Intent(this, Registration.class);
            startActivity(i);
            finish();
        }

        pinEntry = findViewById(R.id.txt_pin_entry);
        if (pinEntry != null) {
            pinEntry.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
                @Override
                public void onPinEntered(CharSequence str) {
                    if (str.toString().equals(is_mpin)) {
                        Intent intent=new Intent(LoginPage.this,WelcomeLogin.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(LoginPage.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginPage.this, "Wrong MPIN", Toast.LENGTH_SHORT).show();
                        pinEntry.setText(null);
                    }
                }
            });
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(cursor!=null){
                    if(cursor.getCount()>0){
                        cursor.moveToNext();

                        sharedpref.saveLoginDetails(logmPin);

                     //   Toast.makeText(LoginPage.this, "", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(LoginPage.this,WelcomeLogin.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(LoginPage.this, "Please Enter Correct MPIN", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });

        login_forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(LoginPage.this, "Please Wait", Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(LoginPage.this, ForgetPassword.class);
                intent.putExtra("mpin",logmPin);
                startActivity(intent);
                finish();
            }
        });

        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginPage.this, "Please Wait", Toast.LENGTH_SHORT).show();

                Intent intent =new Intent(LoginPage.this, Registration.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
