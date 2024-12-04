package com.example.collegealertapplication.ui.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.collegealertapplication.R;
import com.example.collegealertapplication.ui.user.User_SignUp;

public class Admin_User extends AppCompatActivity {
    LinearLayout adminLayout,userLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        userLayout=findViewById(R.id.userLayout);
        adminLayout=findViewById(R.id.adminLayout);

        userLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent myIntent=new Intent(Admin_User.this, User_SignUp.class);
                startActivity(myIntent);
            }
        });

        adminLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent myIntent=new Intent(Admin_User.this,Admin_SignUp.class);
                startActivity(myIntent);
            }
        });
    }
}
