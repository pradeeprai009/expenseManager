package soft.gen.expensemanager.ForgetPassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import soft.gen.expensemanager.Login.LoginPage;
import soft.gen.expensemanager.R;
import soft.gen.expensemanager.SQLiteHelper.SQLiteDataBase;
import soft.gen.expensemanager.Welcome.WelcomeLogin;

public class ForgetPassword extends AppCompatActivity {
    EditText forget_password;
    ImageView send_password;
    String parameter;
    String pass;
    private SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Forget Password");

        openHelper = new SQLiteDataBase(this);
        db = openHelper.getReadableDatabase();
        Bundle extras = getIntent().getExtras();
        final String mpin = extras.getString("mpin");

        forget_password=findViewById(R.id.forget_password);
        send_password=findViewById(R.id.send_password);

        send_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = forget_password.getText().toString();
                cursor = db.rawQuery(" SELECT * FROM " + SQLiteDataBase.TABLE_NAME_REGISTRATION + " WHERE "
                        + SQLiteDataBase.EMAIL + " = ? ", new String[]{email});

                if (cursor != null) {
                    if (cursor.getCount() > 0) {

                        cursor.moveToNext();
                        pass = cursor.getString(cursor.getColumnIndex(SQLiteDataBase.PASSWORD));
                        Toast.makeText(ForgetPassword.this, "Your MPIN is: " + pass, Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(ForgetPassword.this, WelcomeLogin.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(ForgetPassword.this, "Your Details Are Wrong", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        Intent intent=new Intent(ForgetPassword.this,LoginPage.class);
        startActivity(intent);
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent =new Intent(ForgetPassword.this, LoginPage.class);
        startActivity(intent);
        finish();
    }
}
