package soft.gen.expensemanager.SQLiteHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import soft.gen.expensemanager.Expense;
import soft.gen.expensemanager.Type;

public class SQLiteDataBase extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "EXPENSE DETAILS";
    public static final String TABLE_NAME_REGISTRATION = "REGISTRATION";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String MOBILE = "mobile";
    public static final String PASSWORD = "password";
    public static final String DATE = "date";

    String REGISTRATION = "CREATE TABLE " + TABLE_NAME_REGISTRATION + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + NAME + " TEXT," + EMAIL + " TEXT ," + MOBILE + " TEXT," + PASSWORD + " TEXT," + DATE + " TEXT" + ")";

    //////// Add Expense Details Database ///////
    public static final String TABLE_NAME_EXPENSE = "EXPENSE";
    public static final String ID_EXP = "id";
    public static final String EXPENSE_NAME = "expensename";
    public static final String TRANSACTION_TYPE = "transactiontype";
    public static final String AMOUNT = "amount";
    public static final String EXPENSE_TYPE = "expensetype";
    public static final String EXPENSE_DATE = "expensedate";
    public static final String DESCRIPTION = "description";

    String EXPENSE = "CREATE TABLE " + TABLE_NAME_EXPENSE + "(" + ID_EXP + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + EXPENSE_NAME + " TEXT," + TRANSACTION_TYPE + " TEXT," + AMOUNT + " TEXT," + EXPENSE_TYPE + " TEXT,"
            + EXPENSE_DATE + " TEXT," + DESCRIPTION + " TEXT" + ")";


    /////////Expense Type DataBase////////////
    public static final String TABLE_NAME_TYPE = "EXPENSETYPE";
    public static final String ID_TYPE = "id";
    public static final String NAME_TYPE = "name";
    public static final String CREATED_DATE = "date_created";
    public static final String STATUS = "status";
    public static final String TYPE_AMOUNT = "amount";


    String TYPE = "CREATE TABLE " + TABLE_NAME_TYPE + "(" + ID_TYPE + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + NAME_TYPE + " TEXT," + CREATED_DATE + " TEXT," + STATUS + " TEXT," + TYPE_AMOUNT + "TEXT" + ")";


    public SQLiteDataBase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(REGISTRATION);
        sqLiteDatabase.execSQL(EXPENSE);
        sqLiteDatabase.execSQL(TYPE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_REGISTRATION);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_EXPENSE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_TYPE);
        onCreate(sqLiteDatabase);

    }

    public ArrayList<Expense> getExpenseData() {
        ArrayList<Expense> arrayList =new ArrayList<>();
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM " + TABLE_NAME_EXPENSE,null);

        while (cursor.moveToNext()){
            String id=cursor.getString(0);
            String exp_name=cursor.getString(1);
            String exp_trans=cursor.getString(2);
            String exp_amount=cursor.getString(3);
            String exp_type=cursor.getString(4);
            String exp_date=cursor.getString(5);
            String exp_desc=cursor.getString(6);
            Expense expense=new Expense(id,exp_name,exp_trans,exp_amount,exp_type,exp_date,exp_desc);
            arrayList.add(expense);
        }
        return arrayList;
    }

    public  ArrayList<Type> getTypeData(){
        ArrayList<Type> arrayList2= new ArrayList<>();
        SQLiteDatabase db2=this.getWritableDatabase();
        Cursor cursor2=db2.rawQuery("SELECT * FROM " + TABLE_NAME_TYPE,null);
        while (cursor2.moveToNext()){
            String id2=cursor2.getString(0);
            String name_type=cursor2.getString(1);
            String status_type=cursor2.getString(2);
            String date_type=cursor2.getString(3);

            Type type=new Type(id2 , name_type , status_type , date_type );
            arrayList2.add(type);
        }
        return arrayList2;
    }

    public boolean updateData(String id, String edExpName, String edExpamount, String edDesc, String edSpin){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLiteDataBase.EXPENSE_NAME, edExpName);
        contentValues.put(SQLiteDataBase.AMOUNT,edExpamount);
        contentValues.put(SQLiteDataBase.DESCRIPTION,edDesc);
        contentValues.put(SQLiteDataBase.TRANSACTION_TYPE,edSpin);
        db.update(TABLE_NAME_EXPENSE,contentValues,"id = ?" ,new String[] { id });
        return true;
    }

    public boolean updateDataTYPE(String id, String name){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues2= new ContentValues();
        contentValues2.put(SQLiteDataBase.NAME_TYPE,name);
        db.update(TABLE_NAME_TYPE,contentValues2,"id = ?", new String[]{ id });
        return true;
    }

    public Integer deleteExpense(String id){
        SQLiteDatabase db=this.getWritableDatabase();
       return db.delete(TABLE_NAME_EXPENSE,"id = ?", new String[]{ id });
    }

    public Integer deleteTYPE(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME_TYPE,"id = ?", new String[] { id });
    }

    public boolean insertData(String name, String trans_type,String date_date, String type_type, String amount, String desc) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLiteDataBase.EXPENSE_NAME, name);
        contentValues.put(SQLiteDataBase.TRANSACTION_TYPE, trans_type);
        contentValues.put(SQLiteDataBase.EXPENSE_TYPE, type_type);
        contentValues.put(SQLiteDataBase.EXPENSE_DATE, date_date);
        contentValues.put(SQLiteDataBase.AMOUNT, amount);
        contentValues.put(SQLiteDataBase.DESCRIPTION, desc);
        db.insert(SQLiteDataBase.TABLE_NAME_EXPENSE, null, contentValues);
        return true;
    }

}
