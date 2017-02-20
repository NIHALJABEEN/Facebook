package com.nihal.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final String REGISTER_URL="http://services.trainees.baabtra.com//BM-41772/login.php";
    public static final String MyPREFERENCES="MyPrefs";
    public static final String KEY_EMAILORPHONE="Email_Phone";
    public static final String KEY_PASSWORD="Password";

    SharedPreferences sharedpreferences;

   private Button b1,login;
   private EditText edteml,edtpswd;

    private String email,pswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.activity_main_action);
        View view=getSupportActionBar().getCustomView();

        Toolbar parent =(Toolbar) view.getParent();
        parent.setPadding(0,0,0,0);//for tab otherwise give space in tab
        parent.setContentInsetsAbsolute(0,0);


        edteml=(EditText)findViewById(R.id.edteml);
        edtpswd=(EditText)findViewById(R.id.edtpswd);

        sharedpreferences=getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        b1=(Button)findViewById(R.id.b1);
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i=new Intent(MainActivity.this,Registration.class);
                startActivity(i);
            }
        });

        login=(Button)findViewById(R.id.btnlgn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                LoginUser();
            }
        });

    }
    private void LoginUser()
    {
        email=edteml.getText().toString();
        System.out.println(email);
        pswd=edtpswd.getText().toString();
        System.out.println(pswd);

        SharedPreferences.Editor editor=sharedpreferences.edit();

        editor.putString("Email_Phone",email);
        editor.commit();


        StringRequest streq = new StringRequest(Request.Method.POST, REGISTER_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                if (response.contains("200") && response.contains("Success")) {
                    openLogin();

                } else {
                    Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String, String> map = new HashMap<String, String>();



                map.put(KEY_EMAILORPHONE, email);
                map.put(KEY_PASSWORD, pswd);
                return map;

            }
        };
        RequestQueue req = Volley.newRequestQueue(this);
        req.add(streq);


    }
    private void openLogin(){
        Intent intent=new Intent(this,Login.class);
        startActivity(intent);
    }
}
