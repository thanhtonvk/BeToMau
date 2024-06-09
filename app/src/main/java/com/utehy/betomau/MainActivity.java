package com.utehy.betomau;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btn_batdau;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        btn_batdau = findViewById(R.id.btn_batdau);
        btn_batdau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer md = MediaPlayer.create(getApplicationContext(),R.raw.click);
                md.start();
                startActivity(new Intent(getApplicationContext(), ChonKieuActivity.class));
            }
        });
        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.music);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
    }

}