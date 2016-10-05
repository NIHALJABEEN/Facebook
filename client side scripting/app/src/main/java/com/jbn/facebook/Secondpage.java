package com.jbn.facebook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Secondpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondpage);
        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.activity_action);
        View view=getSupportActionBar().getCustomView();
    }


}
