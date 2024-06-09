package com.utehy.betomau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.utehy.betomau.R;

import java.util.List;

public class ListHinhAdapter extends ArrayAdapter<Integer> {
    List<Integer> listHinh;

    public ListHinhAdapter(@NonNull Context context, List<Integer> listHinh) {
        super(context, 0, listHinh);
        this.listHinh = listHinh;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_hinhmau, parent, false);
        }
        ImageView img = convertView.findViewById(R.id.itm_hinh);
        int res = listHinh.get(position);
        img.setImageResource(res);
        return convertView;
    }
}
