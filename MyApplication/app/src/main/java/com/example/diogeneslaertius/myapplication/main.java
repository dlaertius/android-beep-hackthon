package com.example.diogeneslaertius.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.media.ToneGenerator;
import android.media.AudioManager;

public class main extends AppCompatActivity {

    int counter;

    boolean trigger = false;

    long startTime;

    TextView counterPress;

    ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btBegin = findViewById(R.id.btInicio);
        final Button btEnd = findViewById(R.id.btFinaliza);

        counterPress = (TextView) findViewById(R.id.counterPress);
        counterPress.setText("0");

        btBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!trigger) {
                    System.out.println("Debug:- Always is starting pretty good.");
                    counter = 0;
                    counterPress.setText("" + counter);
                    trigger = true;
                    startTime = System.currentTimeMillis();
                }

                System.out.println("Debug:- Counter: " + counter + " trigger: " + trigger);
            }
        });

        btEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Debug:- Counter: " + trigger + "Always ir ending pretty good.");
                counterPress.setText("" + counter);
                trigger = false;
            }
        });


        RelativeLayout rlayout = findViewById(R.id.linearLayout);
        rlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("Debug:- Start Time: " + startTime + " now Time: " + System.currentTimeMillis());

                if (trigger && (System.currentTimeMillis() - startTime) < 5000) {   // Equivalente a segundos.
                    startTime = System.currentTimeMillis();
                    counter++;

                } else if (trigger && (System.currentTimeMillis() - startTime) > 5000) {    // Se ainda for válido digitar e passar para o próximo dígito.

                    toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP,150); // Solta o beep.

                } else {

                    System.out.print("Count: " + counter);

                }

                counterPress.setText("" + counter);
            }
        });



    }
}
