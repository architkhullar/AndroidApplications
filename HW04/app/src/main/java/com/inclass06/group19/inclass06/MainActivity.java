package com.inclass06.group19.inclass06;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements DoWorkAsync.Inter{
    CharSequence[] chr;
    EditText txt;
    String selection;
    Button btn;
    TextView txt1;
    TextView txt2;
    TextView txt3;
    TextView txt4;
    ImageButton ib1;
    ImageButton ib2;
    int index;
    ImageView iv;
    ArrayList<News> data1;
    ProgressBar pb;
    TextView txt5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb =findViewById(R.id.progressBar);
        pb.setVisibility(View.GONE);

        txt =findViewById(R.id.editText);
        txt1 =findViewById(R.id.textView);
        txt2 =findViewById(R.id.textView2);
        txt3 =findViewById(R.id.textView3);
        txt4 =findViewById(R.id.textView4);
        txt5 =findViewById(R.id.textView5);
        txt5.setText("Loading");
        txt5.setVisibility(View.GONE);

        ib1 =findViewById(R.id.imageButton);
        ib2=findViewById(R.id.imageButton3);
        iv=findViewById(R.id.imageView);

        ib1.setEnabled(false);
        ib1.setClickable(false);
        ib2.setEnabled(false);
        ib2.setClickable(false);
        final HashMap<String,String> hashMap = new HashMap<String, String>();

        hashMap.put("Top Stories","http://rss.cnn.com/rss/cnn_topstories.rss");
        hashMap.put("World","http://rss.cnn.com/rss/cnn_world.rss");
        hashMap.put("U.S.","http://rss.cnn.com/rss/cnn_us.rss");
        hashMap.put("Business","http://rss.cnn.com/rss/cnn_us.rss");
        hashMap.put("Politics","http://rss.cnn.com/rss/cnn_allpolitics.rss");
        hashMap.put("Technology","http://rss.cnn.com/rss/cnn_tech.rss");
        hashMap.put("Health","http://rss.cnn.com/rss/cnn_health.rss");
        hashMap.put("Entertainment","http://rss.cnn.com/rss/cnn_showbiz.rss");
        hashMap.put("Travel","http://rss.cnn.com/rss/cnn_travel.rss");
        hashMap.put("Living","http://rss.cnn.com/rss/cnn_living.rss");
        hashMap.put("Most Recent","http://rss.cnn.com/rss/cnn_latest.rss");



        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chr = new CharSequence[]{"Top Stories", "World", "U.S.", "Business", "Politics", "Technology", "Health","Entertainment","Travel","Living","Most Recent"};
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(false);


                builder.setTitle("Choose a Keyword").setItems(chr, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        txt.setText(chr[i]);
                        selection = (String) chr[i];

                        if (isConnectedOnline()) {
                            URL n = null;
                            String base = hashMap.get(selection);
                            Log.d("link",base);
                            //base = base+"&category="+selection;

                            try {
                                n = new URL(base);
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            }

                            //Toast.makeText(MainActivity.this, "Internet Connected", Toast.LENGTH_SHORT).show();
                            index=0;
                            DoWorkAsync o = (DoWorkAsync) new DoWorkAsync(n,pb,txt5, MainActivity.this).execute();
                            //Log.d("demo",tasks);

                        } else {
                            Toast.makeText(MainActivity.this, "Internet Not Connected", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.create().show();



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
    public void getProperty(final ArrayList<News> data) {
        Log.d("News",data.get(0).toString());
        final ArrayList<News> data1 = data;
        if(data.size()==0) {
            Toast.makeText(MainActivity.this, "NO NEWS FOUND", Toast.LENGTH_SHORT).show();
            txt1.setText("");
            txt2.setText("");
            txt3.setText("");
            txt4.setText("");
            iv.setImageResource(0);

        }
        if(data.size()>1) {
            txt1.setText(data1.get(0).title);
            txt2.setText(data1.get(0).publishedat);
            //if(data1.get(index).Desc == null)
              //  txt3.setVisibility(View.GONE);
            //else
                txt3.setText(data1.get(0).Desc);
            txt4.setText((index+1)+ " of "+data1.size());
            Log.d("check",data1.get(0).url);
            if(isConnectedOnline())
                if(data1.get(index).url.contains("http"))
                    Picasso.with(this).load(data1.get(0).url).into(iv);
                else {
                    data1.get(index).url = "http://" + data1.get(index).url;
                    Picasso.with(MainActivity.this).load(data1.get(index).url).into(iv);
                }
            else
                Toast.makeText(MainActivity.this, "Internet Not Connected", Toast.LENGTH_SHORT).show();
            index++;
            ib1.setEnabled(true);
            ib1.setClickable(true);
            ib2.setEnabled(true);
            ib2.setClickable(true);

        }
        ib2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index--;
                if (index == -1)
                    index = (data1.size() - 1);
                if (index >= 0) {
                    txt1.setText(data1.get(index).title);
                    txt2.setText(data1.get(index).publishedat);
                   // if(data1.get(index).Desc == null)
                     //   txt3.setVisibility(View.GONE);
                    //else
                        txt3.setText(data1.get(index).Desc);
                    txt4.setText(index+1+" of "+data1.size());
                    Log.d("check",data1.get(index).url);
                    if(isConnectedOnline())
                        if(data1.get(index).url.contains("http"))
                            Picasso.with(MainActivity.this).load(data1.get(index).url).into(iv);
                        else {
                            data1.get(index).url = "http://" + data1.get(index).url;
                            Picasso.with(MainActivity.this).load(data1.get(index).url).into(iv);
                        }
                    else
                        Toast.makeText(MainActivity.this, "Internet Not Connected", Toast.LENGTH_SHORT).show();

                }
            }
        });
        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index++;
                if(index== data1.size())
                    index=0;
                if (index < data1.size()) {
                    txt1.setText(data1.get(index).title);
                    txt2.setText(data1.get(index).publishedat);
                    //if(data1.get(index).Desc == null)
                      //  txt3.setVisibility(View.GONE);
                    //else
                        txt3.setText(data1.get(index).Desc);
                    txt4.setText(index+1+ " of "+data1.size());
                    Log.d("check",data1.get(index).url);
                    if(isConnectedOnline())
                        if(data1.get(index).url.contains("http"))
                            Picasso.with(MainActivity.this).load(data1.get(index).url).into(iv);
                        else {
                            data1.get(index).url = "http://" + data1.get(index).url;
                            Picasso.with(MainActivity.this).load(data1.get(index).url).into(iv);
                        }
                    else
                        Toast.makeText(MainActivity.this, "Internet Not Connected", Toast.LENGTH_SHORT).show();


                }


            }
        });

    }
}
