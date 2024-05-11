package com.example.wild_animal_detection_system;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CustomizeCameraActivity extends AppCompatActivity {
    Button  deleteBTN;
    FloatingActionButton addCameraBTN;
    AlertDialog dialog;
    LinearLayout layout;
    EditText camName, camIP;
    TextView cam_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize_camera);

        addCameraBTN = findViewById(R.id.addingBtn);
        layout = findViewById(R.id.container1);

        buildDialog();

        addCameraBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
    }

    private void buildDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.add_camera,null);

        camName = view.findViewById(R.id.textView11);
        camIP = view.findViewById(R.id.textView1);

        builder.setView(view);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                addCard(camName.getText().toString());
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        dialog = builder.create();

    }

    private void addCard(String name) {
        View view = getLayoutInflater().inflate(R.layout.camera, null);

        cam_name = view.findViewById(R.id.camName);
        deleteBTN = view.findViewById(R.id.deleteCamera);

        cam_name.setText(name);

        deleteBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout.removeView((View) view.getParent());
            }
        });
        layout.addView(view);
    }
}