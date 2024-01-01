package com.example.secondass;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TaskAdapter extends ArrayAdapter<NewsObj> {
    public TaskAdapter(@NonNull Context context, ArrayList<NewsObj> dataArrayList) {
        super(context,R.layout.list_item, dataArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        NewsObj newsobj = getItem(position);

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        TextView textViewItem = view.findViewById(R.id.textViewItem);
        TextView textViewSubItem = view.findViewById(R.id.textViewSubItem);

        textViewItem.setText(newsobj.getTitle());
        textViewSubItem.setText(newsobj.getDescription());

        return view;
    }
}



