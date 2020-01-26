package com.portfolio.vowelist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SubActivity extends AppCompatActivity {

    private ListView lv;

    SoundPool soundPool;    // 効果音を鳴らす本体（コンポ）

    int[] int_ = new int[5];

    private static final String[][] strArr_ = {
            {"apple [ˈæpl]", "cat [kˈæt]", "hat [hˈæt]", "japan [dʒəpˈæn]", "casual [kˈæʒuəl]"},
            {"stop [stάp]", "box [bάks]", "hot [hάt]", "doctor [dάktɚ]", "college [kάlɪdʒ]"},
            {"up [ˈʌp]", "bus [bˈʌs]", "hut [hˈʌt]", "love [lˈʌv]", "country [kˈʌntri]"},
            {"about [əbάʊt]", "ago [əgóʊ]", "medicine [médəsn]", "japan [dʒəpˈæn]", "balloon [bəlúːn]"},
            {"it [ít]", "is [ɪz]", "say [seɪ]", "will [wíl]", "finger [fíŋgɚ]"},
            {"put [pˈʊt]", "book [bˈʊk]", "full [fˈʊl]", "foot [fˈʊt]", "wolf [wˈʊlf]"},
            {"egg [ég]", "tennis [ténɪs]", "bed [béd]", "get [gét]", "bread [bréd]"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent intent = getIntent();
        int itemIndex = intent.getIntExtra("index",0);
        String itemName = intent.getStringExtra("name");
        getSupportActionBar().setTitle(itemName);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        } else {
            AudioAttributes attr = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setAudioAttributes(attr)
                    .setMaxStreams(5)
                    .build();
        }

        //adapterのインスタンスを作成
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        for(int i = 0;i < 5;i++) {

            adapter.add(strArr_[itemIndex][i]);

            //スペース位置を取得
            int space = strArr_[itemIndex][i].indexOf(" ");
            //音声ファイル名取得
            String word = strArr_[itemIndex][i].substring(0,space);
            //リソースID取得
            int strId = getResources().getIdentifier(word, "raw", getPackageName());
            //音声ファイルロード
            int_[i] = soundPool.load(this, strId, 1);
        }

        lv = (ListView) findViewById(R.id.listview_ex);
        lv.setAdapter(adapter);

        //リスト項目が選択された時のイベントを追加
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                soundRun(int_[position] );
            }
        });
    }
    private void soundRun(int mp3) {
        //再生処理
        soundPool.play(mp3,5f , 5f, 0, 0, 1f);
   //     soundPool.play(mp3b,5f , 5f, 0, 0, 1f);
    }
}
