package com.hw03.group19.hw03;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Trivia extends AppCompatActivity {

    Button btn1;
    Button btn2;
    TextView txt1;
    TextView txt2;
    TextView txt3;
    ImageView iv1;
    ArrayList<Question> getq;
    URL n;
    int index =0;
    ViewGroup viewGroup;
    int scoreC = 0;
    int per = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);



        txt1 = findViewById(R.id.textView);
        txt2 = findViewById(R.id.textView4);
        txt3 = findViewById(R.id.textView6);
        iv1 = findViewById(R.id.imageView2);
        btn1 = findViewById(R.id.button3);
        btn2 = findViewById(R.id.button4);
        final RadioGroup ll = new RadioGroup(this);
        viewGroup = ((ViewGroup) findViewById(R.id.radiogroup));
        final Boolean[] score;

        new CountDownTimer(120000, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                txt2.setText("Time Left: "+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                finish();

            }
        }.start();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btn2.setEnabled(false);
        btn2.setClickable(false);


        getq = (ArrayList<Question>) getIntent().getSerializableExtra(MainActivity.QUESTION);
        final Question dispq = getq.get(index);
        score = new Boolean[getq.size()];
        for(int d=0;d<getq.size();d++)
            score[d] = false;




        txt1.setText("Q" + dispq.queNo);
        txt3.setText("Q" + dispq.que);

        viewGroup.removeAllViews();
        int p = dispq.options.length;
        addRadioButtons(p,dispq.options);


        if (dispq.link == "") {
            iv1.setImageResource(0);
        } else {
            try {
                n = new URL(dispq.link);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            if (isConnectedOnline()) {

                ImageDownloadAsyncTask imageDownloadAsyncTask = (ImageDownloadAsyncTask) new ImageDownloadAsyncTask(n, iv1).execute();
                btn2.setEnabled(true);
                btn2.setClickable(true);

            } else {
                Toast.makeText(Trivia.this, "Internet Not Connected", Toast.LENGTH_SHORT).show();

            }
        }

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup radioButtonGroup = (RadioGroup) findViewById(Integer.parseInt(String.valueOf(1000)));
                int radioButtonID = radioButtonGroup.getCheckedRadioButtonId();
                View radioButton = radioButtonGroup.findViewById(radioButtonID);
                final int idx = radioButtonGroup.indexOfChild(radioButton);
                Question chkq = getq.get(index);
                Log.d("Result",idx+" "+chkq.ans);
                if(Integer.parseInt(chkq.ans) == idx) {
                    Log.d("Result","true");
                    score[index] = true;
                }else {
                    Log.d("Result","false");
                }
                index++;
                //viewGroup.removeAllViews();
                if (index < getq.size() - 2) {
                    getq = (ArrayList<Question>) getIntent().getSerializableExtra(MainActivity.QUESTION);
                    final Question dispq = getq.get(index);

                    txt1.setText("Q" + dispq.queNo);
                    txt3.setText("Q" + dispq.que);
                    int p = dispq.options.length;
                    viewGroup.removeAllViews();
                    addRadioButtons(p,dispq.options);

                    if (dispq.link == "") {
                        iv1.setImageResource(0);
                    } else {
                        try {
                            n = new URL(dispq.link);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        if (isConnectedOnline()) {

                            ImageDownloadAsyncTask imageDownloadAsyncTask = (ImageDownloadAsyncTask) new ImageDownloadAsyncTask(n, iv1).execute();

                        } else {
                            Toast.makeText(Trivia.this, "Internet Not Connected", Toast.LENGTH_SHORT).show();

                        }
                    }

                }
                if(index == getq.size() - 1){

                    for(int d=0;d<getq.size();d++)
                        if(score[d] == true)
                            scoreC++;
                    per = scoreC/score.length*100;
                    Log.d("percentage",scoreC+"");
                    //Intent intent = new Intent(Trivia.this,Stats.class);
                    //putExtraData();
                }
            }

        });


    }
    public void addRadioButtons(int number, String[] choices) {
        for (int row = 0; row < 1; row++) {
            RadioGroup ll = new RadioGroup(Trivia.this);
            ll.setOrientation(LinearLayout.VERTICAL);
            ll.setId(Integer.parseInt(String.valueOf(1000)));
            for (int i = 1; i <= number; i++) {
                RadioButton rdbtn = new RadioButton(this);
                rdbtn.setId((row * 2) + i);
                rdbtn.setText(choices[i-1]);
                Log.d("Choices",choices[number-1]+"");
                ll.addView(rdbtn);
            }
            ViewGroup viewGroup = ((ViewGroup) findViewById(R.id.radiogroup));
            viewGroup.addView(ll);
        }
    }

    private boolean isConnectedOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }

}
