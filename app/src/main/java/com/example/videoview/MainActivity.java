package com.example.videoview;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public final static String VIDEO_URL = "http://sites.google.com/site/ublaccessmobile/sample_video.mp4";
    public final static int URL = 1;
    public final static int SDCARD =2;
    VideoView videoView;
            Button btnStart, btnStop;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView =(VideoView) findViewById(R.id.View);
                btnStart =(Button) findViewById(R.id.btnStart);
                        btnStop = (Button)  findViewById(R.id.btnStop);

        MediaController controller = new MediaController(MainActivity.this);
        videoView.setMediaController(controller);
        videoView.requestFocus();

        int type  = URL;
        switch (type){
            case URL:
                videoView.setVideoURI(Uri.parse(VIDEO_URL));
                break;

            case SDCARD:
                String path = Environment.getExternalStorageDirectory()
                        + "/TestVideo.mp4";
                videoView.setVideoPath(path);
                break;
        }

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Toast.makeText(MainActivity.this, "동영상이 준비되었습니다.", Toast.LENGTH_SHORT).show();

            }
        });
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(MainActivity.this, "동영상 재생이 완료되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void StopButton(View v) {
        playVideo();
    }



    public void StartButton(View v) {
        stopVideo();
    }

    private void playVideo(){
        videoView.seekTo(0);
        videoView.start();
    }
    private  void stopVideo(){
        videoView.pause();
    }


}