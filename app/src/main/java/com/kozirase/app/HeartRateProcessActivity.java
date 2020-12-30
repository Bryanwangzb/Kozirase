package com.kozirase.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.kozirase.app.MathConstants.STEP;

public class HeartRateProcessActivity extends AppCompatActivity {
    private LineChart mHeartRateLineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_rate_process);

        initViews();

        mHeartRateLineChart.setDrawGridBackground(true);
        mHeartRateLineChart.getDescription().setEnabled(true);

        XAxis xAxis = mHeartRateLineChart.getXAxis();
        xAxis.enableGridDashedLine(10f, 10f, 0);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);


        YAxis leftAxis = mHeartRateLineChart.getAxisLeft();
        leftAxis.setAxisMinimum(0);
        leftAxis.setAxisMaximum(150f);

        leftAxis.enableGridDashedLine(10f, 10f, 0);
        leftAxis.setDrawZeroLine(true);


        setHeartData();
        mHeartRateLineChart.animateX(2500);



    }


    private void setHeartData() {

        int[] data = getHeartData();


        ArrayList<Entry> values = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            values.add(new Entry(i, data[i], null, null));

        }

        LineDataSet lineDataSet;

        if (mHeartRateLineChart.getData() != null && mHeartRateLineChart.getData().getDataSetCount() > 0) {
            lineDataSet = (LineDataSet) mHeartRateLineChart.getData().getDataSetByIndex(0);
            lineDataSet.setValues(values);
            mHeartRateLineChart.getData().notifyDataChanged();
            mHeartRateLineChart.notifyDataSetChanged();
        } else {
            lineDataSet = new LineDataSet(values, "心拍数");
            lineDataSet.setDrawIcons(false);
            lineDataSet.setColor(Color.BLACK);

            lineDataSet.setCircleColor(Color.BLUE);
            lineDataSet.setLineWidth(1f);
            lineDataSet.setCircleRadius(3f);
            lineDataSet.setDrawCircleHole(false);
            lineDataSet.setValueTextSize(0f);
            lineDataSet.setDrawFilled(true);
            lineDataSet.setFormLineWidth(1f);
            lineDataSet.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            lineDataSet.setFormSize(15.f);

            lineDataSet.setFillColor(R.color.purple_500);
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(lineDataSet);

            LineData lineData = new LineData(dataSets);

            mHeartRateLineChart.setData(lineData);
        }
    }


    @NotNull
    private int[] getHeartData() {

        String jsonFileString = Utils.getJsonFromAssets(getApplicationContext(), "heart_rate-2020-07-29.json");
        Log.i("data", jsonFileString);
        Gson gson = new Gson();
        Type listUserType = new TypeToken<List<HeartRate>>() {
        }.getType();

        List<HeartRate> heartRates = gson.fromJson(jsonFileString, listUserType);

        List<Integer> heartRateList = new ArrayList<Integer>();

        for (int i = 0; i < heartRates.size(); i++) {
            heartRateList.add(heartRates.get(i).getValue().getBpm());
        }
        System.out.println("heart rate:" + heartRateList.size());
        //int[] data = new int[heartRateList.size()];
        //int[] heartSamplingData = new int[heartRateList.size()/100];
        int heartRateListIndex = 0;

        List<Integer> heartSamplingData = new ArrayList<Integer>();
        for (int i = 0; i < heartRateList.size() / STEP; i++) {
            heartSamplingData.add(heartRateList.get(heartRateListIndex));
            heartRateListIndex = heartRateListIndex + STEP;
        }
        int[] data = new int[heartRateList.size() / STEP];
        for (int i = 0; i < data.length; i++) {
            data[i] = heartSamplingData.get(i);
        }

        return data;

    }

    private void initViews() {
        //TODO: init views here
        mHeartRateLineChart = findViewById(R.id.lineChartHeartRate);

        mHeartRateLineChart.setTouchEnabled(true);
        mHeartRateLineChart.setPinchZoom(true);
    }
}