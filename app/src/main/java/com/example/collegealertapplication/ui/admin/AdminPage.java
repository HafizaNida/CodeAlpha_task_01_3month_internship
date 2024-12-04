package com.example.collegealertapplication.ui.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.collegealertapplication.MainActivity;
import com.example.collegealertapplication.R;
import com.example.collegealertapplication.ui.notice.CreateNoticeActivity;

public class AdminPage extends AppCompatActivity {

    TextView notification;
    ImageButton toolbarForwardBtn;
    LinearLayout noticeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //notification=findViewById(R.id.notification);

        noticeLayout=findViewById(R.id.noticeLayout);
        toolbarForwardBtn=findViewById(R.id.toolbarForwardBtn);

        noticeLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent myIntent=new Intent(AdminPage.this, CreateNoticeActivity.class);
                startActivity(myIntent);
            }
        });

        toolbarForwardBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent myIntent=new Intent(AdminPage.this, MainActivity.class);
                startActivity(myIntent);
            }
        });
    }
}
