package soft.gen.expensemanager.Welcome;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.net.URI;

import soft.gen.expensemanager.R;

public class Profile extends AppCompatActivity {
    ImageView insta, facebook;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Developer Profile");
        insta=findViewById(R.id.insta);
        facebook=findViewById(R.id.facebook);
        activity=this;
        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToInstagram();
            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFacebook();
            }
        });
    }

    private  void goToFacebook(){
            Intent intent =new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/raipradeep009"));
            startActivity(intent);
    }

    private  void goToInstagram(){
        Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.instagram.com/pradeep_rai009/?hl=en"));
        startActivity(intent);
    }
}
