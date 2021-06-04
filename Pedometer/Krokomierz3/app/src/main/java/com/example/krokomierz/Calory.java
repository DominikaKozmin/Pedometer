package com.example.krokomierz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Calory extends AppCompatActivity {

    private EditText step, result;
    private Button button;
    int number;
    double stat = 0.05, results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calory);

        button =  findViewById(R.id.calc);
        step = findViewById(R.id.stepss);
        result = findViewById(R.id.resultt);
    }

    public void clickDone(View view) {
        int one = Integer.parseInt(step.getText().toString());

        results = one * stat;
        int two = (int) results;

        result.setText(String.valueOf(two));



    }


    protected void onPause() {
        super.onPause();


    }

    protected void onStop() {
        super.onStop();

    }

    protected void onResume() {
        super.onResume();

    }
}