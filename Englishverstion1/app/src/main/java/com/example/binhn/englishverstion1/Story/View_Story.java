package com.example.binhn.englishverstion1.Story;

import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.binhn.englishverstion1.DataBases;
import com.example.binhn.englishverstion1.R;
import com.example.binhn.englishverstion1.util.Utils;

import java.util.Locale;

/**
 * Created by user on 30/12/2017.
 */

public class View_Story extends AppCompatActivity {
    TextView view_TenStory, view_ND;
    DataBases db = new DataBases(this);
    @RequiresApi (api = Build.VERSION_CODES.LOLLIPOP)

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_story);
        String lang  = Utils.getLang(this,Utils.KEY_LANGUAGE);
        if(!lang.isEmpty()){
            Locale locale = new Locale(lang);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());
        }
        view_TenStory = (TextView)findViewById(R.id.view_TenStory);
        view_ND = (TextView) findViewById(R.id.view_ND);
        // đọc dữ liệu từ CSDL
        Intent in = getIntent();
        Bundle bundle = getIntent().getExtras();

        String tenstory =bundle.getString("Tentruyen");
        String noidung=bundle.getString("noidung");
        view_TenStory.setText(tenstory);
        view_ND.setText(noidung);

        //Hiện tiêu đề và set màu:
        ActionBar ab = getSupportActionBar();
        ab.setTitle(R.string.truyen);

        //Hiện nút back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        if (tenstory!=null){
//            Cursor cursor = db.getCursor("select * from Story where Tentruyen='"+tenstory+"'");
//            cursor.moveToFirst();
//            view_TenStory.setText(cursor.getString(1));
//            view_ND.setText(cursor.getString(2));
//        }

    }
}
