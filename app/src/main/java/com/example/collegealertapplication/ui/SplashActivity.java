package com.example.collegealertapplication.ui;

import static java.lang.Thread.sleep;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.collegealertapplication.R;
import com.example.collegealertapplication.ui.admin.Admin_User;

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


            Thread myThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        sleep(5000);
                        Intent myIntent = new Intent(SplashActivity.this, Admin_User.class);
                        startActivity(myIntent);
                        finish();

                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });myThread.start();
        }
    }
