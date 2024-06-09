package com.utehy.betomau;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.utehy.betomau.Adapter.ListChuDeAdapter;
import com.utehy.betomau.Model.ChuDe;

import org.lucasr.twowayview.TwoWayView;

import java.util.ArrayList;
import java.util.List;

public class ChonChuDeActivity extends AppCompatActivity {

    List<ChuDe> chuDeList;
    TwoWayView lv_chude;
    public static List<Integer>listHinhAnh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_chu_de);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setChuDeList();
        lv_chude = findViewById(R.id.lv_chude);
        ListChuDeAdapter chuDeAdapter = new ListChuDeAdapter(ChonChuDeActivity.this, chuDeList);
        lv_chude.setAdapter(chuDeAdapter);
        lv_chude.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listHinhAnh = chuDeList.get(i).getListHinhAnh();
                startActivity(new Intent(getApplicationContext(),ChonHinhActivity.class));
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

    private void setChuDeList() {
        chuDeList = new ArrayList<>();
        ChuDe chuDeConVat = new ChuDe();
        chuDeConVat.setTenchude("Con vật");
        chuDeConVat.setHinhanh(R.drawable.anh1);
        List<Integer> list = new ArrayList<>();
        list.add(R.drawable.anh1);
        list.add(R.drawable.anh3);
        list.add(R.drawable.anh4);
        list.add(R.drawable.anh5);
        list.add(R.drawable.anh6);
        list.add(R.drawable.anh8);
        list.add(R.drawable.anh10);
        list.add(R.drawable.anh11);
        list.add(R.drawable.anh12);
        list.add(R.drawable.anh13);
        list.add(R.drawable.anh14);
        list.add(R.drawable.anh15);
        chuDeConVat.setListHinhAnh(list);
        chuDeList.add(chuDeConVat);

        ChuDe chuDeQua = new ChuDe();
        chuDeQua.setTenchude("Quả");
        chuDeQua.setHinhanh(R.drawable.qua1);
        List<Integer> listQua = new ArrayList<>();
        listQua.add(R.drawable.qua1);
        listQua.add(R.drawable.qua2);
        listQua.add(R.drawable.qua3);
        listQua.add(R.drawable.qua4);
        listQua.add(R.drawable.qua5);
        listQua.add(R.drawable.qua6);
        listQua.add(R.drawable.qua7);
        chuDeQua.setListHinhAnh(listQua);
        chuDeList.add(chuDeQua);

        ChuDe chuDeHoa = new ChuDe();
        chuDeHoa.setTenchude("Hoa");
        chuDeHoa.setHinhanh(R.drawable.hoa1);
        List<Integer> listHoa = new ArrayList<>();
        listHoa.add(R.drawable.hoa1);
        listHoa.add(R.drawable.hoa2);
        listHoa.add(R.drawable.hoa3);
        listHoa.add(R.drawable.hoa4);
        listHoa.add(R.drawable.hoa5);
        listHoa.add(R.drawable.hoa6);
        listHoa.add(R.drawable.hoa7);
        chuDeHoa.setListHinhAnh(listHoa);
        chuDeList.add(chuDeHoa);


        ChuDe chuDeHoatHinh = new ChuDe();
        chuDeHoatHinh.setTenchude("Hoạt hình");
        chuDeHoatHinh.setHinhanh(R.drawable.hoathinh1);
        List<Integer> listHoaHinh = new ArrayList<>();
        listHoaHinh.add(R.drawable.hoathinh1);
        listHoaHinh.add(R.drawable.hoathinh2);
        listHoaHinh.add(R.drawable.hoathinh3);
        listHoaHinh.add(R.drawable.hoathinh4);
        listHoaHinh.add(R.drawable.hoathinh5);
        listHoaHinh.add(R.drawable.hoathinh6);
        listHoaHinh.add(R.drawable.hoathinh7);
        listHoaHinh.add(R.drawable.hoathinh8);
        chuDeHoatHinh.setListHinhAnh(listHoaHinh);
        chuDeList.add(chuDeHoatHinh);


    }
}