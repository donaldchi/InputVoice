package com.example.chi.inputvoice;

import java.util.ArrayList;
import java.lang.String;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);

        // ボタンをクリックした時、音声認識を開始し
        // トーストに認識結果を表示する。
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // インテント作成
                    Intent intent = new Intent(
                            RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

                    intent.putExtra(
                            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

                    // メッセージを設定。
                    intent.putExtra(
                            RecognizerIntent.EXTRA_PROMPT,
                            "音声認識を実行中。");

                    // インテント発行
                    startActivityForResult(intent, 0);
                } catch (ActivityNotFoundException e) {
                    // このインテントに応答できるアクティビティがインストールされていない場合
                    Toast.makeText(MainActivity.this,
                            "音声認識は使用できません。", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // requestCode は startActivityForResult関数の第2引数の値
        if (requestCode == 0 && resultCode == RESULT_OK) {
            String msg = "";

            // 結果文字列リスト
            ArrayList<String> ret = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);

            for (int i = 0; i< ret.size(); i++) {
                // 音声認識の結果をトーストに表示
                msg += ret.get(i) + ", " ;
            }

            // トーストを使って結果を表示
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
