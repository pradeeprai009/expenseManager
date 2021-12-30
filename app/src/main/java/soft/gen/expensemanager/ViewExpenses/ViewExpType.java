package soft.gen.expensemanager.ViewExpenses;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import soft.gen.expensemanager.Adapter.Expense_Adapter;
import soft.gen.expensemanager.Adapter.Type_Adapter;
import soft.gen.expensemanager.Expense;
import soft.gen.expensemanager.R;
import soft.gen.expensemanager.SQLiteHelper.SQLiteDataBase;
import soft.gen.expensemanager.Type;
import soft.gen.expensemanager.UpdateEdit.DeleteType;
import soft.gen.expensemanager.UpdateEdit.EditType;

public class ViewExpType extends AppCompatActivity {
    SQLiteDataBase db;
    ListView view_expense_type_list;
    ArrayList<Type> list2;
    Type_Adapter adapter;
    Activity activity;
    TextView nodata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewexpensetypelist);
        nodata = findViewById(R.id.nodata);
        db = new SQLiteDataBase(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("View Expense type");
        activity = this;

        view_expense_type_list = findViewById(R.id.view_expense_type_list);

        list2 = new ArrayList();
        view_expense_type_list.setAdapter(adapter);
        db = new SQLiteDataBase(this);
        loadDataInListView();

        if (list2.size() == 0) {
            nodata.setVisibility(View.VISIBLE);

        } else {
            nodata.setVisibility(View.GONE);
        }
    }

    private void loadDataInListView() {
        list2 = db.getTypeData();
        adapter = new Type_Adapter(this, list2, activity);
        adapter.notifyDataSetChanged();
        view_expense_type_list.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        finish();

        return super.onOptionsItemSelected(item);
    }
}
