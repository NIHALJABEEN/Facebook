package jbn.loginsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {
    EditText regEmail,regPass;
    Button SgnUp;
    MySqlLiteHepler mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        regEmail=(EditText)findViewById(R.id.edtRegEmail);
        regPass=(EditText)findViewById(R.id.edtRegPass);
        SgnUp=(Button)findViewById(R.id.btnSgnUp);
        mydb=new MySqlLiteHepler(Registration.this);

        SgnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=regEmail.getText().toString();
                String pswd=regPass.getText().toString();
                mydb.InsertData(email,pswd);

                    Toast.makeText(getApplicationContext(), "Account created", Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(Registration.this,MainActivity.class);
                    startActivity(intent);



            }
        });

    }
}
