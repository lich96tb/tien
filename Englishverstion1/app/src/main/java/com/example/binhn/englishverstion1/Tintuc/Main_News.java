package com.example.binhn.englishverstion1.Tintuc;

/**
 * Created by user on 10/01/2018.
 */

import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.binhn.englishverstion1.R;
import com.example.binhn.englishverstion1.util.Utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;

public class Main_News extends AppCompatActivity {
    // khai báo ánh xạ:
    ListView lvTieuDe;
    ArrayList<String> arrayTitle, arrayLink;
    ArrayAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainnews);
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
        ab.setTitle(R.string.tintuc);
        //Hiện nút back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // ánh xạ:
        lvTieuDe = (ListView) findViewById(R.id.listviewTieuDe);
        arrayTitle = new ArrayList<>();
        arrayLink = new ArrayList<>();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayTitle);
        lvTieuDe.setAdapter(adapter);

        new ReadRSS().execute("http://feeds.nbcnews.com/feeds/topstories");
        lvTieuDe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Main_News.this, News.class); //Chuyển dữ liệu qua
                intent.putExtra("linkTinTuc", arrayLink.get(position));
                startActivity(intent);
            }
        });
    }
    // tạo class đọc tin:
    private class ReadRSS extends AsyncTask<String, Void, String> {
        // đọc dữ liệu
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line = ""; // biến đọc
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line); // phần đọc
                }
                bufferedReader.close(); // đóng phần đọc lại

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content.toString();

        }
        // trả dữ liệu ra:
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);

            // chứa danh sách các item:
            NodeList nodeList = document.getElementsByTagName("item");
            String tieuDe = "";
            // đọc item:
            for (int i=0; i< nodeList.getLength(); i++){
                Element element = (Element) nodeList.item(i);
                tieuDe = parser.getValue(element, "title");
                arrayTitle.add(tieuDe);
                arrayLink.add(parser.getValue(element,"link"));

            }
            adapter.notifyDataSetChanged(); // cập nhật thay đổi
        }
    }
}
