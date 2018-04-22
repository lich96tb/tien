package com.example.binhn.englishverstion1.TraTu;


import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.binhn.englishverstion1.DataBases;
import com.example.binhn.englishverstion1.Note.edtNote;
import com.example.binhn.englishverstion1.R;
import com.example.binhn.englishverstion1.util.Utils;

import java.util.Locale;

;

/**
 * Created by user on 21/09/2017.
 */

public class Tra_Tu extends AppCompatActivity implements SearchView.OnQueryTextListener {
    TextView txt;
    TextToSpeech t1;
    String worl;
    String mean;
    DataBases db = new DataBases(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tra_tu);
        String lang  = Utils.getLang(this,Utils.KEY_LANGUAGE);
        if(!lang.isEmpty()){
            Locale locale = new Locale(lang);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());
        }
        txt = (TextView) findViewById(R.id.tvhientratu);
        Bundle b = getIntent().getExtras();
        worl = b.getString("key_Word");
        mean = b.getString("key_Mean");
        Lichsu(worl);
        txt.setText(mean);
        //hiện tiêu đề;
        ActionBar ab = getSupportActionBar();
        //set mầu cho actionBar
        ab.setTitle(worl);
        //Hiện nút back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.ENGLISH);
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_tratu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        edtNote edtnote=new edtNote();
        switch (item.getItemId()) {
            case R.id.ThemNote:
                Bundle b = getIntent().getExtras();
                worl = b.getString("key_Word");
                mean = b.getString("key_Mean");
                mean=android.database.DatabaseUtils.sqlEscapeString(mean);
                int sbg = db.GetCount("Select * from GhiChu where TenGhiChu=\"" + worl.trim() + "\"");
                int count = db.GetCount("Select * from GhiChu");
                count = count + 1;
                if (sbg == 1) {
                    AlertDialog.Builder al = new AlertDialog.Builder(this);
                    al.setTitle(R.string.thongbao);
                    al.setMessage(R.string.tenghichudatontai);
                    al.create().show();
                } else if (sbg == 0) {
                    /*Thêm 2 dấu nháy kép và 1 2 dấu xổ chéo để đọc được các kí tự đặc biệt, kí hiệu trong txt*/
                    db.ExecuteSQL("Insert into GhiChu values("+count+",\""+worl.trim()+"\",\""+mean+"\")");
                    Toast.makeText(this, R.string.dathem, Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.item_search:
                Intent in = new Intent(getApplicationContext(), Search_tran.class);
                finish();
                startActivity(in);
                break;

            case R.id.itemdoc:
                t1.speak(worl, TextToSpeech.QUEUE_FLUSH, null);
                Toast.makeText(this, R.string.dangdoc, Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void Lichsu(String a) {
        Cursor c = db.getCursor("select * from LichSuTraTu");
        int leng = c.getCount();
        if (leng < 50) {
            setLS(a, leng);
        } else {
            db.ExecuteSQL("delete from LichSuTraTu where ID = 1");// xoa lich sử
            setLS(a, leng);
        }
    }

    //kiểm tra trùng lặp
    public void setLS(String a, int i) {
        Cursor c = db.getCursor("select * from LichSuTraTu where work = '" + a + "'");
        int leng = c.getCount();
        if (leng == 0) {
            db.ExecuteSQL("insert into LichSuTraTu values(" + i +1 + ",\"" + a + "\")");
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
