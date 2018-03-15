package com.group19_homework2.taskmanager;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class ViewTask extends AppCompatActivity implements Comparable<Task> {


    final public static int REQ_CODE = 100;
    public static final String TASK = "task";
    LinkedList<Task> taskll = new LinkedList<Task>();
    TextView txt1;
    TextView txt2;
    TextView txt3;
    TextView txt4;
    TextView txt5;
    ImageButton del1;
    ImageButton next;
    ImageButton prev;
    ImageButton last;
    ImageButton first;
    ImageButton edit;

    int index = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);
        //set title and app logo
        setTitle("View Tasks");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.task);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        txt1 = findViewById(R.id.textView);
        txt2 = findViewById(R.id.textView2);
        txt3 = findViewById(R.id.textView3);
        txt4 = findViewById(R.id.textView4);
        txt5 = findViewById(R.id.textView5);
        del1 = findViewById(R.id.imageButton3);
        next=findViewById(R.id.imageButton6);
        prev=findViewById(R.id.imageButton5);
        first=findViewById(R.id.imageButton9);
        last=findViewById(R.id.imageButton7);
        edit=findViewById(R.id.imageButton2);

        //add new task
        ImageButton addTask = findViewById(R.id.imageButton);
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewTask.this, CreateTask.class);
                startActivityForResult(intent, REQ_CODE);

            }
        });

        //delete a task on click of delete icon
        //set current task to previous task, update task list and indexes
        del1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (taskll.size() <=1) {
                    if(taskll.size()==1){
                       taskll.remove(0);
                    }
                    txt1.setText("Task Title");
                    txt2.setText("Task Date");
                    txt3.setText("Task Time");
                    txt4.setText("Task Priority");
                    txt5.setText("NO TASKS");
                    index=0;
                } else {
                    taskll.remove(index-1 );
                    Task tr = taskll.get(0);
                    txt1.setText(tr.title);
                    txt2.setText(tr.date);
                    txt3.setText(tr.time);
                    txt4.setText(tr.priority);
                    index=1;
                    txt5.setText("Task 1 of " + taskll.size());

                }

            }
        });

        //view next task in list
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index++;
                if (taskll.size() >= index) {
                    Log.d("next",index+"");
                    Task tr = taskll.get(index-1);
                    txt1.setText(tr.title);
                    txt2.setText(tr.date);
                    txt3.setText(tr.time);
                    txt4.setText(tr.priority);
                    txt5.setText("Task " + (index) + " of " + taskll.size());
                } else {
                    index--;
                    Log.d("next",index+"");
                    Toast toast = Toast.makeText(getApplicationContext(), "Current Task is Last Task", Toast.LENGTH_SHORT);
                    toast.show();
                    return;

                }
            }
        });
        //view prev task on list
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index--;
                if (index >= 1) {
                    Log.d("next",index+"");
                    Task tr = taskll.get(index-1);
                    txt1.setText(tr.title);
                    txt2.setText(tr.date);
                    txt3.setText(tr.time);
                    txt4.setText(tr.priority);
                    txt5.setText("Task " + index + " of " + taskll.size());
                } else {
                    index++;
                    Log.d("next",index+"");
                    Toast toast = Toast.makeText(getApplicationContext(), "Current Task is First Task", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
            }
        });

        //view the last task on list
        last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (taskll.size() > 1 && index!=taskll.size()) {
                    index=taskll.size();
                    Task tr = taskll.getLast();
                    txt1.setText(tr.title);
                    txt2.setText(tr.date);
                    txt3.setText(tr.time);
                    txt4.setText(tr.priority);
                    txt5.setText("Task " + index + " of " + taskll.size());
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Current Task is Last Task", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
            }
        });
        //view the first task onlist
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (taskll.size() > 0 && index!=1) {
                    index=1;
                    Task tr = taskll.getFirst();
                    txt1.setText(tr.title);
                    txt2.setText(tr.date);
                    txt3.setText(tr.time);
                    txt4.setText(tr.priority);
                    txt5.setText("Task " + index + " of " + taskll.size());
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Current Task is first Task", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

            }
        });
        //edit a task
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(taskll.size()>0) {
                    Task send = taskll.get(index - 1); // assuming we are editing the record number at the index

                    Intent ie = new Intent(ViewTask.this, EditActivity.class);
                    ie.putExtra("EDIT", send);
                    startActivityForResult(ie, REQ_CODE);

                    taskll.remove(index - 1);
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Nothing to edit", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
            }
        });



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_CODE) {

            if (resultCode == RESULT_OK) {
                Task t = (Task) data.getExtras().getSerializable(TASK);

                taskll.add(t);


                //sort all tasks
                Collections.sort(taskll, new Comparator<Task>() {
                    public int compare(Task o1, Task o2) {
                        Log.d("sort","sort called");
                        return o1.date_time.compareTo(o2.date_time);
                    }
                });

                for (int i = 0; i < taskll.size(); i++) {
                    Task temp=taskll.get(i);
                    Log.d("iter",temp.date_time+"");

                }

                int pos=taskll.indexOf(t);
                Log.d("pos",pos+"");

                Task t1 = taskll.get(pos);
                txt1.setText(t1.title);
                txt2.setText(t1.date);
                txt3.setText(t1.time);

                txt4.setText(t1.priority);
                txt5.setText("Task "+(pos+1)+ " of " + taskll.size());
                index=pos+1;


            } else {
                Log.d("demo", "cancelled");
            }
        }

    }


    @Override
    public int compareTo(@NonNull Task task) {
        return task.getdate().compareTo(task.getdate());
    }


}
