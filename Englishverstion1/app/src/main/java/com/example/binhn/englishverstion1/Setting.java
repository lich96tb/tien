package com.example.binhn.englishverstion1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.binhn.englishverstion1.util.KeyWord;
import com.example.binhn.englishverstion1.util.Utils;

import java.util.Locale;

/**
 * Created by user on 17/11/2017.
 */

public class Setting extends AppCompatActivity {
    Button btxoaTest, btxoasTratu;
    TextView tvthongtin;
    DataBases db = new DataBases(this);
    ImageView imgAnh, imgVietNam;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        ActionBar ab = getSupportActionBar();
        //set mầu cho actionBar
        ab.setTitle(R.string.caidat);
        //Hiện nút back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imgAnh = (ImageView) findViewById(R.id.imageAnh);
        imgVietNam = (ImageView) findViewById(R.id.imageVietNam);
        //
        btxoasTratu = (Button) findViewById(R.id.btxoatratu);
        btxoaTest = (Button) findViewById(R.id.btxoaTest);
        btxoasTratu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.ExecuteSQL("DELETE FROM LichSuTraTu");
                Toast.makeText(Setting.this, R.string.xoathanhcong, Toast.LENGTH_SHORT).show();
            }
        });
        btxoaTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.ExecuteSQL("DELETE FROM LichSuTest");
                Toast.makeText(Setting.this, R.string.xoathanhcong, Toast.LENGTH_SHORT).show();
            }
        });

        imgAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ganNgonNgu("en");

            }
        });

        imgVietNam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ganNgonNgu("vi");
            }
        });
    }

    public void ganNgonNgu(String language) {
        SharedPreferences sharedPreferences = getSharedPreferences(KeyWord.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("key", language);
        editor.commit();


        Locale locale = new Locale(language); // chèn ngôn ngữ
        Configuration config = new Configuration(); // cấu tạo lại hệ thống
        config.locale = locale; // cấu hình ngôn ngữ
        // cập nhật Resoures
        Utils.setLanguagePref(this, Utils.KEY_LANGUAGE, language);
        getBaseContext().getResources().updateConfiguration(
                config,
                getBaseContext().getResources().getDisplayMetrics());
        // load lại view
        //chuyền nó vào chỗ này nhé chyền vào cái KEY_LANGUAGE
        // XONG B3n nhận thì viết nguy3n lại c2u tr3n rồi lấy key ra thay vào
        Intent inten = new Intent(Setting.this, Setting.class);
        startActivity(inten);
        finish();

    }

}
