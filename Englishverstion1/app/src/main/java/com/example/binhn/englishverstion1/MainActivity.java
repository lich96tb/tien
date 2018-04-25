package com.example.binhn.englishverstion1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;


import com.example.binhn.englishverstion1.NguPhap.Main_NguPhap;
import com.example.binhn.englishverstion1.Note.Main_Note;
import com.example.binhn.englishverstion1.Question.main_question;
import com.example.binhn.englishverstion1.Story.Main_Story;
import com.example.binhn.englishverstion1.Tintuc.Main_News;
import com.example.binhn.englishverstion1.TraTu.Search_tran;
import com.example.binhn.englishverstion1.lichSuTest.lichSu_Test;
import com.example.binhn.englishverstion1.util.KeyWord;
import com.example.binhn.englishverstion1.util.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    GridView lv;
    DataBases db = new DataBases(this);
    ArrayList<String> ar = null;
    String[] item  ;
    Integer[] icon = {R.drawable.timkiem, R.drawable.tets, R.drawable.ghi_chu, R.drawable.book2, R.drawable.history,R.drawable.book5,R.drawable.news, R.drawable.icon1,R.drawable.aa};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoadActivity();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
      LoadActivity();
    }


    private void LoadActivity() {
        SharedPreferences sharedPreferences = getSharedPreferences(KeyWord.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        String language= sharedPreferences.getString("key","");
        if (language.equals("")==false){
            Locale locale = new Locale(language); // chèn ngôn ngữ
            Configuration config = new Configuration(); // cấu tạo lại hệ thống
            config.locale = locale; // cấu hình ngôn ngữ
            // cập nhật Resoures
            Utils.setLanguagePref(this, Utils.KEY_LANGUAGE, language);
            getBaseContext().getResources().updateConfiguration(
                    config,
                    getBaseContext().getResources().getDisplayMetrics());
        }
        //0ể lu4n chỗ này cho nó set này nếu nó khác null thì set
        item = getResources().getStringArray(R.array.main)  ;
        try {
            db.CopyDB2SDCard();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ar = new ArrayList<String>();
        for (String a : item) {
            ar.add(a);
        }
        ActionBar ab = getSupportActionBar();
        //set mầu cho actionBar
        ab.setTitle(R.string.app_name);

        Custom_listView adapter = new Custom_listView(this, ar, icon);
        adapter.notifyDataSetChanged();
        lv = (GridView) findViewById(R.id.Listitem);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stubin
                if (position == 0) {
                    Intent in = new Intent(getApplication(), Search_tran.class);
                    startActivity(in);
                }
                if (position == 1) {
                    Intent in = new Intent(getApplication(), main_question.class);
                    startActivity(in);
                }
                if (position == 2) {
                    Intent in = new Intent(getApplication(), Main_Note.class);
                    startActivity(in);
                }
                if (position == 3) {
                    Intent in = new Intent(getApplication(), Main_NguPhap.class);
                    startActivity(in);
                }
                if (position == 4) {
                    Intent in = new Intent(getApplication(), lichSu_Test.class);
                    startActivity(in);
                }
                if (position == 5) {
                    Intent in = new Intent(getApplication(), Main_Story.class);
                    startActivity(in);
                }
                if (position == 6) {
                    Intent in = new Intent(getApplication(), Main_News.class);
                    startActivity(in);
                }

                if (position == 7) {
                    Intent in = new Intent(getApplication(), Setting.class);
                    startActivity(in);
                }
                if (position == 8){
                    Intent in=new Intent(getApplication(),GioiThieu.class);
                    startActivity(in);
                }
            }
        });
    }
}

