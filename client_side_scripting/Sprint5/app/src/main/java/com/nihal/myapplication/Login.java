package com.nihal.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.R.attr.value;
import static com.nihal.myapplication.MainActivity.MyPREFERENCES;

public class Login extends AppCompatActivity {

    public static final String HOME_URL="http://services.trainees.baabtra.com//BM-41772/Uname.php";
    public static final String KEY_EMAILORPHONE="Email_Phone";

    ImageButton Checkin;
    TextView usrname,loc1,loc2;
    String uname,email;

    SharedPreferences sharedpreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.activity_action);

        View view=getSupportActionBar().getCustomView();
        Toolbar parent =(Toolbar) view.getParent();
        parent.setPadding(0,0,0,0);//for tab otherwise give space in tab
        parent.setContentInsetsAbsolute(0,0);

        loc1=(TextView)findViewById(R.id.tvloc1);
        loc2=(TextView)findViewById(R.id.tvloc2);
        usrname=(TextView)findViewById(R.id.tvuname);



       sharedpreferences=getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);





        Checkin=(ImageButton)findViewById(R.id.chkbtn);
        Checkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(Login.this,Checkin.class);
                startActivity(i);

            }
        });
        Intent intent = getIntent();
        String lc1=intent.getStringExtra("loc");
        loc1.setText(lc1);
        loc2.setText(lc1);
        final String unm=sharedpreferences.getString("Email_Phone","");
        //System.out.println(unm);




        StringRequest strq= new StringRequest(Request.Method.POST, HOME_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                System.out.println(response);
                JSONArray Value= null;
                try {
                    Value = new JSONArray(response);
                    for(int i=0;i<Value.length();i++)
                    {
                        JSONObject o = Value.getJSONObject(i);
                        uname = o.getString("first_name");
                        System.out.println(uname);
                        usrname.setText(uname);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {

                Toast.makeText(Login.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError
        {
            Map<String, String> map = new HashMap<String, String>();



            map.put(KEY_EMAILORPHONE,unm);
            return map;

        }

        };






        FrameLayout Fl1=(FrameLayout)findViewById(R.id.frmlyt);
        if(lc1!=null){
            Fl1.setVisibility(View.VISIBLE);

        }
        RequestQueue req = Volley.newRequestQueue(this);
        req.add(strq);



    }
}
