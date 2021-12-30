package soft.gen.expensemanager.SplashScreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import soft.gen.expensemanager.Login.LoginPage;
import soft.gen.expensemanager.MainActivity.MainActivity;
import soft.gen.expensemanager.R;
import soft.gen.expensemanager.Registration.Registration;
import soft.gen.expensemanager.SharedPreferences.SharedP;
import soft.gen.expensemanager.Welcome.WelcomeLogin;

public class SplashScreen extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        /** Sets a layout for this activity */
        setContentView(R.layout.splashscreen);

        /** Creates a count down timer, which will be expired after 5000 milliseconds */
        new CountDownTimer(1000,200) {

            /** This method will be invoked on finishing or expiring the timer */
            @Override
            public void onFinish() {

                /** Creates an intent to start new activity */
                Intent intent = new Intent(getApplication(), LoginPage.class);
                startActivity(intent);
                finish();
            }
            @Override
            public void onTick(long millisUntilFinished) {

            }
        }.start();
    }
}


