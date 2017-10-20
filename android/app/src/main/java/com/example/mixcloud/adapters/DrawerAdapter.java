package com.example.mixcloud.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mixcloud.R;

public class DrawerAdapter extends BaseAdapter{

    private final String[] stringArrayRes;

    public DrawerAdapter(Context context) {
        this.stringArrayRes = context.getResources().getStringArray(R.array.drawer_strings);

    }

    @Override
    public int getCount() {
        return stringArrayRes.length;
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

        LayoutInflater inflater = LayoutInflater.from(view.getContext());

        switch(position) {

            case 0:
                view = inflater.inflate(R.layout.item_drawer_header, null);
                //todo
                return view;

            case 1:
                view = inflater.inflate(R.layout.item_drawer, null);
                ((TextView)view.findViewById(R.id.drawer_item)).setText(stringArrayRes[position]);
                return view;

                default:
                    return null;

        }
    }
}
