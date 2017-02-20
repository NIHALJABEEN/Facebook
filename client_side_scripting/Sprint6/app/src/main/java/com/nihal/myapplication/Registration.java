package com.nihal.myapplication;

import android.content.Context;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity
{

    public static final String REGISTER_URL="http://services.trainees.baabtra.com//BM-41772/Registration.php";
    public static final String KEY_FIRSTNAME="FirstName";
    public static final String KEY_SURNAME="Surname";
    public static final String KEY_EMAILORPHONE="Email_Phone";
    public static final String KEY_GENDER="Gender";
    public static final String KEY_BDATE="Bdate";
    public static final String KEY_BMONTH="Bmonth";
    public static final String KEY_BYEAR="Byear";
    public static final String KEY_PASSWORD="Password";

    private EditText edtFname;
    private EditText edtSname;
    private EditText edtPhnEml;
    private Spinner  spinner1;
    private EditText edtBdat;
    private EditText edtBmnth;
    private EditText edtByr;
    private EditText edtPswd;
    private Button btnsnp;
    private TextView log;


    private String fname,sname,emlphn,gndr,bdate,bmnth,byr,pswd;
    public String item;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.activity_main_action);
        View view=getSupportActionBar().getCustomView();

        Toolbar parent =(Toolbar) view.getParent();
        parent.setPadding(0,0,0,0);//for tab otherwise give space in tab
        parent.setContentInsetsAbsolute(0,0);


        edtFname=(EditText)findViewById(R.id.edt_fnm);
        edtSname=(EditText)findViewById(R.id.edt_snm);
        edtPhnEml=(EditText)findViewById(R.id.edt_phneml);

        edtBdat=(EditText)findViewById(R.id.edt_bd);
        edtBmnth=(EditText)findViewById(R.id.edt_mn);
        edtByr=(EditText)findViewById(R.id.edt_by);
        edtPswd=(EditText)findViewById(R.id.edt_pwd);
        btnsnp=(Button)findViewById(R.id.btn_sgnup);
        spinner1=(Spinner)findViewById(R.id.spinner);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                item = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        log=(TextView)findViewById(R.id.txtvlog);



        //sharepreferences=getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        btnsnp.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                registerUser();


            }


        });
        log.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i=new Intent(Registration.this,MainActivity.class);
                startActivity(i);
            }
        });

    }
    private void registerUser() {
        fname = edtFname.getText().toString();
        System.out.println(fname);
        sname = edtSname.getText().toString();
        System.out.println(sname);

        emlphn = edtPhnEml.getText().toString();
        System.out.println(emlphn);

        bdate = edtBdat.getText().toString();
        System.out.println(bdate);
        bmnth = edtBmnth.getText().toString();
        System.out.println(bmnth);
        byr = edtByr.getText().toString();
        System.out.println(byr);
        pswd = edtPswd.getText().toString();
        System.out.println(pswd);




        StringRequest streq = new StringRequest(Request.Method.POST, REGISTER_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                if (response.contains("200") && response.contains("Success")) {
                    openLogin();

                } else {
                    Toast.makeText(Registration.this, response, Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(Registration.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();

                map.put(KEY_FIRSTNAME, fname);
                map.put(KEY_SURNAME, sname);
                map.put(KEY_EMAILORPHONE, emlphn);
                map.put(KEY_GENDER, item);
                map.put(KEY_BDATE, bdate);
                map.put(KEY_BMONTH, bmnth);
                map.put(KEY_BYEAR, byr);
                map.put(KEY_PASSWORD, pswd);
                return map;

            }
        };
        RequestQueue req = Volley.newRequestQueue(this);
        req.add(streq);

    }
    private void openLogin(){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }



}
