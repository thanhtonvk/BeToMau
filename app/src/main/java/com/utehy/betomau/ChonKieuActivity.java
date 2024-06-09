package com.utehy.betomau;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class ChonKieuActivity extends AppCompatActivity {

    RelativeLayout btn_vetudo, btn_tomau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_kieu);

        anhXa();  this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        btn_vetudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), VeTuDoActivity.class));
                MediaPlayer md = MediaPlayer.create(getApplicationContext(),R.raw.click);
                md.start();
            }
        });
        btn_tomau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ChonChuDeActivity.class));
                MediaPlayer md = MediaPlayer.create(getApplicationContext(),R.raw.click);
                md.start();
            }
        });
        Button btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                MediaPlayer md = MediaPlayer.create(getApplicationContext(),R.raw.click);
                md.start();
            }
        });
    }

    private void anhXa() {
        btn_vetudo = findViewById(R.id.btn_vetudo);
        btn_tomau = findViewById(R.id.btn_tomau);
    }
}