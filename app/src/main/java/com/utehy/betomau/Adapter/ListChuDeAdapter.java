package com.utehy.betomau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.utehy.betomau.Model.ChuDe;
import com.utehy.betomau.R;

import java.util.List;

public class ListChuDeAdapter extends ArrayAdapter<ChuDe> {
    List<ChuDe> listHinh;

    public ListChuDeAdapter(@NonNull Context context, List<ChuDe> listHinh) {
        super(context, 0, listHinh);
        this.listHinh = listHinh;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_hinhmau, parent, false);
        }
        ChuDe chuDe = listHinh.get(position);
        ImageView img = convertView.findViewById(R.id.itm_hinh);
        img.setImageResource(chuDe.getHinhanh());
        TextView tv_tenchude = convertView.findViewById(R.id.tv_tenchude);
        tv_tenchude.setText(chuDe.getTenchude());
        return convertView;
    }
}
