package com.nihal.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.provider.Settings;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.nihal.myapplication.MainActivity.MyPREFERENCES;
import static com.nihal.myapplication.R.id.editText;

public class Status extends AppCompatActivity  {

    public static final String FETCH_URL = "http://services.trainees.baabtra.com//BM-41772/getImage.php";
    public static final String POST_URL = "http://services.trainees.baabtra.com//BM-41772/Status.php";
    public static final String UPLOAD_URL="http://services.trainees.baabtra.com/BM-41772/UploadImgId.php";
    public static final String KEY_Status = "Status";
    public static final String KEY_EMAILORPHONE = "email";



    private static int RESULT_LOAD_IMAGE = 1;

    private Bitmap bitmap;

    private Uri filePath;



    private static final int STORAGE_PERMISSION_CODE=123;

    EditText edtsts;
    TextView post;
    ImageButton glry;
    ImageView imgglry;


    private String Status,sts;


    List<Address> addresses;
    final int maxResult = 5;
    String addressList[] = new String[maxResult];
    Uri selectedImage;


    private ArrayAdapter<String> adapter;

    ImageView ImgvSts;

    ArrayList<String> urlList;
    ListView lv;

    SharedPreferences sharedpreferences;

    String unm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.activity_action_status);
        View view = getSupportActionBar().getCustomView();

        Toolbar parent = (Toolbar) view.getParent();
        parent.setPadding(0, 0, 0, 0);//for tab otherwise give space in tab
        parent.setContentInsetsAbsolute(0, 0);

//        setContentView(R.layout.activity_login);
//        lv = (ListView) findViewById(R.id.imglv);


        sharedpreferences=getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        unm=sharedpreferences.getString("Email_Phone","");
        System.out.println(unm);

        edtsts = (EditText) findViewById(R.id.edtstst);
        post = (TextView) findViewById(R.id.tvpost);
        imgglry=(ImageView)findViewById(R.id.imgVglry);

        glry=(ImageButton)findViewById(R.id.btnGlry);

        glry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
             Intent galaryIntent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);// TO SELECT GALARY
                startActivityForResult(galaryIntent,RESULT_LOAD_IMAGE);


            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                postStatus();

            }
        });



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode== RESULT_LOAD_IMAGE && resultCode==RESULT_OK && null!=data)
        {
            selectedImage=data.getData();
            System.out.println(selectedImage);
            String[] filePathColumn={MediaStore.Images.Media.DATA};

            Cursor cursor=getContentResolver().query(selectedImage,filePathColumn,null,null,null);
            cursor.moveToFirst();

            int columnIndex=cursor.getColumnIndex(filePathColumn[0]);
            final String picturePath=cursor.getString(columnIndex);
            cursor.close();

            setContentView(R.layout.activity_image_status);

            final ImageView img=(ImageView)findViewById(R.id.imgvsts);
            TextView Post=(TextView)findViewById(R.id.tvpost);
            Post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    uploadMultipart();


                    Intent open_displayPage=new Intent(Status.this,Login.class);
                    open_displayPage.putExtra("imagePath", getPath(selectedImage));
                    startActivity(open_displayPage);


//
//                    getImage();


                }
            });







//            Intent intent=new Intent(Status.this,ImageStatus.class);
//            intent.putExtra("Intimg",picturePath);
//            startActivity(intent);


            img.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }

    public void uploadMultipart()
    {
        EditText editText=(EditText)findViewById(R.id.imgpst);
        String name = editText.getText().toString().trim();

        String path = getPath(selectedImage);


        try
        {
            String uploadID= UUID.randomUUID().toString();

            new MultipartUploadRequest(this,uploadID,this.UPLOAD_URL)
                    .addFileToUpload(path,"image")
                    .addParameter("name",name)
                    .addParameter("email",unm)
                    .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(2)
                    .startUpload();
        }
        catch(Exception exc)
        {
            Toast.makeText(this,exc.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
    public String getPath(Uri uri){
        Cursor cursor = getContentResolver().query(uri,null,null,null,null);
        cursor.moveToFirst();
        String document_id=cursor.getString(0);
        document_id=document_id.substring(document_id.lastIndexOf(":")+1);
        cursor.close();

        cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,null,MediaStore.Images.Media._ID+"=?",new String[]{document_id},null);
        cursor.moveToFirst();
        String path=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();
        return path;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void postStatus()

    {

        //sharedpreferences=getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        sts = edtsts.getText().toString();
        //unm=sharedpreferences.getString("email","");


        StringRequest strq = new StringRequest(Request.Method.POST, POST_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)

            {
                if (response.contains("200") && response.contains("Success")) {
                    openHome();

                } else
                {
                    Toast.makeText(Status.this, response, Toast.LENGTH_LONG).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Status.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();

                map.put(KEY_Status,sts);
                map.put(KEY_EMAILORPHONE,unm);

                return map;
            }

        };
        RequestQueue req = Volley.newRequestQueue(Status.this);
        req.add(strq);

    }

    private void openHome() {
        Toast.makeText(Status.this, "posted", Toast.LENGTH_SHORT).show();

        Intent intent=new Intent(this,Login.class);
        intent.putExtra("sts",edtsts.getText().toString());
        startActivity(intent);

    }

//    public void getImage() {
//
//
//        StringRequest strq = new StringRequest(Request.Method.POST, FETCH_URL, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                System.out.println(response);
//                urlList = new ArrayList<>();
//                try
//                {
//                    JSONArray array= new JSONArray(response);
//
//                    for (int i = 0; i < array.length(); i++)
//                    {
//                        JSONObject u = array.getJSONObject(i);
//
//                        for (int j=0;j<u.length();j++)
//                        {
//                            JSONArray array1=u.getJSONArray("url");
//                            String url=array1.getString(j);
//                            urlList.add(url);
//                        }
//                        //Picasso.with(FetchImage.this).load(String.valueOf(urlList)).into();
//                        System.out.println(urlList);
//                    }
//
//                    ImgSAdapter adapter=new ImgSAdapter(getApplicationContext(),urlList);
//                    setContentView(R.layout.activity_login);
//                    lv = (ListView) findViewById(R.id.imglv);
//
//                    ImgvSts=(ImageView)findViewById(R.id.imgvsts);
//                    lv.setAdapter(adapter);
//
//
//
//
//
//
//                }
//                catch (JSONException e)
//                {
//                    e.printStackTrace();
//                }
//
//            }
//
//
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(Status.this, error.toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        RequestQueue req = Volley.newRequestQueue(this);
//        req.add(strq);
//    }


}