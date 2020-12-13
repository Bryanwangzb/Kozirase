package com.kozirase.app;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.GestureDetector;
import android.widget.ImageView;

public class FinalScoreActivity extends AppCompatActivity {
    ImageView img;
    int loveVoltage = 50;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_score);

        img = findViewByid(R.id.smallHeatMark);

        public void showloveVoltage(View v){
            if (loveVoltage >= 80)
                img.setImageResource(R.drawable.bigHeat);
            else (loveVoltage >= 50)
                img.setImageResource(R.drawable.middleHeat);
            else
                img.setImageResource(R.drawable.smallHeat);





        }








    }
}