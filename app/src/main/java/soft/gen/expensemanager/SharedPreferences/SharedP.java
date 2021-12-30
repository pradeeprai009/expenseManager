package soft.gen.expensemanager.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedP {

    Context context;
    public SharedP(Context context){

        this.context=context;

    }
    public void saveLoginDetails(String mpin){
        SharedPreferences sharedPreferences=context.getSharedPreferences("LoginDetails",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putString("mpin", mpin);
        editor.commit();

    }
    public void  saveAmount (String amount ){
        SharedPreferences sharedPreferences=context.getSharedPreferences("Amount",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putString("amount", amount);
        editor.commit();


    }
    public String getMpin()
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("LoginDetails",Context.MODE_PRIVATE);
        return sharedPreferences.getString("mpin","");

    }
    public String getAmount(){
        SharedPreferences sharedPreferences=context.getSharedPreferences("LoginDetails",Context.MODE_PRIVATE);
        return  sharedPreferences.getString("amount","");
    }


}
