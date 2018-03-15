package com.group19_inclass04.inclass04;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

       final Handler handler = new Handler();
       handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(Welcome.this,MainActivity.class);
                 startActivity(i);
            }
        }, 3000);
    }
}
