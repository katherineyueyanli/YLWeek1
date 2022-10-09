package com.example.ylweek1;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PrimesActivity extends AppCompatActivity {
    private static final String TAG = "PrimesActivity";
    Button find, terminate;
    TextView result, check;
    private Handler textHandler = new Handler();
    boolean sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primes);
        find = findViewById(R.id.bttFind);
        terminate = findViewById(R.id.bttTerminate);
        result = findViewById(R.id.latest);
        check = findViewById(R.id.checking);
        runnableThread runnableThread = new runnableThread();
        Thread t1 = new Thread(runnableThread);
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1.start();
            }
        });

        terminate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1.interrupt();
            }
        });

    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(PrimesActivity.this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        PrimesActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    //Method which runs on the UI thread.
    public void runOnMainThread(View view) {
        check.setText("1");
        for (int i = 3; i < 50; i += 2) {
            int finalI = i;
            textHandler.post(new Runnable() {
                @SuppressLint("SetTextI18n")
                @Override
                public void run() {
                    check.setText(String.valueOf(finalI));
                }
            });
            Log.d(TAG, String.valueOf(i));
            try {

                Thread.sleep(100); //Makes the thread sleep or be inactive for 10 seconds

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    //Method which runs on a different thread which uses the Runnable interface.
    public void runOnRunnableThread(View view) {
        runnableThread runnableThread = new runnableThread();
        new Thread(runnableThread).start();
    }

    //Class which implements the Runnable interface.
    class runnableThread implements Runnable {

        @Override
        public void run() {
            for (int i = 3; i <= 100; i += 2) {
                final int finalI = i;
                //The handler changes the TextView running in the UI thread.
                textHandler.post(new Runnable() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void run() {
                        check.setText(String.valueOf(finalI));
                    }

                });
                if (finalI % 2 == 0 && finalI != 2) return;
                sign = true;
                for (int j = 2; j < finalI; j++) {
                    if (finalI % j == 0) {
                        sign = false;
                        break;
                    }
                }
                if (sign) {
                    Log.d(TAG, finalI + "is a Prime Number");
                    textHandler.post(new Runnable() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void run() {
                            result.setText(String.valueOf(finalI));
                        }

                    });

                }


                Log.d(TAG, "Running on a different thread using Runnable Interface: " + i);
                try {
                    Thread.sleep(1000); //Makes the thread sleep or be inactive for 10 seconds

                } catch (InterruptedException e) {

                    return;
                }

            }
        }




    }
}








