package com.group19_homework1.calcutatetip;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import static com.group19_homework1.calcutatetip.R.*;

public class TIpCalculator extends AppCompatActivity {

    float amt = (float) 0.00;
    final String regex = "[0-9]+";
    CharSequence ch = "new";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_tip_calculator);
        setTitle("Tip Calculator");

        final EditText txt1 = findViewById(id.editText);
        final TextView tip = findViewById(id.textView6);
        final TextView total = findViewById(id.textView7);
        final TextView cust = findViewById(id.textView8);
        final SeekBar sb = findViewById(id.seekBar);
        final Button btn= findViewById(id.button);

       sb.setEnabled(false);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        final RadioGroup rg = findViewById(id.radioGroup);
        txt1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d("demo", "before" + charSequence + i + i1 + i2);
            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                ch = charSequence;
                Log.d("demo", "on" + charSequence + "");

                RadioGroup rg = findViewById(R.id.radioGroup);
                int sel = rg.getCheckedRadioButtonId();
                RadioButton x1=(RadioButton) findViewById(sel);
                if (charSequence.length()!=0 && charSequence.toString().matches(regex)) {
                    if (!x1.getText().toString().equals("Custom")) {
                        String Val = x1.getText().toString();
                        Log.d("demo", "on" + charSequence + "inside default");

                        float amt = Integer.parseInt(charSequence.toString());
                        Log.d("demo", amt + "");
                        String P = Val.replace("%", "");
                        float x = (float) Integer.parseInt(P.toString());
                        float cal = (float) ((x / 100) * amt);
                        String a = cal + "";
                        tip.setText(a);
                        float t = (float) (((x / 100) + 1) * amt);
                        String b = t + "";
                        total.setText(b);

                    } else {

                        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            @Override
                            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                                float y = i;
                                float amt = Integer.parseInt(ch.toString());
                                float cal =  ((y/ 100) * amt);
                                String r = cal + "";
                                tip.setText(r);
                                float t = (float) (((y / 100) + 1) * amt);
                                String s = t + "";
                                total.setText(s);
                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {

                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {

                            }


                        });

                    }

                }else{
                    txt1.setError("Enter Bill Total");
                    tip.setText("0.00");
                    total.setText("0.00");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }


        });



        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(RadioGroup radioGroup, int i) {


               if (txt1.length()!=0 && txt1.getText().toString().matches(regex)) {


                    amt = (float) Integer.parseInt(txt1.getText().toString());
                    if (amt >= 0) {
                        RadioButton rb = findViewById(i);
                        final String p = rb.getText().toString();


                        if (!p.equals("Custom")) {
                            sb.setEnabled(false);

                            String P = p.replace("%", "");
                            float x = (float) Integer.parseInt(P.toString());
                            float cal = ((x / 100) * amt);

                            String a = cal + "";
                            tip.setText(a);
                            float t = (float) ((1 + (x / 100)) * amt);
                            String b = t + "";
                            total.setText(b);
                        } else if (p.equals("Custom")) {
                            float x = (float) 25.00;
                            float cal = ((x / 100) * amt);
                            Log.d("demo", amt + "");
                            String a = cal + "";
                            tip.setText(a);
                            float t = ((1 + (x / 100)) * amt);
                            String b = t + "";
                            total.setText(b);
                            if (p.equals("Custom")) {
                                sb.setEnabled(true);
                                sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                    @Override
                                    public void onProgressChanged(SeekBar seekBar, int j, boolean b) {
                                        float y = (float) j;
                                        cust.setText(j + "%");
                                        float cal = ((y / 100) * amt);

                                        String aa = cal + "";

                                        if (p.equals("Custom")) {
                                            tip.setText(aa);
                                            float t = ((1 + (y / 100)) * amt);
                                            String bb = t + "";
                                            total.setText(bb);
                                        }


                                    }


                                    @Override
                                    public void onStartTrackingTouch(SeekBar seekBar) {

                                    }

                                    @Override
                                    public void onStopTrackingTouch(SeekBar seekBar) {


                                    }
                                });
                            }


                        }
                    }
                }
               else{
                   txt1.setError("Enter Bill total");
               }


            }
        });
    }
}
