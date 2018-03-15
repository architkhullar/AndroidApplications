package com.hw03.group19.hw03;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements InitialLoadAsyncTask.Inter {

    Button btn1;
    Button btn2;
    TextView txt1;
    ImageView iv1;
    URL url;
    String[] dataL;
    ArrayList<Question> q;
    public static String QUESTION = "QUEST";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);
        txt1 = findViewById(R.id.textView2);
        iv1 = findViewById(R.id.imageView);

        btn2.setEnabled(false);
        btn2.setClickable(false);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if(isConnectedOnline()){
            url = null;
            try {
                url = new URL("http://dev.theappsdr.com/apis/trivia_json/trivia_text.php");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            InitialLoadAsyncTask initialLoadAsyncTask = (InitialLoadAsyncTask) new InitialLoadAsyncTask(url, MainActivity.this).execute();

            btn2.setEnabled(true);
            btn2.setClickable(true);

            iv1.setImageResource(R.drawable.trivia);
            txt1.setText("Trivia Ready");


        } else {
            Toast.makeText(MainActivity.this, "Internet Not Connected", Toast.LENGTH_SHORT).show();
        }

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Trivia.class);
                intent.putExtra(QUESTION,q);
                startActivity(intent);

            }
        });


    }
    private boolean isConnectedOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }

    @Override
    public void getInitialData(String data)  {
        dataL = data.split("\\|");
        q=new ArrayList<Question>();



       for(int i=0;i<dataL.length;i++){
            String[] iarray = dataL[i].split(";");
            //Log.d("Demo",iarray[iarray.length-1]);

            String[] opt= new String[iarray.length-4];
            int k=0;
            for(int j =3;j<(iarray.length -1);j++) {
                opt[k] = iarray[j];
                //Log.d("options",opt[k]);
                k++;
            }

            Question newq = new Question(iarray[0],iarray[1],iarray[2],opt,iarray[iarray.length-1]);
            q.add(newq);

        }


    }
}
