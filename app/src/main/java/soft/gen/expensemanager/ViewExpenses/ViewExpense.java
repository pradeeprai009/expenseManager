package soft.gen.expensemanager.ViewExpenses;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import soft.gen.expensemanager.Adapter.Expense_Adapter;
import soft.gen.expensemanager.Expense;
import soft.gen.expensemanager.R;
import soft.gen.expensemanager.SQLiteHelper.SQLiteDataBase;
import soft.gen.expensemanager.UpdateEdit.DeleteExpense;
import soft.gen.expensemanager.UpdateEdit.EditExpense;

public class ViewExpense extends AppCompatActivity {
    SQLiteDataBase db;
    ListView view_expense_list;
    ArrayList<Expense> list2;
    Expense_Adapter adapter;
    Activity activity;
    TextView nodataexp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewexpenselist);
        nodataexp = findViewById(R.id.nodataexp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("View Expense");
        activity = this;
        view_expense_list = findViewById(R.id.view_expense_list);
        list2 = new ArrayList();
        view_expense_list.setAdapter(adapter);
        db = new SQLiteDataBase(this);
        loadDataInListView();

        if (list2.size() == 0) {
            nodataexp.setVisibility(View.VISIBLE);

        } else {
            nodataexp.setVisibility(View.GONE);
        }
    }

    private void loadDataInListView() {
        list2 = db.getExpenseData();
        adapter = new Expense_Adapter(this, list2, activity);
        adapter.notifyDataSetChanged();
        view_expense_list.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        finish();
        return super.onOptionsItemSelected(item);
    }
}
