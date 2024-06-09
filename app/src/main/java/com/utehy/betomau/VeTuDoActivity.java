package com.utehy.betomau;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.utehy.betomau.Adapter.ListColorAdapter;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class VeTuDoActivity extends AppCompatActivity {

    GridView lv_color;
    List<String> listColor;
    private DisplayPaint drawView;
    private ImageView drawBtn, eraseBtn, newBtn, saveBtn, zoomBtn, exitBtn, openBtn;
    final int GALERY_REQUEST = 123;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ve_tu_do);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        anhXa();
        setLv_color();
        loadXML();
        lv_color.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                drawView.setColor(listColor.get(i));
                MediaPlayer md = MediaPlayer.create(getApplicationContext(),R.raw.click);
                md.start();
            }
        });
        onClick();
    }


    private void anhXa() {
        drawBtn = findViewById(R.id.btn_ve);


        eraseBtn = findViewById(R.id.btn_xoa);

        newBtn = findViewById(R.id.btn_reset);


        saveBtn = findViewById(R.id.btn_chup);
        drawView = findViewById(R.id.drawing);
        zoomBtn = findViewById(R.id.btn_zoom);
        exitBtn = findViewById(R.id.btn_thoat);
        openBtn = findViewById(R.id.btn_open);
        img = findViewById(R.id.img_anh);
    }


    private void onClick() {
        drawBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eraseBtn.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                drawBtn.setBackgroundResource(R.color.teal_700);
                drawView.setBrushSize(5);
                drawView.setLastBrushSize(5);
                drawView.setErase(false);
                MediaPlayer md = MediaPlayer.create(getApplicationContext(),R.raw.click);
                md.start();

            }
        });
        eraseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eraseBtn.setBackgroundResource(R.color.teal_700);
                drawBtn.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                drawView.setErase(true);
                drawView.setBrushSize(5);
                MediaPlayer md = MediaPlayer.create(getApplicationContext(),R.raw.click);
                md.start();

            }
        });
        newBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawView.startNew();
                MediaPlayer md = MediaPlayer.create(getApplicationContext(),R.raw.click);
                md.start();
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer md = MediaPlayer.create(getApplicationContext(),R.raw.click);
                md.start();
                AlertDialog.Builder saveDialog = new AlertDialog.Builder(VeTuDoActivity.this);
                saveDialog.setTitle("Lưu lại");
                saveDialog.setMessage("Bạn muốn lưu vào bộ sưu tập không?");
                saveDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //save drawing
                        //save drawing
                        drawView.setDrawingCacheEnabled(true);
                        String imageSaved = MediaStore.Images.Media.insertImage(
                                getContentResolver(), drawView.getDrawingCache(),
                                UUID.randomUUID().toString() + "_vetudo", "drawing");
                        if (imageSaved != null) {
                            Toast savedToast = Toast.makeText(getApplicationContext(),
                                    "Đã được lưu", Toast.LENGTH_SHORT);
                            savedToast.show();
                        } else {
                            Toast unsavedToast = Toast.makeText(getApplicationContext(),
                                    "Lỗi", Toast.LENGTH_SHORT);
                            unsavedToast.show();
                        }
                        drawView.destroyDrawingCache();
                    }
                });
                saveDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                saveDialog.show();
            }
        });
        zoomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSize();
                MediaPlayer md = MediaPlayer.create(getApplicationContext(),R.raw.click);
                md.start();
            }
        });
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                MediaPlayer md = MediaPlayer.create(getApplicationContext(),R.raw.click);
                md.start();
            }
        });
        openBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer md = MediaPlayer.create(getApplicationContext(),R.raw.click);
                md.start();
                ActivityCompat.requestPermissions(VeTuDoActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},GALERY_REQUEST);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

         if(requestCode==GALERY_REQUEST&&grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent,GALERY_REQUEST);
        }
        else{
            Toast.makeText(getApplicationContext(), "Bạn chưa cấp quyền camera", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==GALERY_REQUEST&&resultCode == RESULT_OK && data!=null){
            Uri uriImage = data.getData();
            InputStream inputStream = null;
            try {
                inputStream = getContentResolver().openInputStream(uriImage);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                img.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }


    private void setSize() {
        Dialog dialog = new Dialog(VeTuDoActivity.this);
        dialog.setContentView(R.layout.dialog_size);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        SeekBar seekBar = dialog.findViewById(R.id.set_size);
        seekBar.setMax(100);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                drawView.setBrushSize(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        dialog.show();
    }


    private void setLv_color() {
        listColor = new ArrayList<>();
        lv_color = findViewById(R.id.lv_listmau);
        ListColorAdapter listColorAdapter = new ListColorAdapter(VeTuDoActivity.this, listColor);
        lv_color.setAdapter(listColorAdapter);
    }

    private void loadXML() {

        try {
            XmlPullParserFactory parserFactory;
            parserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserFactory.newPullParser();
            InputStream is = getAssets().open("colors.xml");
            parser.setInput(is, null);
            processParsing(parser, listColor);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processParsing(XmlPullParser parser, List<String> listColor) throws XmlPullParserException, IOException {
        int evenType = parser.getEventType();
        while (evenType != XmlPullParser.END_DOCUMENT) {
            String eltName = null;
            switch (evenType) {
                case XmlPullParser.START_TAG:
                    eltName = parser.getName();
                    if ("color".equals(eltName)) {
                        listColor.add(parser.nextText());
                    }
            }
            evenType = parser.next();
        }
    }

    Random random = new Random();

}