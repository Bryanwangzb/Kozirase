package com.kozirase.app;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.GestureDetector;
import android.widget.ImageView;

public class FinalScoreActivity extends AppCompatActivity {
    ImageView img;
    int loveVoltage = 100;   //50;              // テスト用に変えてみる

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_score);

        img = ((ImageView)findViewById(R.id.smallHeatMark)); // img view オブジェクトを取得しておく
        showloveVoltage();    // 追加。これがなければshowloveVoltageは呼ばれない
    }

    public void showloveVoltage(){                                       // view parameter は削除
        if (loveVoltage >= 80) {
            img.setImageResource(R.drawable.bigheat);
        }
        else if (loveVoltage >= 50) {                                    // if else の ネスト{} を修正
            img.setImageResource(R.drawable.middleheat);
        }
        else {
            img.setImageResource(R.drawable.smallheat);
        }
    }
}