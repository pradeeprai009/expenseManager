package soft.gen.expensemanager.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;

import soft.gen.expensemanager.Expense;
import soft.gen.expensemanager.R;

public class Report_Adapter extends BaseAdapter {
    Context context;
    ArrayList<Expense> arrayList1;
    Activity activity;

    public Report_Adapter(Context context, ArrayList<Expense> arrayList1, Activity activity) {
        this.context = context;
        this.arrayList1 = arrayList1;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return arrayList1.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList1.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.activity_report, null);
        // TextView report_Id=convertView.findViewById(R.id.report_Id);
        TextView report_date = convertView.findViewById(R.id.report_date);
        TextView report_name = convertView.findViewById(R.id.report_expname);
        TextView report_credit = convertView.findViewById(R.id.report_credit);
        TextView report_debit = convertView.findViewById(R.id.report_debit);
        TextView report_amount = convertView.findViewById(R.id.report_amount);
        TextView report_type = convertView.findViewById(R.id.report_type);
        LinearLayout lyr_top = convertView.findViewById(R.id.lyr_top);
        final LinearLayout lyr_below = convertView.findViewById(R.id.lyr_below);
        final ImageView down=convertView.findViewById(R.id.down);
        final ImageView upview=convertView.findViewById(R.id.upview);

        Expense report = arrayList1.get(position);

        // report_Id.setText(String.valueOf(position+1));
        report_date.setText(report.getDateexp());
        report_name.setText(report.getNameexp());
        report_type.setText(report.getTypeexp());
        String color = String.valueOf(arrayList1.get(position).getTransexp());

        if (color.equals("Dr.")) {
            report_amount.setTextColor(context.getResources().getColor(R.color.RED));
        } else {
            report_amount.setTextColor(context.getResources().getColor(R.color.GREEN));
        }

        String crdr = String.valueOf(arrayList1.get(position).getTransexp());

        if (crdr.equals("Dr.")) {
            report_debit.setText(crdr);
            report_credit.setText("--");
        } else {
            report_credit.setText(crdr);
            report_debit.setText("--");
        }

        lyr_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (lyr_below.getVisibility()==View.VISIBLE){
                    lyr_below.setVisibility(View.GONE);
                    down.setVisibility(View.VISIBLE);
                    upview.setVisibility(View.GONE);
                }
                else {
                    lyr_below.setVisibility(View.VISIBLE);
                    down.setVisibility(View.GONE);
                    upview.setVisibility(View.VISIBLE);

                }
            }
        });

        report_amount.setText(report.getAmountexp());
        return convertView;
    }
}
