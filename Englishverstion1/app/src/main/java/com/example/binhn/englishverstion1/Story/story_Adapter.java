package com.example.binhn.englishverstion1.Story;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.binhn.englishverstion1.R;

import java.util.ArrayList;

/**
 * Created by user on 30/12/2017.
 */

public class story_Adapter extends ArrayAdapter<Story> {
    private Context context;
    private int resource;
    private ArrayList<Story> arr;
    private ArrayAdapter<Story> adp;
    public story_Adapter (@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Story> arr) {
        super(context, resource, arr);
        this.context = context;
        this.resource = resource;
        this.arr = arr;
    }

    // hứng giá trị từ Main
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_story, parent, false);
        ImageView imageView = (ImageView)convertView.findViewById(R.id.imgStory);
        final TextView txtTentruyen = (TextView)convertView.findViewById(R.id.txt_StoryName);
        Story story = arr.get(position);
        txtTentruyen.setText(story.getTentruyen());
        convertView.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent in= new Intent(v.getContext(), View_Story.class);
                Bundle bundle = new Bundle();
                bundle.putString("Tentruyen",arr.get(position).getTentruyen());
                bundle.putString("noidung",arr.get(position).getNoidungtruyen());
                in.putExtras(bundle);
                context.startActivity(in);
            }

        });
        return convertView;
    }

}
