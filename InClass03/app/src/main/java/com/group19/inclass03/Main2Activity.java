package com.group19.inclass03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    public static final int REQ_NAME =100;
    public static final int REQ_EMAIL =101;
    public static final int REQ_DEPT =102;
    public static final int REQ_MOOD =103;

    public static final String REQ_NAME1 ="name";
    public static final String REQ_EMAIL1 ="email";
    public static final String REQ_DEPT1 ="dept";
    public static final String REQ_MOOD1 ="mood";

    TextView txt1;
    TextView txt2;
    TextView txt3;
    TextView txt4;

    public static String INT = "Value";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQ_NAME){
            if(resultCode==RESULT_OK){
                String value =data.getExtras().getString(REQ_NAME1);
                txt1.setText(value);
            }
        }else if(requestCode==REQ_EMAIL){
            if(resultCode==RESULT_OK){
                String value =data.getExtras().getString(REQ_EMAIL1);
                txt2.setText(value);
            }
        }else if(requestCode==REQ_DEPT) {
            if (resultCode == RESULT_OK) {
                String value = data.getExtras().getString(REQ_DEPT1);
                txt3.setText(value);
            }
        } else if (requestCode == REQ_MOOD) {
            if (resultCode == RESULT_OK) {
                    String value = data.getExtras().getString(REQ_MOOD1);
                    txt4.setText(value);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

         txt1 = findViewById(R.id.name2);
         txt2 = findViewById(R.id.email2);
         txt3 = findViewById(R.id.dept2);
         txt4 = findViewById(R.id.mood2);


        if(getIntent() != null && getIntent().getExtras() != null){

            Log.d("demo:","here");

            StudentClass studentClass = (StudentClass) getIntent().getExtras().getSerializable(MainActivity.STUDENT_KEY);
            Log.d("demo:",studentClass.toString());

            //String str = (String) studentClass.mood + " % Positive"
            txt1.setText(studentClass.name.toString());
            txt2.setText(studentClass.email.toString());
            txt3.setText(studentClass.dept.toString());
            txt4.setText(studentClass.mood +" % Positive");


        }
        findViewById(R.id.nameedit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent name = new Intent("com.group19.inclass03.intent.action.VIEW");
                name.addCategory(Intent.CATEGORY_DEFAULT);
                name.putExtra(INT,"1");
                startActivityForResult(name,REQ_NAME);
                Log.d("demo","hello");

            }
        });

        findViewById(R.id.emailedit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent name = new Intent("com.group19.inclass03.intent.action.VIEW");
                name.addCategory(Intent.CATEGORY_DEFAULT);
                name.putExtra(INT,"2");
                startActivityForResult(name,REQ_EMAIL);
                Log.d("demo","hello");

            }
        });

        findViewById(R.id.deptedit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent name = new Intent("com.group19.inclass03.intent.action.VIEW");
                name.addCategory(Intent.CATEGORY_DEFAULT);
                name.putExtra(INT,"3");
                startActivityForResult(name,REQ_DEPT);
                Log.d("demo","hello");

            }
        });

        findViewById(R.id.moodedit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent name = new Intent("com.group19.inclass03.intent.action.VIEW");
                name.addCategory(Intent.CATEGORY_DEFAULT);
                name.putExtra(INT,"4");
                startActivityForResult(name,REQ_MOOD);
                Log.d("demo","hello");

            }
        });



    }
}
