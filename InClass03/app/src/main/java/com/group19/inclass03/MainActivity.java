package com.group19.inclass03;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String dept;
    int prog;
    static String NAME_KEY = "NAME";
    static String EMAIL_KEY = "EMAIL";
    static String DEPT_KEY = "DEPT";
    static String MOOD_KEY = "MOOD";
    static String STUDENT_KEY = "STUDENTCLASS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText name = findViewById(R.id.name3);
        final EditText email = findViewById(R.id.emailid);
        final RadioGroup rg = findViewById(R.id.rg1);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb =  findViewById(checkedId);
                dept = rb.getText().toString();
                Log.d("demo:",dept);

            }
        });

        SeekBar sg = findViewById(R.id.seekBar);
        sg.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                prog = progress;
                Log.d("demo:",prog + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

         findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String name1 = (name.getText().toString());
                 if(name1.isEmpty()) {
                     Toast toast = Toast.makeText(getApplicationContext(), "Missing Name", Toast.LENGTH_SHORT);
                     toast.show();
                     return;
                 }
                 String email1 = (email.getText().toString());
                 if(email1.isEmpty()) {
                     Toast toast = Toast.makeText(getApplicationContext(), "Missing Email", Toast.LENGTH_SHORT);
                     toast.show();
                     return;
                 }
                 Log.d("demo:",email1 + name1 + dept + prog);

                 Intent intent = new Intent(MainActivity.this, Main2Activity.class);

                 StudentClass StudentClass = new StudentClass(name1, email1, dept, prog);
                 intent.putExtra(STUDENT_KEY, StudentClass );

                 startActivity(intent);
             }
         });
    }
}
