package com.jbn.facebook;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText et_emailid;
    EditText et_password;
    Button bt_login,reg;
    TextView tv_notf;
    DatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb=new DatabaseHelper(this);
        et_emailid=(EditText)findViewById(R.id.eid);
        et_password=(EditText)findViewById(R.id.pw);
        tv_notf=(TextView)findViewById(R.id.tv);
        reg=(Button)findViewById(R.id.reg);
        bt_login=(Button)findViewById(R.id.log);
       // bt_login.setOnClickListener(new View.OnClickListener() {
          // @Override
           // public void onClick(View view)
            //{
                  //boolean inserted=mydb.insertData(et_emailid.getText().toString(),et_password.getText().toString());
                //if(inserted==true)
                //{
                  //Toast.makeText(getApplicationContext(),"email and password are inserted", Toast.LENGTH_SHORT).show();
               // }


                //}
        //});
        reg.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view)
            {
                Cursor res=mydb.getData();
                if (res.getCount()==0)
                {
                    showData("error", "no data is inserted");
                }
                StringBuffer str=new StringBuffer();
                while (res.moveToNext())
                {
                    str.append("id:"+res.getString(0)+"\n");
                    str.append("email:"+res.getString(1)+"\n");
                    str.append("password:"+res.getString(2)+"\n");
                }
                showData("success",str.toString());


            }

            public void showData(String title, String msg)
            {
                AlertDialog.Builder alt=new AlertDialog.Builder(MainActivity.this);
                alt.setCancelable(true);
                alt.setMessage(msg);
                alt.setTitle(title);
                alt.show();
            }


        });
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String mail=et_emailid.getText().toString();
               String pwd=et_password.getText().toString();
                String stored=mydb.getEntry(mail);
                if (pwd.equals(stored))
                {
                    Intent i=new Intent(MainActivity.this,Secondpage.class);
                    startActivity(i);
                    //Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_LONG).show();
                }
               //else
               // {
                   // tv_notf.setText("Email or Password does not match");
                    //tv_notf.setVisibility(View.VISIBLE);
               // }
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
