package com.example.binhn.englishverstion1.Story;

import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.binhn.englishverstion1.DataBases;
import com.example.binhn.englishverstion1.NguPhap.Custom_Adapter_NguPhap;
import com.example.binhn.englishverstion1.NguPhap.NguPhap;
import com.example.binhn.englishverstion1.R;
import com.example.binhn.englishverstion1.util.Utils;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by user on 30/12/2017.
 */

public class Main_Story extends AppCompatActivity /* implements SearchView.OnQueryTextListener */{
    DataBases db=new DataBases(this);
    ArrayList<Story>arr;
    story_Adapter adp;
    ListView lv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lv_story);
        String lang  = Utils.getLang(this,Utils.KEY_LANGUAGE);
        if(!lang.isEmpty()){
            Locale locale = new Locale(lang);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());
        }
        ActionBar ab = getSupportActionBar();
        //set mầu cho actionBar
        ab.setTitle(R.string.truyen);
        //Hiện nút back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lv=(ListView)findViewById(R.id.lvstory);
        arr=new ArrayList<Story>();
        adp=new story_Adapter (this,R.layout.item_story,arr);
        lv.setAdapter(adp);
        load();
        adp.notifyDataSetChanged();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      //  getMenuInflater().inflate(R.menu.timkiem,menu);
      //  MenuItem item=menu.findItem(R.id.search);
      //  SearchView searchView=(SearchView) item.getActionView();
      //  searchView.setOnQueryTextListener(this);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
    public void load() {
        Cursor cur = db.getCursor("Select * from Story");
        int count = cur.getCount();
        if (cur.moveToFirst()) {
            do {
                arr.add(new Story(count++, cur.getString(1),cur.getString(2)));
            } while (cur.moveToNext());
        }
    }
 //   @Override
 //   public boolean onQueryTextSubmit(String query) {
 //       return true;
 //   }

   // @Override
  //  public boolean onQueryTextChange(String newText) {
  //      Cursor cur = db.getCursor("Select * from Story where Tentruyen like '%"+newText+"&'");
  //      int count = cur.getCount();
        // làm mới lại adp
   //     adp.clear();
//
  //      if (cur.moveToFirst()) {
  //          do {
   //             arr.add(new Story(count++, cur.getString(1), cur.getString(2)));
   //         } while (cur.moveToNext());
    //    }
   //     lv.setAdapter(adp);
  //      adp.notifyDataSetChanged();
  //      return true;
   // }
}
