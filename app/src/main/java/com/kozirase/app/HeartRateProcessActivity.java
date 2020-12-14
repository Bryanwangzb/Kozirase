package com.kozirase.app;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HeartRateProcessActivity extends AppCompatActivity {
    private LineChart mHeartRateLineChart;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_rate_process);

        initViews();

        //background setting
        mHeartRateLineChart.setDrawGridBackground(true);
        mHeartRateLineChart.getDescription().setEnabled(true);

        XAxis xAxis = mHeartRateLineChart.getXAxis();
        xAxis.enableGridDashedLine(10f,10f,0);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        YAxis leftAxis = mHeartRateLineChart.getAxisLeft();
        leftAxis.setAxisMinimum(0);
        leftAxis.setAxisMaximum(150f);

        leftAxis.enableGridDashedLine(10f,10f,0);
        leftAxis.setDrawZeroLine(true);

        setHeartData();
        mHeartRateLineChart.animateX(2500);




        //HeartRate heartRate = new HeartRate("07/28/20 15:57:27",new HeartRate.Value(70,2));
        //String json = gson.toJson(heartRate);

        //String jsonFileString = Utils.getJsonFromAssets(getApplicationContext(), "heart_rate-2020-07029.json");
        //Log.i("data", jsonFileString);
        //Gson gson = new Gson();
        //Type listUserType = new TypeToken<List<HeartRate>>() {
        //}.getType();

        //List<HeartRate> heartRates = gson.fromJson(jsonFileString, listUserType);
        //System.out.println("The item's amount is:" + heartRates.size());



//        for (int i = 0; i < heartRates.size(); i++) {
//            Log.i("data", "> Item" + i + "\n" +
//                    "Time: " + heartRates.get(i).getDateTime() + "\n" +
//                    "HeartRate: " + heartRates.get(i).getValue().getBpm() + "\n");
//        }

//        HeartRate heartRate = gson.fromJson(json, HeartRate.class);
//        // For testing gson function.
//        System.out.println("*********************************");
//        System.out.println(heartRate.getDateTime());
//        System.out.println(heartRate.getValue().getBpm());
//        System.out.println(heartRate.getValue().getConfidence());


    }

    private void setHeartData(){
        int[] data = {70,82,82,82,75,69,70,69,69,76,76,78,70,70,82,82,82,75,69,70,69,69,76,76,78,70};
        ArrayList<Entry> values = new ArrayList<>();
        for(int i=0;i<data.length;i++){
            values.add(new Entry(i,data[i],null,null));

        }

        LineDataSet lineDataSet;

        if(mHeartRateLineChart.getData()!=null && mHeartRateLineChart.getData().getDataSetCount()>0){
            lineDataSet  = (LineDataSet)mHeartRateLineChart.getData().getDataSetByIndex(0);
            lineDataSet.setValues(values);
            mHeartRateLineChart.getData().notifyDataChanged();
            mHeartRateLineChart.notifyDataSetChanged();
        }else{
            lineDataSet = new LineDataSet(values,"Heart Rate Data");
            lineDataSet.setDrawIcons(false);
            lineDataSet.setColor(Color.BLACK);
            lineDataSet.setCircleColor(Color.BLACK);
            lineDataSet.setLineWidth(1f);
            lineDataSet.setCircleRadius(3f);
            lineDataSet.setDrawCircleHole(false);
            lineDataSet.setValueTextSize(0f);
            lineDataSet.setDrawFilled(true);
            lineDataSet.setFormLineWidth(1f);
            lineDataSet.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            lineDataSet.setFormSize(15.f);

            lineDataSet.setFillColor(Color.BLUE);
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(lineDataSet);

            LineData lineData = new LineData(dataSets);

            mHeartRateLineChart.setData(lineData);
        }
    }


    private void initViews(){
        //TODO: init views here
        mHeartRateLineChart = (findViewById(R.id.lineChartHeartRate));
        mHeartRateLineChart.setTouchEnabled(true);
        mHeartRateLineChart.setPinchZoom(true);
    }
}