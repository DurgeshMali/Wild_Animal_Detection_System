package com.example.wild_animal_detection_system;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CustomizeCameraActivity extends AppCompatActivity {
    private FloatingActionButton addsBtn;
    private RecyclerView recv;
    private ArrayList<Users> camList;
    private CameraAdapter cameraAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize_camera);

        // set List
        camList = new ArrayList<>();

        // set find Id
        addsBtn = findViewById(R.id.addingBtn);
        recv = findViewById(R.id.mRecycler);

        // set Adapter
        cameraAdapter = new CameraAdapter(this, camList);

        // set Recycler view Adapter
        recv.setLayoutManager(new LinearLayoutManager(this));
        recv.setAdapter(cameraAdapter);

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
            camList.add(new Users("CamName: " + camnames, "CamIP. : " + camip));
            cameraAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Adding Camera Information Success", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });
        addDialog.setNegativeButton("Cancel", (dialog, which) -> {
            dialog.dismiss();
            Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
        });
        addDialog.create();
        addDialog.show();
    }
}

