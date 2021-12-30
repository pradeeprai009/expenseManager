package soft.gen.expensemanager.ViewExpenses;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import soft.gen.expensemanager.Adapter.Expense_Adapter;
import soft.gen.expensemanager.Adapter.Report_Adapter;
import soft.gen.expensemanager.Expense;
import soft.gen.expensemanager.R;
import soft.gen.expensemanager.SQLiteHelper.SQLiteDataBase;
import soft.gen.expensemanager.SharedPreferences.SharedP;

public class Report extends AppCompatActivity {
    SQLiteDataBase db;
    ListView view_expense_list;
    ArrayList<Expense> list2;
    Report_Adapter adapter;
    Activity activity;
    TextView total_report_amount,total_report_amountcr,total_report_amountdr,nodatareport;
    int fina_amt=0;
    int cramt=0;
    int dramt=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewreportlist);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Report");

        view_expense_list = findViewById(R.id.report);
        total_report_amount = findViewById(R.id.total_report_amount);
        total_report_amountcr=findViewById(R.id.total_report_amountcr);
        total_report_amountdr=findViewById(R.id.total_report_amountdr);
        nodatareport=findViewById(R.id.nodatareport);

        list2 = new ArrayList();
        view_expense_list.setAdapter(adapter);
        db = new SQLiteDataBase(this);
        reportListView();

        for (int i = 0; i < list2.size(); i++) {

            fina_amt +=  Integer.parseInt(list2.get(i).getAmountexp());
        }

        for (int i = 0; i < list2.size(); i++) {
            String crdr= String.valueOf(list2.get(i).getTransexp());
            if(crdr.equals("Cr.")){
                cramt+=Integer.parseInt(list2.get(i).getAmountexp());
            }
        }

        for (int i = 0; i < list2.size(); i++){
        String crdr= String.valueOf(list2.get(i).getTransexp());
        if(crdr.equals("Dr.")){
            dramt+=Integer.parseInt(list2.get(i).getAmountexp());
        }
    }
        total_report_amountdr.setText(""+dramt);
        total_report_amountcr.setText(""+cramt);

        fina_amt = cramt -dramt;
        total_report_amount.setText(""+fina_amt);

        if(fina_amt >= 0){
            total_report_amount.setTextColor(getResources().getColor(R.color.GREEN));

        }else{
            total_report_amount.setTextColor(getResources().getColor(R.color.RED));
        }
        if(list2.size()==0){
            nodatareport.setVisibility(View.VISIBLE);

        }else {
            nodatareport.setVisibility(View.GONE);
        }
    }

    private void reportListView() {

        list2 = db.getExpenseData();

        adapter = new Report_Adapter(this, list2, activity);
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