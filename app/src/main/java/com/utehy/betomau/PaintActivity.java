package com.utehy.betomau;

import static com.utehy.betomau.ChonHinhActivity.res;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.utehy.betomau.Adapter.ListColorAdapter;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class PaintActivity extends AppCompatActivity {

    GridView lv_color;
    List<String> listColor;
    private DisplayPaint drawView;
    private ImageView drawBtn, eraseBtn, newBtn, saveBtn, zoomBtn, exitBtn;
    ImageView hinhanh;
    RelativeLayout layout_save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_paint);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        anhXa();
        setLv_color();
        loadXML();
        hinhanh.setImageResource(res);
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
        hinhanh = findViewById(R.id.img_anh);
        drawBtn = findViewById(R.id.btn_ve);
        layout_save = findViewById(R.id.layout_save);

        eraseBtn = findViewById(R.id.btn_xoa);

        newBtn = findViewById(R.id.btn_reset);


        saveBtn = findViewById(R.id.btn_chup);
        drawView = findViewById(R.id.drawing);
        zoomBtn = findViewById(R.id.btn_zoom);
        exitBtn = findViewById(R.id.btn_thoat);

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
                AlertDialog.Builder saveDialog = new AlertDialog.Builder(PaintActivity.this);
                saveDialog.setTitle("Lưu lại");
                saveDialog.setMessage("Bạn muốn lưu vào bộ sưu tập không?");
                saveDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //save drawing
                        //save drawing
                        layout_save.setDrawingCacheEnabled(true);
                        String imageSaved = MediaStore.Images.Media.insertImage(
                                getContentResolver(), layout_save.getDrawingCache(),
                                UUID.randomUUID().toString()+"_tomau", "drawing");
                        if(imageSaved != null)
                        {
                            Toast savedToast = Toast.makeText(getApplicationContext(),
                                    "Đã được lưu", Toast.LENGTH_SHORT);
                            savedToast.show();
                        }else
                        {
                            Toast unsavedToast = Toast.makeText(getApplicationContext(),
                                    "Lỗi", Toast.LENGTH_SHORT);
                            unsavedToast.show();
                        }
                        layout_save.destroyDrawingCache();
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
    }

    private void setSize() {
        Dialog dialog = new Dialog(PaintActivity.this);
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
        ListColorAdapter listColorAdapter = new ListColorAdapter(PaintActivity.this, listColor);
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


}