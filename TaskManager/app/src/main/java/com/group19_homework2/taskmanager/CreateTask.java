package com.group19_homework2.taskmanager;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

public class CreateTask extends AppCompatActivity {

    String priority1;
    int id;
    public static final String TASK = "task1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);
        //set title and icon
        setTitle("Create Task");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.task);
        getSupportActionBar().setDisplayUseLogoEnabled(true);


        final EditText txt1 = findViewById(R.id.editText);
        final EditText txt2 = findViewById(R.id.editText2);
        final EditText txt3 = findViewById(R.id.editText3);
        txt2.setKeyListener(null);
        txt3.setKeyListener(null);
        txt2.setClickable(true);
        txt3.setClickable(true);
        txt2.setFocusable(false);
        txt3.setFocusable(false);

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        Log.d("current",hour+"");
        final StringBuilder dateF = new StringBuilder();
        final StringBuilder timeF = new StringBuilder();

        final Date date_time_pass;

        final DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                dateF.append(i1 + 1).append("/").append(i2).append("/").append(i).append(" ");

                txt2.setText(new StringBuilder().append(i1 + 1).append("/").append(i2).append("/").append(i));
            }
        }, year, month, day);

        findViewById(R.id.editText2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }


        });


        final TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                String a = "AM";
                int currentHour;
                if (i > 11) {
                    a = "PM";
                }
                if (i > 11) {
                    currentHour = i - 12;
                } else {
                    currentHour = i;
                }
                timeF.append(String.format("%02d", i)).append(":").append(String.format("%02d", i1));


                txt3.setText(new StringBuilder().append(String.format("%02d", currentHour)).append(":").
                        append(String.format("%02d", i1)).append(" ").append(a));

            }
        }, hour, minute, true);


        findViewById(R.id.editText3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        });

        RadioGroup rg = findViewById(R.id.radioGroup);
        rg.check(findViewById(R.id.radioButton2).getId());
        RadioButton rb2= findViewById(R.id.radioButton2);
        priority1=rb2.getText().toString();
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rb = findViewById(i);
                priority1 = rb.getText().toString();
                id=i;
                Log.d("demo", rb.getText().toString() + "");

            }
        });


        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String tittle = "title";
                String date = "Date";
                String time = "time";


                if (txt1.getText().toString().equals("")) {
                    txt1.requestFocus();
                    txt1.setError("Enter Valid Title");
                    return;
                } else {
                    tittle = txt1.getText().toString();
                }
                if (txt2.getText().toString().equals("")) {
                    txt2.requestFocus();
                    txt2.setError("Enter Valid Date");
                    return;
                } else {
                    date = txt2.getText().toString();
                }
                if (txt3.getText().toString().equals("")) {

                    txt3.requestFocus();
                    txt3.setError("Enter Valid Time");
                    return;
                } else {
                    time = txt3.getText().toString();
                }

                Date date_time_pass=Calendar.getInstance().getTime();
                String date_time=dateF.toString()+timeF.toString();
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm");
                try {
                     date_time_pass = format.parse(String.valueOf(date_time));
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                if (!txt1.getText().toString().isEmpty() && !txt2.getText().toString().isEmpty() && !txt3.getText().toString().isEmpty()) {

                    Task task = new Task(tittle, date, time, priority1,date_time_pass,dateF.toString(),timeF.toString(),id);
                    Intent i = new Intent();
                    i.putExtra(ViewTask.TASK, task);
                    setResult(RESULT_OK,i);
                }
                else{
                    setResult(RESULT_CANCELED);
                }
                finish();


            }
        });

    }

}
