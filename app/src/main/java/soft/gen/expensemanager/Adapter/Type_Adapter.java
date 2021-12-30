package soft.gen.expensemanager.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import soft.gen.expensemanager.R;
import soft.gen.expensemanager.Type;
import soft.gen.expensemanager.UpdateEdit.DeleteExpense;
import soft.gen.expensemanager.UpdateEdit.DeleteType;
import soft.gen.expensemanager.UpdateEdit.EditExpense;
import soft.gen.expensemanager.UpdateEdit.EditType;
import soft.gen.expensemanager.ViewExpenses.ViewExpType;

public class Type_Adapter extends BaseAdapter {
    Context context;
    ArrayList<Type> arrayList2;
    private static Activity activity;
    AlertDialog.Builder builder;

    public Type_Adapter(Context context, ArrayList<Type> arrayList2,Activity aactivity){
        this.context=context;
        this.arrayList2=arrayList2;
        this.activity = aactivity;
    }

    @Override
    public int getCount() {
        return arrayList2.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList2.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder{
        String id;
        TextView view_exp_type_name,view_exp_type_status,view_exp_type_id;
        ImageView exp_type_delete,exp_type_edit;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {

        View row=convertView;
        ViewHolder holder=new ViewHolder();
        builder = new AlertDialog.Builder(context);

        if(row==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(R.layout.activity_view_exp_type,null);

            holder.view_exp_type_name=row.findViewById(R.id.view_exp_type_name);
            holder.view_exp_type_status=row.findViewById(R.id.view_exp_type_status);
            holder.view_exp_type_id=row.findViewById(R.id.view_exp_type_id);
            holder.exp_type_delete=row.findViewById(R.id.exp_type_delete);
            holder.exp_type_edit=row.findViewById(R.id.exp_type_edit);
            row.setTag(holder);

        }else {
            holder = (ViewHolder) row.getTag();
        }

        Type type=arrayList2.get(position);

        holder.view_exp_type_name.setText(type.getName_type());
        holder.view_exp_type_status.setText(type.getStatus_type());
        holder.view_exp_type_id.setText(String.valueOf(position+1));
        holder.exp_type_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setMessage("Are You Sure You Want To Edit This")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(activity, EditType.class);
                                String id2=arrayList2.get(position).getId2();
                                intent.putExtra("typeID",id2);
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
                alert.setTitle("Warning!");
                alert.show();
                alert.setCancelable(true);


            }
        });

        holder.exp_type_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Setting message manually and performing action on button click
                builder.setMessage("Are You Sure You Want To Delete This")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(activity, DeleteType.class);
                                String id2=arrayList2.get(position).getId2();
                                intent.putExtra("typeID",id2);

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
                alert.setTitle("Warning!");
                alert.show();
                alert.setCancelable(true);


            }
        });
        return row;
    }
}
