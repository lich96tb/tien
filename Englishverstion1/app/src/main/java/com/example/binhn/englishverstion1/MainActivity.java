package com.example.binhn.englishverstion1;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;


import com.example.binhn.englishverstion1.NguPhap.Main_NguPhap;
import com.example.binhn.englishverstion1.Note.Main_Note;
import com.example.binhn.englishverstion1.Question.main_question;
import com.example.binhn.englishverstion1.Story.Main_Story;
import com.example.binhn.englishverstion1.Tintuc.Main_News;
import com.example.binhn.englishverstion1.TraTu.Search_tran;
import com.example.binhn.englishverstion1.lichSuTest.lichSu_Test;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GridView lv;
    DataBases db = new DataBases(this);
    ArrayList<String> ar = null;
    String[] item  ;
    Integer[] icon = {R.drawable.timkiem, R.drawable.tets, R.drawable.ghi_chu, R.drawable.book2, R.drawable.history,R.drawable.book5,R.drawable.news, R.drawable.icon1,R.drawable.aa};

    @Override
    protected void onRestart() {
        super.onRestart();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

