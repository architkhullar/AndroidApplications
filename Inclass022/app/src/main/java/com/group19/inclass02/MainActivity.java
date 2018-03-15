package com.group19.inclass02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn =  findViewById(R.id.calcutate);

        final EditText edA = findViewById(R.id.years);
        final EditText edW = findViewById(R.id.pounds);
        final EditText edF = findViewById(R.id.feet);
        final EditText edI = findViewById(R.id.inches);

        final TextView txt = findViewById(R.id.result);

        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("demo", String.valueOf(edA.getText()));

                int age = Integer.parseInt(edA.getText().toString());
                Float weight = Float.valueOf(edW.getText().toString());
                int feet = Integer.parseInt(edF.getText().toString());
                int inches = Integer.parseInt(edI.getText().toString());

                int height = (feet * 12) + inches;

                float BMI = 703*(weight/((height)*(height)));

                String message = "";
                String category = "";


                if(BMI < 18.5){

                    category = "Underweight";
                    float newW = (float) (18.5 *((height)*(height))/703);
                    newW = newW - weight;
                    message = "you will need to gain " + newW + " to reach a BMI of 18.5";
                }
                if(BMI >= 18.5 && BMI < 25) {
                    category = "Normal";
                    message = "Keep up the good work !! ";
                }
                if(BMI >= 25 && BMI < 30){
                    category = "Overweight";
                    float newW = (float) (25 *((height)*(height))/703);
                    newW = (weight - newW);
                    message = "you will need to lose " + newW + " to reach a BMI of 25";
                }
                if(BMI >= 30) {
                    category = "Obese";
                    float newW = (float) (25 * ((height) * (height)) / 703);
                    newW = weight - newW;
                    message = "you will need to lose " + newW + " to reach a BMI of 25";
                }
                String res = "Result\n" + "BMI = "+ BMI + " " + category + "\n" + "Normal BMI range: 18.5 - 25" + "\n" + message;

                txt.setText(res);

            }
        });
    }

}
