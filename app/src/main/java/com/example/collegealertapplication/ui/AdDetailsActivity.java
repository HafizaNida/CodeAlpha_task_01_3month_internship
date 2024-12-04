
package com.example.collegealertapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.collegealertapplication.MainActivity;
import com.example.collegealertapplication.R;
import com.example.collegealertapplication.data.ImageSliderAdapter;
import com.example.collegealertapplication.ui.notice.EditNoticeActivity;
import com.example.collegealertapplication.ui.notice.Notice;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class AdDetailsActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private String currentUserId;
    ImageButton toolbarBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_detail);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("notices");
        currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Initialize views
        TextView titleTv = findViewById(R.id.titleTv);
        TextView dateTv = findViewById(R.id.dateTv);
        TextView descriptionTv = findViewById(R.id.descriptionTv);
        ViewPager2 imageSliderVp = findViewById(R.id.imageSliderVp);
        toolbarBackBtn= findViewById(R.id.toolbarBackBtn);


        toolbarBackBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent myIntent=new Intent(AdDetailsActivity.this, MainActivity.class);
                startActivity(myIntent);
            }
        });

        // Fetch and display notice details
        String noticeId = getIntent().getStringExtra("noticeId");
        if (noticeId == null) {
            // Handle case where notice ID is not provided
            Toast.makeText(this, "Notice ID not provided", Toast.LENGTH_SHORT).show();
            finish(); // Close activity
        } else {
            fetchNoticeDetails(noticeId);
        }

        // Set click listeners for edit and delete buttons
        findViewById(R.id.toolbarEditBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle edit button click
                editNotice(noticeId);
            }
        });

        findViewById(R.id.toolbarDeleteBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle delete button click
                deleteNotice(noticeId);
            }
        });
    }

    // Method to fetch notice details from the database
    private void fetchNoticeDetails(String noticeId) {
        DatabaseReference noticeRef = mDatabase.child(noticeId);
        noticeRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Notice notice = dataSnapshot.getValue(Notice.class);
                if (notice != null) {
                    displayNoticeDetails(notice);
                    // Check if the current user is the creator of the notice
                    String creatorId = notice.getCreatorId();
                    if (creatorId != null && creatorId.equals(currentUserId)) {
                        // Current user is the creator, enable edit and delete buttons
                        findViewById(R.id.toolbarEditBtn).setVisibility(View.VISIBLE);
                        findViewById(R.id.toolbarDeleteBtn).setVisibility(View.VISIBLE);
                    } else {
                        // Current user is not the creator, disable edit and delete buttons
                        findViewById(R.id.toolbarEditBtn).setVisibility(View.GONE);
                        findViewById(R.id.toolbarDeleteBtn).setVisibility(View.GONE);
                    }
                } else {
                    Toast.makeText(AdDetailsActivity.this, "Notice details not found", Toast.LENGTH_SHORT).show();
                    finish(); // Close activity if notice details not found
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
                Toast.makeText(AdDetailsActivity.this, "Failed to fetch notice details: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                finish(); // Close activity on error
            }
        });
    }


    // Method to handle delete notice operation
    private void deleteNotice(String noticeId) {
        DatabaseReference noticeRef = mDatabase.child(noticeId);
        noticeRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Notice notice = dataSnapshot.getValue(Notice.class);
                if (notice != null && notice.getCreatorId().equals(currentUserId)) {
                    // Current user is the creator, delete the notice
                    noticeRef.removeValue(); // Remove notice from database
                    Toast.makeText(AdDetailsActivity.this, "Notice deleted successfully", Toast.LENGTH_SHORT).show();
                    finish(); // Close activity after deletion
                } else {
                    // Current user is not the creator, show error message
                    Toast.makeText(AdDetailsActivity.this, "You don't have permission to delete this notice", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
                Toast.makeText(AdDetailsActivity.this, "Failed to delete notice: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Method to handle edit notice operation
    private void editNotice(String noticeId) {
        DatabaseReference noticeRef = mDatabase.child(noticeId);
        noticeRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Notice notice = dataSnapshot.getValue(Notice.class);
                if (notice != null && notice.getCreatorId().equals(currentUserId)) {
                    // Current user is the creator, proceed with edit operation
                    // Start EditNoticeActivity with notice details
                    Intent intent = new Intent(AdDetailsActivity.this, EditNoticeActivity.class);
                    intent.putExtra("noticeId", noticeId);
                    intent.putExtra("title", notice.getTitle());
                    intent.putExtra("description", notice.getDescription());
                    // Add more notice details if needed
                    startActivity(intent);
                } else {
                    // Current user is not the creator, show error message
                    Toast.makeText(AdDetailsActivity.this, "You don't have permission to edit this notice", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
                Toast.makeText(AdDetailsActivity.this, "Failed to fetch notice details: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    // Method to display notice details
    private void displayNoticeDetails(Notice notice) {
        TextView titleTv = findViewById(R.id.titleTv);
        TextView dateTv = findViewById(R.id.dateTv);
        TextView descriptionTv = findViewById(R.id.descriptionTv);
        ViewPager2 imageSliderVp = findViewById(R.id.imageSliderVp);

        titleTv.setText(notice.getTitle());
        dateTv.setText(notice.getDate());
        descriptionTv.setText(notice.getDescription());

        // Check if the list of image URLs is not null before creating the adapter
        List<String> imageUrls = notice.getImageUrls();
        if (imageUrls != null) {
            // Create and set adapter for ViewPager2
            ImageSliderAdapter adapter = new ImageSliderAdapter(this, imageUrls);
            imageSliderVp.setAdapter(adapter);
        } else {
            // Handle case where image URLs are null
            Toast.makeText(this, "Image URLs not found", Toast.LENGTH_SHORT).show();
        }
    }

}
