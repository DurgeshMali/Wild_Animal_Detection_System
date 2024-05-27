package com.example.wild_animal_detection_system;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import com.github.niqdev.mjpeg.DisplayMode;
import com.github.niqdev.mjpeg.Mjpeg;
import com.github.niqdev.mjpeg.MjpegView;

public class LiveFeedActivity extends AppCompatActivity {

    private static final String IPCAM_URL = "http://10.187.206.146:8080/video";
    private MjpegView mjpegView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_feed);

        mjpegView = findViewById(R.id.mjegView);
        Mjpeg.newInstance().open(IPCAM_URL).subscribe(inputStream -> {
            mjpegView.setSource(inputStream);
            mjpegView.setDisplayMode(DisplayMode.BEST_FIT);
            mjpegView.showFps(true);
        });

    }
}