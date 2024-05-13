package com.example.wild_animal_detection_system;

import static android.os.Build.VERSION_CODES.M;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.github.niqdev.mjpeg.DisplayMode;
import com.github.niqdev.mjpeg.Mjpeg;
import com.github.niqdev.mjpeg.MjpegView;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
//import com.android.forum.R;
import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;

import java.io.IOException;
import java.util.ArrayList;
import com.github.niqdev.mjpeg.MjpegSurfaceView;


public class LiveFeedActivity extends AppCompatActivity {

    private  static final String IPCAM_URL = "http://10.134.141.201:8080/video";
    private MjpegView mjpegView;

//    ImageView imageView;
//    private static final boolean USE_TEXTURE_VIEW = false;
//    private static final boolean ENABLE_SUBTITLES = true;
//    private LibVLC mLibVLC;
//    private MediaPlayer mMediaPlayer;
//    private ImageView imageView;
//    private MjpegStreamTask mjpegStreamTask;
//    private SimpleExoPlayer player;
//    private PlayerView playerView;
//    WebView webVieww;
//    VideoView videoView;


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



//        imageView = findViewById(R.id.imageView);
//        Glide.with(this).load("https://usualcom.net/wp-content/uploads/2017/09/12364849-Planet-Earth-and-human-eye-Stock-Photo.jpg").into(imageView);

//        ArrayList<String> options = new ArrayList<>();
//        options.add("--no-drop-late-frames");
//        options.add("--no-skip-frames");
//        options.add("--rtsp-tcp");
//        options.add("-vvv");
//        mLibVLC = new LibVLC(this, options);
//        mMediaPlayer = new MediaPlayer(mLibVLC);

//        imageView = findViewById(R.id.imageView);
//
//        // Start fetching and displaying MJPEG stream
//        String mjpegUrl = "https://10.129.120.59:8080/video";
//        mjpegStreamTask = new MjpegStreamTask();
//        mjpegStreamTask.execute(mjpegUrl);

//        // Initialize PlayerView
//        playerView = findViewById(R.id.playerView);
//
//        // Initialize ExoPlayer
//        player = new SimpleExoPlayer.Builder(this).build();
//
//        // Set PlayerView to display video
//        playerView.setPlayer(player);
//
//        // HLS stream URL
//        String hlsUrl = "https://10.129.120.59:8080/video.mp4";
//
//        Uri uri = Uri.parse(hlsUrl);
//
//        MediaItem mediaItem = MediaItem.fromUri(uri);
//
//        // Set the MediaItem to the player
//        player.setMediaItem(mediaItem);
//
//        // Prepare the player
//        player.prepare();

//        webVieww = findViewById(R.id.webview);
//        webVieww.loadUrl("rtsp://10.129.120.59:8080/h264_opus.sdp");
//        mWebView.setWebViewClient(new WebViewClient());
//
//        // Allow all SSL certificates
//        allowAllCertificates();
//
//        // Load your HLS content URL here
//        String hlsUrl = "https://192.0.0.4:8080/";
//        mWebView.loadUrl(hlsUrl);/ Create a MediaItem
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        mMediaPlayer.attachViews(findViewById(R.id.videolayout), null, ENABLE_SUBTITLES, USE_TEXTURE_VIEW);
//
//        String name = "login";
//        String password = "password";
//        String cameraUrl = "https://10.129.120.59:8080/video";
//        String rtspUrl = "rtsp://" + name + ":" + password + "@" + cameraUrl;
//        String httpUrl = "https://archive.org/download/Popeye_forPresident/Popeye_forPresident_512kb.mp4";
//        Uri uri = Uri.parse(cameraUrl);
//
//        Media media = new Media(mLibVLC, uri);
//        media.setHWDecoderEnabled(true, false);
//        media.addOption(":network-caching=150");
//        media.addOption(":clock-jitter=0");
//        media.addOption(":clock-synchro=0");
//        mMediaPlayer.setMedia(media);
//        media.release();
//
//        mMediaPlayer.play();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        mMediaPlayer.stop();
//        mMediaPlayer.detachViews();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mMediaPlayer.release();
//        mLibVLC.release();
//    }




//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        // Stop fetching MJPEG stream when the activity is destroyed
//        if (mjpegStreamTask != null) {
//            mjpegStreamTask.cancel(true);
//        }
//    }
//
//    private class MjpegStreamTask extends AsyncTask<String, Bitmap, Void> {
//
//        @Override
//        protected Void doInBackground(String... strings) {
//            try {
//                // Connect to MJPEG stream URL
//                URL url = new URL(strings[0]);
//                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//
//                // Read MJPEG stream frames
//                while (!isCancelled()) {
//                    StringBuilder imageData = new StringBuilder();
//                    String line;
//                    while ((line = reader.readLine()) != null) {
//                        if (line.startsWith("--boundary")) {
//                            if (imageData.length() > 0) {
//                                // Parse and decode JPEG image
//                                byte[] jpegData = imageData.toString().getBytes();
//                                Bitmap bitmap = BitmapFactory.decodeByteArray(jpegData, 0, jpegData.length);
//                                // Publish progress to update UI
//                                publishProgress(bitmap);
//                            }
//                            imageData = new StringBuilder();
//                        }
//                        imageData.append(line);
//                    }
//                }
//
//                // Close connection
//                reader.close();
//                connection.disconnect();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onProgressUpdate(Bitmap... bitmaps) {
//            // Update ImageView with latest frame
//            imageView.setImageBitmap(bitmaps[0]);
//        }
//    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        player.setPlayWhenReady(true); // Start playback when the activity is visible
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        player.setPlayWhenReady(false); // Pause playback when the activity is not visible
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        player.release(); // Release the player when the activity is destroyed
//    }



//    private void allowAllCertificates() {
//        try {
//            TrustManager[] trustAllCerts = new TrustManager[]{
//                    new X509TrustManager() {
//                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//                            return null;
//                        }
//                        public void checkClientTrusted(X509Certificate[] certs, String authType) {
//                        }
//                        public void checkServerTrusted(X509Certificate[] certs, String authType) {
//                        }
//                    }
//            };
//
//            // Install the all-trusting trust manager
//            SSLContext sc = SSLContext.getInstance("SSL");
//            sc.init(null, trustAllCerts, new java.security.SecureRandom());
//            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
//
//            // Install the all-trusting host verifier
//            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
//                public boolean verify(String hostname, SSLSession session) {
//                    return true;
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }






//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
//        webView.getSettings().setDomStorageEnabled(true);
//        webView.getSettings().setMediaPlaybackRequiresUserGesture(false);
//        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
////        webView.loadUrl("https://www.google.com");
//        webView.loadUrl("https://videos.files.wordpress.com/Fo0X2qAf/samplevideos2_hd.mp4");


//        videoView = findViewById(R.id.videoView);
//        videoView.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.animal_detection_demo_clip);
//        MediaController mediaController = new MediaController(this);
//        mediaController.setAnchorView(videoView);
//        videoView.setMediaController(mediaController);
//        videoView.start();

    }