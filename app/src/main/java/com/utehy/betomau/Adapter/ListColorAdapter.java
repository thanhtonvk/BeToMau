package com.utehy.betomau.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.utehy.betomau.R;

import java.util.List;

public class ListColorAdapter extends ArrayAdapter<String> {
    List<String> listMau;

    public ListColorAdapter(@NonNull Context context, List<String> listMau) {
        super(context, 0, listMau);
        this.listMau = listMau;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_color, parent, false);
        }
        View view = convertView.findViewById(R.id.item_color);
        String color = listMau.get(position);
        view.setBackgroundColor(Color.parseColor(color));
        return convertView;
    }
}

