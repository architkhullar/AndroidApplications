package com.group19_inclass04.inclass04;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    SeekBar sb1;
    SeekBar sb2;
    TextView txt1;
    TextView txt2;
    TextView txt4;
    static int prog1;
    static int prog2;
    Handler handler1;
    Button btn1;
    Button btn2;
    CharSequence ch[];
    ProgressDialog progressDialog;

    ArrayList<String> passwords = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sb1 = findViewById(R.id.seekBar);
        sb2 = findViewById(R.id.seekBar2);
        txt1 = findViewById(R.id.textView);
        txt2 = findViewById(R.id.textView2);
        txt4 = findViewById(R.id.textView4);
        btn1 = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Updating Progress");
        progressDialog.setProgressStyle(progressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);


        txt1.setText(1 + "");
        sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                prog1 = i+1;
                progressDialog.setMax(i+1);
                txt1.setText(prog1 + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Log.d("out", prog1 + "");

        txt2.setText(8 + "");

        prog1=1;
        prog2=8;
        sb2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                prog2 = i+8;
                txt2.setText(prog2 + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Executor tpool = Executors.newFixedThreadPool(2);
                tpool.execute(new calculate(prog1, prog2));
            }
        });


        Log.d("progress", prog1 + "");


        handler1 = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {

                progressDialog.show();
                Log.d("prog", message.what + "");
                progressDialog.setProgress(message.what);

                if (message.what == 0) {
                    progressDialog.dismiss();
                }

                if (message.getData().containsKey("STRING")) {
                    txt4.setText("");
                    final ArrayList<String> passStr = message.getData().getStringArrayList("STRING");
                    final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setCancelable(false);

                    final String[] ch1 = passStr.toArray(new String[passStr.size()]);
                    builder.setTitle("Passwords").setItems(ch1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            txt4.setText(ch1[i]);
                            passwords.clear();
                        }
                    });
                    builder.create().show();


                }
                return true;
            }

        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DoWorkAsync().execute(prog1,prog2);
            }
        });



    }

    public class calculate implements Runnable {

        int count;
        int len;
        public calculate(int prog1, int prog2) {
            this.count = prog1;
            this.len = prog2;
        }

        @Override
        public void run() {
            for (int i = 0; i < prog1; i++) {
                passwords.add(Util.getPassword(prog2));
                Message message1 = new Message();
                message1.what = i + 1;
                handler1.sendMessage(message1);
                Log.d("prog-count", i + "");
            }
            sndMsg(passwords);
        }

        private void sndMsg(ArrayList<String> pass) {
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("STRING", pass);
            Message message = new Message();
            message.setData(bundle);
            handler1.sendMessage(message);

        }

    }


    class DoWorkAsync extends AsyncTask<Integer, Integer, ArrayList<String>> {
        @Override
        protected void onPostExecute(ArrayList<String> passStr) {
            txt4.setText("");
            progressDialog.dismiss();
            final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setCancelable(false);

            final String[] ch1 = passStr.toArray(new String[passStr.size()]);
            builder.setTitle("Passwords").setItems(ch1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    txt4.setText(ch1[i]);
                    passwords.clear();
                }
            });
            builder.create().show();

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressDialog.show();
            progressDialog.setProgress(values[0]);

        }

        @Override
        protected void onPreExecute() {


        }

        @Override
        protected ArrayList<String> doInBackground(Integer... strings) {
            for (int i = 0; i < strings[0]; i++) {
                passwords.add(Util.getPassword(strings[1]));
                publishProgress(i+1);
            }
            return (passwords);
        }
    }

}

