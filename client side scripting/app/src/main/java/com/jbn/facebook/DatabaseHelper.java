package com.jbn.facebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by shameer on 8/25/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper
{
    public DatabaseHelper(Context context)
    {
        super(context, "FbDB", null, 1);
    }




    @Override
    public void onCreate(SQLiteDatabase db)
    {
        try
        {
            db.execSQL("create table tbl_fb(id integer primary key autoincrement,email text,password text)");
        }
        catch (SQLiteException e)
        {
            Log.d("error in creating table", e.getLocalizedMessage().toString());
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {

        db.execSQL("drop table if exists tbl_fb ");
        onCreate(db);

    }
    public boolean insertData(String email,String password)
    {
        long result=-1;
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues mycontent=new ContentValues();
        mycontent.put("email",email);
        mycontent.put("password",password);
        try
        {
            result=db.insert("tbl_fb",null,mycontent);
        }
        catch (SQLiteException e)
        {
            Log.d("message",e.getLocalizedMessage().toString());
        }
        if (result==-1)
        {
            return false;
        }
        else
            return true;

    }
    public Cursor getData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from tbl_fb",null);
        return res;
    }
    public String getEntry(String mail)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.query("tbl_fb",null,"email=?",new String[]{mail},null,null,null);
        if (res.getCount()<1)
        {
            res.close();
            return "Not Exists";
        }
        res.moveToFirst();
        String pwd=res.getString(res.getColumnIndex("password"));
        res.close();
        return pwd;

    }

}
