package com.example.krokomierz;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private Chronometer timer;
    private long pauseOffset;
    private boolean running = false;

    private TextView textView;
    private double stat = 0;
    private Integer stepCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = findViewById(R.id.time);

    }

    public void changeScreen(View view) {
        Intent intent = new Intent(this, Calory.class);
        startActivity(intent);

    }

    public void goGo(View view)
    {
        if(running == false) {


            timer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            timer.start();
            running = true;


            textView = findViewById(R.id.steps);
            SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
            Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

            SensorEventListener stepDetector = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent sensorEvent) {
                    if (sensorEvent != null) {
                        float x_acceleration = sensorEvent.values[0];
                        float y_acceleration = sensorEvent.values[1];
                        float z_acceleration = sensorEvent.values[2];

                        double Magnitude = Math.sqrt(x_acceleration * x_acceleration + y_acceleration * y_acceleration + z_acceleration * z_acceleration);
                        double MagnitudeDelta = Magnitude - stat;
                        stat = Magnitude;

                        if (MagnitudeDelta > 2) {
                            stepCount++;
                        }
                        textView.setText(stepCount.toString());
                    }
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int i) {
                }
            };

            sensorManager.registerListener(stepDetector, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    public void resetAll(View view) {

        if(running == true) {

            timer.setBase(SystemClock.elapsedRealtime());
            pauseOffset = 0;
            timer.stop();
            stepCount = 0;
            super.onStop();
            running = false;


        }


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