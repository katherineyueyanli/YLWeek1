package com.example.ylweek1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ClickyActivity extends AppCompatActivity {
    Button bttA, bttB,bttC,bttD,bttE,bttF;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicky);
        result=findViewById(R.id.result);
        bttA=findViewById(R.id.bttA);
        bttB=findViewById(R.id.bttB);
        bttC=findViewById(R.id.bttC);
        bttD=findViewById(R.id.bttD);
        bttE=findViewById(R.id.bttE);
        bttF=findViewById(R.id.bttF);
        bttA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.setText("Pressed: A");
            }
        });
        bttB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.setText("Pressed: B");
            }
        });
        bttC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.setText("Pressed: C");
            }
        });
        bttD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.setText("Pressed: D");
            }
        });
        bttE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.setText("Pressed: E");
            }
        });
        bttF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.setText("Pressed: F");
            }
        });





    }

}