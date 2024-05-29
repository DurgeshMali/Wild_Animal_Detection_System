package com.example.wild_animal_detection_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CustomizeCameraActivity extends AppCompatActivity {
    private FloatingActionButton addsBtn;
    private RecyclerView recv;
    private ArrayList<Users> camList;
    private CameraAdapter cameraAdapter;
    private DatabaseReference databaseReference;
    private FirebaseAuth auth;
    String phoneUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize_camera);

        Intent intent = getIntent();
        phoneUser = intent.getStringExtra("userPhone");

        auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(phoneUser).child("Cameras");

        // set List
        camList = new ArrayList<>();

        // set find Id
        addsBtn = findViewById(R.id.addingBtn);
        recv = findViewById(R.id.mRecycler);

        // set Adapter
        cameraAdapter = new CameraAdapter(this, camList, phoneUser);

        // set Recycler view Adapter
        recv.setLayoutManager(new LinearLayoutManager(this));
        recv.setAdapter(cameraAdapter);

        fetchCameraData();

        // set Dialog
        addsBtn.setOnClickListener(v -> addInfo());
    }

    private void addInfo() {
        LayoutInflater inflter = LayoutInflater.from(this);
        android.view.View v = inflter.inflate(R.layout.add_camera, null);

        // set view
        EditText camName = v.findViewById(R.id.cam_Name);
        EditText camIP = v.findViewById(R.id.cam_IP);

        AlertDialog.Builder addDialog = new AlertDialog.Builder(this);

        addDialog.setView(v);
        addDialog.setPositiveButton("Ok", (dialog, which) -> {
            String camnames = camName.getText().toString();
            String camip = camIP.getText().toString();
            String id = String.valueOf(camList.size() + 1); // Linear child name
            Users user = new Users(camnames, camip);

            if (id != null) {
                databaseReference.child(id).setValue(user).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
//                        camList.add(user);
                        cameraAdapter.notifyDataSetChanged();
                        Toast.makeText(this, "Adding Camera Information Success", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Failed to add camera", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        addDialog.setNegativeButton("Cancel", (dialog, which) -> {
            dialog.dismiss();
            Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
        });
        addDialog.create();
        addDialog.show();
    }

    private void fetchCameraData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                camList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Users user = dataSnapshot.getValue(Users.class);
                    camList.add(user);
                }
                cameraAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CustomizeCameraActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
