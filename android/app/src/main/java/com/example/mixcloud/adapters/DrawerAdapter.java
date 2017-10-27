package com.example.mixcloud.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        view = inflater.inflate(R.layout.item_drawer, null);
        TextView text = (TextView) view.findViewById(R.id.drawer_item_text);
        ImageView image = (ImageView) view.findViewById(R.id.drawer_item_image);

        String str = stringArrayRes[position];
        text.setText(str);

        switch(str) {

            case "Tracks":
                image.setImageDrawable(view.getContext().getResources().getDrawable(R.drawable.ic_iconmonstr_disc_5));
                break;
            case "Playlist":
                image.setImageDrawable(view.getContext().getResources().getDrawable(R.drawable.ic_iconmonstr_disc_7));
                break;
            case "Groups":
                image.setImageDrawable(view.getContext().getResources().getDrawable(R.drawable.ic_iconmonstr_user_29));
            break;
            case "Following":
                image.setImageDrawable(view.getContext().getResources().getDrawable(R.drawable.ic_iconmonstr_user_1));
                break;

        }

        return view;
    }
}
