package com.example.binhn.englishverstion1.Tintuc;

/**
 * Created by user on 10/01/2018.
 */

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.binhn.englishverstion1.R;
import com.example.binhn.englishverstion1.util.Utils;

import java.util.Locale;

// đọc chi tiết báo:

public class News extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
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
        ab.setTitle(getResources().getString(R.string.tintuc));
        //Hiện nút back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //ánh xạ
        webView = (WebView) findViewById(R.id.webviewTinTuc) ;
        //lấy dũ liệu ra:
        Intent intent = getIntent();
        String link = intent.getStringExtra("linkTinTuc");
        // load giao diện HTLM
        webView.loadUrl(link);
        webView.setWebViewClient(new WebViewClient()); //mở file khác vẫn chạy được trong app
    }
}
