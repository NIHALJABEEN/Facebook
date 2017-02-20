package com.nihal.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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

public class StatusCheckin extends AppCompatActivity {


    public static final String POST_URL="http://services.trainees.baabtra.com//BM-41772/Check_in.php";
    public static final String KEY_Address="Address";
    public static final String KEY_Status="Status";
    public String str;

    TextView tvadrs;
    TextView post;
    EditText edtsts;


    private String Status,Adrs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statuscheckin);
        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.activity_action_status);
        View view = getSupportActionBar().getCustomView();

        Toolbar parent = (Toolbar) view.getParent();
        parent.setPadding(0, 0, 0, 0);//for tab otherwise give space in tab
        parent.setContentInsetsAbsolute(0, 0);

        Intent intent = getIntent();
        tvadrs = (TextView) findViewById(R.id.tvadrs);
        str = intent.getStringExtra("Strg");
        tvadrs.setText(str);

        edtsts = (EditText) findViewById(R.id.edtsts);
        post = (TextView) findViewById(R.id.tvpost);


        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postStatus();
            }
        });
    }

            private void postStatus(){


                Status=edtsts.getText().toString();
                Adrs=tvadrs.getText().toString();


                StringRequest streq=new StringRequest(Request.Method.POST,POST_URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {
                        if (response.contains("200") && response.contains("Success"))
                        {
                            openHome();

                        }
                        else
                        {
                            Toast.makeText(StatusCheckin.this, response, Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(StatusCheckin.this, error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }){
                    protected Map<String, String> getParams() throws AuthFailureError
                    {
                        Map<String, String> map = new HashMap<String, String>();

                        map.put(KEY_Address, Adrs);
                        map.put(KEY_Status, Status);

                        return map;
                    }
            };

                RequestQueue req = Volley.newRequestQueue(StatusCheckin.this);
                req.add(streq);
        }

            private void openHome(){
                Intent intent=new Intent(StatusCheckin.this,Login.class);
                intent.putExtra("loc",str);
                startActivity(intent);





    }
}
