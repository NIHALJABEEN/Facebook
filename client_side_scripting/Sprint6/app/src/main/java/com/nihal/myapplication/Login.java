package com.nihal.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.attr.value;
import static com.nihal.myapplication.MainActivity.MyPREFERENCES;
import static com.nihal.myapplication.Status.FETCH_URL;

public class Login extends AppCompatActivity {

    public static final String HOME_URL="http://services.trainees.baabtra.com//BM-41772/Uname.php";
    public static final String KEY_EMAILORPHONE="Email_Phone";

    public static final String FETCH_URL = "http://services.trainees.baabtra.com//BM-41772/getImgId.php";
    public static final String KEY_EMAIL="email";
    public static final String FETCH_STATUS = "http://services.trainees.baabtra.com//BM-41772/getStatus.php";

    ImageButton Checkin;
    ImageButton Status;
    TextView usrname,loc1,loc2,tvsts,stsuname,Imgunm,Proname,TxtVSts;
    String uname,email;

    ImageView ImgvSts;

   ArrayList<String> urlList;
    ArrayList<String>stsList;
    ListView Imglv;
    ListView Stslv;

    ImageView Postimg;
    ImageView cam;





    SharedPreferences sharedpreferences;
    String unm;



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


       Imglv = (ListView) findViewById(R.id.imglv);

        Stslv=(ListView)findViewById(R.id.stsLv);


       urlList = new ArrayList<>();
        ImgvSts=(ImageView)findViewById(R.id.imgvsts);
        TxtVSts=(TextView)findViewById(R.id.TxtVSts);

        stsList=new ArrayList<>();


        tvsts=(TextView)findViewById(R.id.tvstatus);
        stsuname=(TextView)findViewById(R.id.stsuname);

        loc1=(TextView)findViewById(R.id.tvloc1);
        loc2=(TextView)findViewById(R.id.tvloc2);
        usrname=(TextView)findViewById(R.id.tvuname);
        Status=(ImageButton)findViewById(R.id.btnsts);
        Imgunm=(TextView)findViewById(R.id.imgunam);


       Postimg=(ImageView)findViewById(R.id.postimg);

        String path = getIntent().getStringExtra("imagePath");
        Bitmap org_bmp = BitmapFactory.decodeFile(path);
        Postimg.setImageBitmap(org_bmp);

        //final String path = getApplication().getIntent().getStringExtra("imagePath");
        //String name=getActivity().getIntent().getStringExtra("imageText");








        Status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Login.this,Status.class);
                startActivity(i);
            }
        });


        getImage();

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
        String sts=intent.getStringExtra("sts");
        tvsts.setText(sts);


        getStatus();








        loc1.setText(lc1);
            loc2.setText(lc1);
        unm=sharedpreferences.getString("Email_Phone","");
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
                        stsuname.setText(uname);
                        Imgunm.setText(uname);




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






        FrameLayout FlChksts=(FrameLayout)findViewById(R.id.frmlyt);
        if(lc1!=null) {
            FlChksts.setVisibility(View.VISIBLE);
        }
            RelativeLayout FlSts=(RelativeLayout)findViewById(R.id.frmlytsts);
            if (sts!=null){
                FlSts.setVisibility(View.VISIBLE);
            }

        RelativeLayout Lvfrmlyt=(RelativeLayout) findViewById(R.id.lvfrmlyt);
        if(org_bmp!=null){
            Lvfrmlyt.setVisibility(View.VISIBLE);
        }





        RequestQueue req = Volley.newRequestQueue(this);
        req.add(strq);



    }




    //final String eml=sharedpreferences.getString("email","");

    public void getImage() {

        StringRequest strq1 = new StringRequest(Request.Method.POST, FETCH_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);

                try
                {
                    JSONArray array= new JSONArray(response);

                    for (int i = 0; i < array.length(); i++)
                    {
                        JSONObject u = array.getJSONObject(i);

                        JSONArray array1=u.getJSONArray("url");

                        //for (int j=0;j<array1.length();j++)
                        for(int j=array1.length();j>0;j--)
                        {


                            String url=array1.getString(j-1);
                            System.out.println(url);

                            urlList.add(url);
                        }
                        //Picasso.with(FetchImage.this).load(String.valueOf(urlList)).into();
                    }

                    ImgSAdapter adapter=new ImgSAdapter(getApplicationContext(),urlList);
                    Imglv.setAdapter(adapter);






                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }

            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String, String> map = new HashMap<String, String>();



                map.put(KEY_EMAIL,unm);
                return map;

            }

        };
        RequestQueue req = Volley.newRequestQueue(this);
        req.add(strq1);
    }

    public void getStatus()

    {
      StringRequest strq2= new StringRequest(Request.Method.POST, FETCH_STATUS, new Response.Listener<String>() {
          @Override
          public void onResponse(String response)


          {
              System.out.println(response);
              try {
                  JSONArray array = new JSONArray(response);

                  for (int i = 0; i < array.length(); i++) {
                      JSONObject u = array.getJSONObject(i);
                      //System.out.println(u);
                      JSONArray array1 = u.getJSONArray("status");
                     // System.out.println(array1);
                      //for (int j=0;j<array1.length();j++)
                      for (int j = array1.length(); j > 0; j--) {


                          String status = array1.getString(j - 1);
                          System.out.println(status);

                          stsList.add(status);
                      }

                  }

                  StsAdapter adapter = new StsAdapter(getApplicationContext(), stsList);
                  Stslv.setAdapter(adapter);

              } catch (JSONException s) {
                  s.printStackTrace();
              }
          }
      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
              Toast.makeText(Login.this, error.toString(), Toast.LENGTH_SHORT).show();
          }
      })  {
          protected Map<String, String> getParams() throws AuthFailureError
          {
              Map<String, String> map = new HashMap<String, String>();



              map.put(KEY_EMAIL,unm);
              return map;

          }

      };
        RequestQueue req = Volley.newRequestQueue(this);
        req.add(strq2);
    }

}
