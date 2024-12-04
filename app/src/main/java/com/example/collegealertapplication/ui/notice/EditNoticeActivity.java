package com.example.collegealertapplication.ui.notice;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.collegealertapplication.MainActivity;
import com.example.collegealertapplication.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditNoticeActivity extends AppCompatActivity {

    private EditText editTitleEt,editDescriptionEt;
    private EditText descriptionEditText;
    private Button updateButton;

    private DatabaseReference mDatabase;
    private String noticeId;
    ImageButton editToolbarAddImageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        // Initialize views
        editTitleEt = findViewById(R.id.editTitleEt);
        editDescriptionEt = findViewById(R.id.editDescriptionEt);
        updateButton = findViewById(R.id.updateButton);

        // Initialize Firebase database reference
        mDatabase = FirebaseDatabase.getInstance().getReference().child("notices");

        // Retrieve noticeId from intent extras
        noticeId = getIntent().getStringExtra("noticeId");
        if (noticeId == null) {
            // Handle case where notice ID is not provided
            Toast.makeText(this, "Notice ID not provided", Toast.LENGTH_SHORT).show();
            finish(); // Close activity
        }

        // Retrieve existing notice details from intent extras
        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");

        // Set existing notice details in EditText fields
        editTitleEt.setText(title);
        editDescriptionEt.setText(description);

        // Set click listener for update button
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateNotice();
            }
        });
    }
    // Method to update notice details in Firebase
    private void updateNotice() {
        String newTitle = editTitleEt.getText().toString().trim();
        String newDescription = editDescriptionEt.getText().toString().trim();

        // Check if any field is empty
        if (TextUtils.isEmpty(newTitle) || TextUtils.isEmpty(newDescription)) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        // Create a map to update notice details in Firebase
        DatabaseReference noticeRef = mDatabase.child(noticeId);
        noticeRef.child("title").setValue(newTitle);
        noticeRef.child("description").setValue(newDescription);

        Toast.makeText(this, "Notice updated successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(EditNoticeActivity.this, MainActivity.class);
        startActivity(intent);
        //finish();
    }
}