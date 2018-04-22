package com.example.binhn.englishverstion1.NguPhap;

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

;import java.util.Locale;

/**
 * Created by user on 28/09/2017.
 */

public class ViewNguPhap extends AppCompatActivity {
    TextView view_ten,view_CauKD,view_CauPD,view_CauNV,view_CachDung,view_ChuY;
    String ten;
    DataBases db=new DataBases(this);
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_nguphap);
        view_ten=(TextView)findViewById(R.id.view_Ten);
        view_CauKD=(TextView)findViewById(R.id.view_CauKD);
        view_CauPD=(TextView)findViewById(R.id.view_CauPD);
        view_CauNV=(TextView)findViewById(R.id.view_CauNV);
        view_CachDung=(TextView)findViewById(R.id.view_CachDung);
        view_ChuY=(TextView)findViewById(R.id.view_ChuY);
        //Đọc dữ liệu từ csdl lên
        Intent in=getIntent();
        Bundle bundle=getIntent().getExtras();
        ten=bundle.getString("Ten");
        String ten=bundle.getString("Ten");
        String lang  = Utils.getLang(this,Utils.KEY_LANGUAGE);
        if(!lang.isEmpty()){
            Locale locale = new Locale(lang);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());
        }
        //hiện tiêu đề;
        ActionBar ab = getSupportActionBar();
        //set mầu cho actionBar
        ab.setTitle(R.string.nguphap);

        //Hiện nút back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(ten!=null){
            Cursor cursor=db.getCursor("select * from Gramar where Ten='"+ten+"'");
            cursor.moveToFirst();
            view_ten.setText(cursor.getString(1));
            view_CauKD.setText(cursor.getString(2));
            view_CauPD.setText(cursor.getString(3));
            view_CauNV.setText(cursor.getString(4));
            view_CachDung.setText(cursor.getString(5));
            view_ChuY.setText(cursor.getString(6));
        }
    }


}
