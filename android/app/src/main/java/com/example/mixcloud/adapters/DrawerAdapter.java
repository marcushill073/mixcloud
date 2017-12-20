package com.example.mixcloud.adapters;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mixcloud.R;
import com.example.mixcloud.model.Navigation;

public class DrawerAdapter extends BaseAdapter{

    private final Navigation[] navigations = Navigation.values();


    @Override
    public int getCount() {
        return navigations.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        view = inflater.inflate(R.layout.item_drawer, null);
        TextView text = (TextView) view.findViewById(R.id.drawer_item_text);
        AppCompatImageView image = (AppCompatImageView) view.findViewById(R.id.drawer_item_image);
        image.setImageResource(navigations[position].getResourceDrawable());
        text.setText(navigations[position].getValue());
        return view;
    }
}
