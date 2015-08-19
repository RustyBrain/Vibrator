package com.example.russellplunkett.myapplication;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;




public class MainActivity extends Activity {
    Button vib1;
    Button vib2;
    Button vib3;

    Vibrator mVibrator;

    int dot = 200;
    int dash = 500;
    int short_gap = 200;
    int medium_gap = 500;
    int long_gap = 1000;

    long [] pattern = { 0,
            dot, short_gap, dot, short_gap, dot,
            medium_gap, dash, short_gap, dash,short_gap, dash,
            medium_gap, dot, short_gap, dot, short_gap, dot,
            long_gap};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rect_activity_main);
        vib1 = (Button) findViewById(R.id.btn1);
        vib2 = (Button) findViewById(R.id.btn2);
        vib3 = (Button) findViewById(R.id.btn3);
        mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


        vib1.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v) {
                mVibrator.vibrate(300);
            }
        });

        vib2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mVibrator.vibrate(pattern, -1);
            }
        });

        vib3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Button b = (Button) v;
                if (b.getText().toString().equalsIgnoreCase("ON")){
                    b.setText("OFF");
                    mVibrator.vibrate(new long[]{ 0, 200, 0}, 0);

                } else {
                    b.setText("ON");
                    mVibrator.cancel();
                }

            }
        });
    }

    @Override
    public void onDestroy(){
        if (mVibrator != null)
            mVibrator.cancel();
        super.onDestroy();
    }
}