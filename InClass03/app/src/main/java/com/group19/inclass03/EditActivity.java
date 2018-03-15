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
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    String dept;
    int prog;
    static String NAME_KEY = "NAME";
    static String EMAIL_KEY = "EMAIL";
    static String DEPT_KEY = "DEPT";
    static String MOOD_KEY = "MOOD";
    static String STUDENT_KEY = "STUDENTCLASS";
    String str = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText name = findViewById(R.id.name3);
        final EditText email = findViewById(R.id.emailid);
        final TextView tx1 = findViewById(R.id.textView);
        final TextView tx2 = findViewById(R.id.textView2);
        final RadioGroup rg = findViewById(R.id.rg1);
        final SeekBar sb = findViewById(R.id.seekBar);

        name.setVisibility(View.GONE);
        email.setVisibility(View.GONE);
        rg.setVisibility(View.GONE);
        sb.setVisibility(View.GONE);
        tx1.setVisibility(View.GONE);
        tx2.setVisibility(View.GONE);

        if(getIntent() != null && getIntent().getExtras() != null) {
            Log.d("HGHJG", "HJ" + getIntent().getExtras().get("INT"));
            str = getIntent().getExtras().getString(Main2Activity.INT);

            Log.d("Demo", str);
            switch (Integer.parseInt(str)) {

                case 1:
                    name.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    email.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    tx1.setVisibility(View.VISIBLE);
                    rg.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    tx2.setVisibility(View.VISIBLE);
                    sb.setVisibility(View.VISIBLE);
                    break;

            }
        }

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 switch(Integer.parseInt(str)) {

                     case 1:
                         String value = name.getText().toString();
                         if(value==null  ||  value.length()==0){
                             setResult(RESULT_CANCELED);
                             Toast toast=Toast.makeText(getApplicationContext(), "Missing Name",Toast.LENGTH_SHORT );
                             toast.show();
                             return;
                         }else{
                             Intent intent = new Intent();
                             intent.putExtra(Main2Activity.REQ_NAME1,value);
                             setResult(RESULT_OK,intent);
                         }

                         finish();
                         break;

                     case 2:
                         String value1 = email.getText().toString();
                         if(value1==null  ||  value1.length()==0){
                             setResult(RESULT_CANCELED);
                             Toast toast=Toast.makeText(getApplicationContext(), "Missing Email",Toast.LENGTH_SHORT );
                             toast.show();
                             return;
                         }else{
                             Intent intent = new Intent();
                             intent.putExtra(Main2Activity.REQ_EMAIL1,value1);
                             setResult(RESULT_OK,intent);
                         }

                         finish();
                         break;

                     case 3:
                         int val = rg.getCheckedRadioButtonId();
                         RadioButton rb = findViewById(val);
                         String value2 = rb.getText().toString();
                         if(value2 ==null  ||  value2.length()==0){
                             setResult(RESULT_CANCELED);
                         }else{
                             Intent intent = new Intent();
                             intent.putExtra(Main2Activity.REQ_DEPT1,value2);
                             setResult(RESULT_OK,intent);
                         }

                         finish();
                         break;

                     case 4:
                         int ab = sb.getProgress();
                         Intent intent = new Intent();
                         intent.putExtra(Main2Activity.REQ_MOOD1,ab+"");
                         setResult(RESULT_OK,intent);
                         finish();
                         break;
                 }
             }
         });
    }
}