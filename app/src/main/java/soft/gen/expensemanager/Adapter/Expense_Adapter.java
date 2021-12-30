package soft.gen.expensemanager.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import soft.gen.expensemanager.AddExpense.AddExpense;
import soft.gen.expensemanager.AddExpense.ExpenseType;
import soft.gen.expensemanager.Expense;
import soft.gen.expensemanager.R;
import soft.gen.expensemanager.SQLiteHelper.SQLiteDataBase;
import soft.gen.expensemanager.UpdateEdit.DeleteExpense;
import soft.gen.expensemanager.UpdateEdit.EditExpense;
import soft.gen.expensemanager.UpdateEdit.EditType;
import soft.gen.expensemanager.ViewExpenses.ViewExpense;

public class Expense_Adapter extends BaseAdapter {
    Context context;
    ArrayList<Expense> arrayList1;
    private static Activity activity;
    AlertDialog.Builder builder;

    public Expense_Adapter(Context context, ArrayList<Expense> arrayList1, Activity aactivity) {
        this.context = context;
        this.arrayList1 = arrayList1;
        this.activity = aactivity;

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

    public class ViewHolder {
        String id;
        TextView viewExpName, viewTransType, viewAmount, viewDes, viewExpID,  Type_Name;
        ImageView viewDeleteExp,viewEditExp;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        View row = convertView;

        ViewHolder holder = new ViewHolder();
        builder = new AlertDialog.Builder(context);

        if (row == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.activity_view_expense, null);

            holder.viewExpName = row.findViewById(R.id.viewExpName);
            holder.viewTransType = row.findViewById(R.id.viewTransType);
            holder.viewAmount = row.findViewById(R.id.viewAmount);
            holder.viewDes = row.findViewById(R.id.viewDes);
            holder.Type_Name = row.findViewById(R.id.Type_Name);
            holder.viewDeleteExp = row.findViewById(R.id.viewDeleteExp);
            holder.viewEditExp=row.findViewById(R.id.viewEditExp);
            row.setTag(holder);

        } else {
            holder = (ViewHolder) row.getTag();
        }
        Expense expense = arrayList1.get(position);

        holder.viewExpName.setText(expense.getNameexp());
        holder.viewTransType.setText(expense.getTransexp());

        holder.viewAmount.setText(expense.getAmountexp());
        holder.viewDes.setText(expense.getDescexp());
        holder.Type_Name.setText(expense.getTypeexp());
        holder.viewEditExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setMessage("Are You Sure You Want To Edit This")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(activity, EditExpense.class);
                                String id2 = arrayList1.get(position).getId();
                                intent.putExtra("Exp_del_id", id2);
                                activity.startActivity(intent);
                                activity.finish();

                          /*  Toast.makeText(getApplicationContext(), "you choose yes action for alertbox",
                                    Toast.LENGTH_SHORT).show();*/

                            }

                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();

                          /*  Toast.makeText(getApplicationContext(), "you choose no action for alertbox",
                                    Toast.LENGTH_SHORT).show();*/
                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Action Button");
                alert.dismiss();
                alert.show();
                alert.setCancelable(true);

            }
        });

        holder.viewDeleteExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Setting message manually and performing action on button click
                builder.setMessage("Are You Sure You Want To Delete This")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(activity, DeleteExpense.class);
                                String id2 = arrayList1.get(position).getId();
                                intent.putExtra("Exp_del_id", id2);
                                activity.startActivity(intent);
                                activity.finish();


                          /*  Toast.makeText(getApplicationContext(), "you choose yes action for alertbox",
                                    Toast.LENGTH_SHORT).show();*/
                            }

                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();

                          /*  Toast.makeText(getApplicationContext(), "you choose no action for alertbox",
                                    Toast.LENGTH_SHORT).show();*/
                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Action Button");
                alert.dismiss();
                alert.show();
                alert.setCancelable(true);
            }
        });

        return row;
    }
}
