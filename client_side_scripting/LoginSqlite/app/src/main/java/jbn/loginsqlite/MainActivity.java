package jbn.loginsqlite;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button newAcnt,Login;
    EditText email,pasword;
    MySqlLiteHepler mydb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb=new MySqlLiteHepler(MainActivity.this);
        email=(EditText)findViewById(R.id.edtEmail);
        pasword=(EditText)findViewById(R.id.edtPswd);
        Login=(Button)findViewById(R.id.btnLog);


        newAcnt=(Button)findViewById(R.id.btnReg);
        newAcnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Registration.class);
                startActivity(intent);
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_id=email.getText().toString();
                String pswd=pasword.getText().toString();

                String storedPswd=mydb.getPaswd(email_id);
                if (pswd.equals(storedPswd)){

                    Intent intent =new Intent(MainActivity.this,Home.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this,"Login Successfull", Toast.LENGTH_LONG).show();

                }
                else{
                    Toast.makeText(MainActivity.this,"Password or email doesnot exist", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
