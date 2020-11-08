package com.example.learningsupport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.learningsupport.model.NewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class newAdapter extends ArrayAdapter<NewModel> {
    public newAdapter(@NonNull Context context, int resource, List<NewModel> list) {
        super(context, resource,list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view==null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.layout_new_listview,null);
        }
        NewModel p = getItem(position);
        if(p!=null){
            TextView txtTitle = (TextView)view.findViewById(R.id.ListView_new_title);
            txtTitle.setText(p.title);

            ImageView imageView = view.findViewById(R.id.ListView_new_img);
            try {
                Picasso.with(getContext()).load(p.img).into(imageView);
            }catch (Exception e){

            }
        }
        return view;
    }
}
