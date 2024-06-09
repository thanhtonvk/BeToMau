package com.utehy.betomau;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.utehy.betomau.Adapter.ListHinhAdapter;

import org.lucasr.twowayview.TwoWayView;

import java.util.ArrayList;
import java.util.List;
import static com.utehy.betomau.ChonChuDeActivity.listHinhAnh;
public class ChonHinhActivity extends AppCompatActivity {

    List<Integer> listHinh;
    ListHinhAdapter hinhAdapter;
    TwoWayView lv_hinh;
    public static int res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_mau);
        lv_hinh = findViewById(R.id.lv_hinhmau);
        listHinh = listHinhAnh;
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        hinhAdapter = new ListHinhAdapter(ChonHinhActivity.this, listHinh);
        lv_hinh.setAdapter(hinhAdapter);
        lv_hinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                res = listHinh.get(i);
                startActivity(new Intent(ChonHinhActivity.this, PaintActivity.class));
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

}