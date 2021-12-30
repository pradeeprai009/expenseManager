package soft.gen.expensemanager.Welcome;

import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import android.view.View;

import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import soft.gen.expensemanager.Adapter.Report_Adapter;
import soft.gen.expensemanager.AddExpense.AddExpense;
import soft.gen.expensemanager.AddExpense.ExpenseType;
import soft.gen.expensemanager.Expense;
import soft.gen.expensemanager.Login.LoginPage;
import soft.gen.expensemanager.MainActivity.MainActivity;
import soft.gen.expensemanager.R;
import soft.gen.expensemanager.SQLiteHelper.SQLiteDataBase;
import soft.gen.expensemanager.SharedPreferences.SharedP;
import soft.gen.expensemanager.ViewExpenses.Report;
import soft.gen.expensemanager.ViewExpenses.ViewExpType;
import soft.gen.expensemanager.ViewExpenses.ViewExpense;

public class WelcomeLogin extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    CardView view_head_exp,view_head_exptype,add_head_exp,add_head_exptype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        view_head_exp=findViewById(R.id.view_head_exp);
        view_head_exptype=findViewById(R.id.view_head_exptype);
        add_head_exp=findViewById(R.id.add_head_exp);
        add_head_exptype=findViewById(R.id.add_head_exptype);

        view_head_exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WelcomeLogin.this,ViewExpense.class);
                startActivity(intent);
            }
        });
        view_head_exptype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WelcomeLogin.this,ViewExpType.class);
                startActivity(intent);
            }
        });
        add_head_exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WelcomeLogin.this,AddExpense.class);
                startActivity(intent);
            }
        });
        add_head_exptype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WelcomeLogin.this,ExpenseType.class);
                startActivity(intent);
            }
        });

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }


    void AppShare() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey Check Out This app: https://i.diawi.com/ijkuyp");
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, "Sharing Option"));
        // startActivity(sendIntent);
    }

    void AppRate() {
        Uri uri = Uri.parse("market://details?id=" + WelcomeLogin.this.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=")));
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.welcome_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            Intent intent = new Intent(WelcomeLogin.this, LoginPage.class);
            startActivity(intent);
            finish();

        }else if(id == R.id.profile){
            Intent intent =new Intent(WelcomeLogin.this,Profile.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.add_exp) {
            Intent intent = new Intent(WelcomeLogin.this, AddExpense.class);
            startActivity(intent);

            // Handle the camera action
        } else if (id == R.id.exp_type) {
            Intent intent = new Intent(WelcomeLogin.this, ExpenseType.class);
            startActivity(intent);


        } else if (id == R.id.view_exp) {
            Intent intent = new Intent(WelcomeLogin.this, ViewExpense.class);
            startActivity(intent);


        } else if (id == R.id.view_exp_type) {
            Intent intent = new Intent(WelcomeLogin.this, ViewExpType.class);
            startActivity(intent);

        }else  if(id==R.id.report){
            Intent intent= new Intent(WelcomeLogin.this, Report.class);
            startActivity(intent);
        }else  if(id==R.id.logouthead){
            Intent intent= new Intent(WelcomeLogin.this, LoginPage.class);
            startActivity(intent);
            finish();
        }else  if(id==R.id.rateus){

            AppRate();

            }else  if(id==R.id.share){

           AppShare();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
