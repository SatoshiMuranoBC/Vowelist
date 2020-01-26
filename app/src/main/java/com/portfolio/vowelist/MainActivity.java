package com.portfolio.vowelist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //adapterのインスタンスを作成
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        //アイテムを追加
        adapter.add("æ");
        adapter.add("ɑ");
        adapter.add("ʌ");
        adapter.add("ǝ");
        adapter.add("ɪ");
        adapter.add("ʊ");
        adapter.add("e");

        lv = (ListView) findViewById(R.id.listview_vow);
        lv.setAdapter(adapter);

        //リスト項目が選択された時のイベントを追加
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView list = (ListView)parent;
                //アイテム名を取得
                String name = (String)list.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), SubActivity.class);

                intent.putExtra("index", position);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });

    }
}
