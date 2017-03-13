package jbn.loginsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Nihal on 09-03-2017.
 */

public class MySqlLiteHepler extends SQLiteOpenHelper {
    public MySqlLiteHepler(Context context) {
        super(context, "StudentDb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table tbl_Reg(id integer primary key autoincrement,email text,password text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists tbl_Reg");
        onCreate(db);


    }

    public void InsertData(String email_id,String pswd)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues mycontent=new ContentValues();
        mycontent.put("email",email_id);
        mycontent.put("password",pswd);

        db.insert("tbl_Reg",null,mycontent);



    }


    public String getPaswd(String email_id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor =db.query("tbl_Reg",null,"email=?",new String[]{email_id},null,null,null);
        if (cursor.getCount()<1)
        {
            cursor.close();
            return "Does not exists";

        }
        cursor.moveToFirst();
        String password=cursor.getString(cursor.getColumnIndex("password"));
        cursor.close();
        return password;


    }

}
