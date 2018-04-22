package com.example.binhn.englishverstion1;

import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.binhn.englishverstion1.R;
import com.example.binhn.englishverstion1.util.Utils;

import java.util.Locale;

public class GioiThieu extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gioi_thieu);
        ActionBar ab = getSupportActionBar();
        //set mầu cho actionBar
        ab.setTitle(R.string.gioithieumain);
        //Hiện nút back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
