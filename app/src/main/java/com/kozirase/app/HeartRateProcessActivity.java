package com.kozirase.app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.kozirase.app.MathConstants.STEP;
import static com.kozirase.app.MathConstants.TIME_COUNT;

public class HeartRateProcessActivity extends AppCompatActivity {

    public static final int ADD_EVENT_REQUEST = 1;
    private LineChart mHeartRateLineChart;
    ArrayList<String> x_values = new ArrayList<String>();
    private String heartRateFileName = "heart_rate-2020-08-01.json";
    private EventViewModel eventViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_rate_process);

        initViews();


        setHeartData();
        axisSetting(mHeartRateLineChart);

        HeartRateMarkerView mv = new HeartRateMarkerView(this);
        mHeartRateLineChart.setMarker(mv);
        mHeartRateLineChart.invalidate();


        mHeartRateLineChart.animateX(2500);

        Button buttonAddEvent = findViewById(R.id.btn_add_event);
        buttonAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HeartRateProcessActivity.this, AddEventActivity.class);
                startActivityForResult(intent, ADD_EVENT_REQUEST);

            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view_event);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        EventAdapter adapter = new EventAdapter();
        recyclerView.setAdapter(adapter);


        eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);
        eventViewModel.getAllEvents().observe(this, new Observer<List<Event>>() {
            @Override
            public void onChanged(List<Event> events) {
                // update RecyclerView
                adapter.setEvents(events);
                System.out.println(events.size());
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
               eventViewModel.delete(adapter.getEventAt(viewHolder.getAdapterPosition()));
               Toast.makeText(HeartRateProcessActivity.this,"Event deleted",Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);



        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_EVENT_REQUEST && resultCode == RESULT_OK){
            String event_date_time = data.getStringExtra(AddEventActivity.EXTRA_DATE_TIME);
            String event_name = data.getStringExtra(AddEventActivity.EXTRA_EVENT);
            String firstMember = data.getStringExtra(AddEventActivity.EXTRA_FIRST_MEMBER);
            String secondMember = data.getStringExtra(AddEventActivity.EXTRA_SECOND_MEMBER);
            String thirdMember = data.getStringExtra(AddEventActivity.EXTRA_THIRD_MEMBER);
            String fourthMember = data.getStringExtra(AddEventActivity.EXTRA_FOURTH_MEMBER);

            Event event = new Event(event_date_time,event_name,firstMember,secondMember,thirdMember,fourthMember);
            eventViewModel.insert(event);

            Toast.makeText(this,"Event saved",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Event not saved",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.delete_all_events:
                eventViewModel.deleteAllEvents();
                Toast.makeText(this,"All Events deleted",Toast.LENGTH_SHORT).show();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setHeartData() {

        int[] data = getHeartData();


        ArrayList<Entry> values = new ArrayList<>();
        System.out.println("Size:" + data.length);
        for (int i = 0; i < data.length; i++) {
            values.add(new Entry(i, data[i], null, null));
            x_values.add(Integer.toString(i));  // list to save x axis values.

        }

        LineDataSet lineDataSet;

        if (mHeartRateLineChart.getData() != null && mHeartRateLineChart.getData().getDataSetCount() > 0) {
            lineDataSet = (LineDataSet) mHeartRateLineChart.getData().getDataSetByIndex(0);
            lineDataSet.setValues(values);
            mHeartRateLineChart.getData().notifyDataChanged();
            mHeartRateLineChart.notifyDataSetChanged();
        } else {
            lineDataSet = new LineDataSet(values, "Heart beat");
            lineDataSet.setDrawIcons(false);
            lineDataSet.setColor(Color.BLUE);
            lineDataSet.setDrawCircles(false);
            lineDataSet.setCircleColor(Color.BLUE);
            lineDataSet.setLineWidth(1f);
            lineDataSet.setCircleRadius(2);
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

        String jsonFileString = Utils.getJsonFromAssets(getApplicationContext(), heartRateFileName);
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


    private void axisSetting(LineChart lineChart) {

        float heartRateMin = lineChart.getYMin();
        float heartRateMax = lineChart.getYMax();
        int minBias = 5;
        int maxBias = 5;

        lineChart.setDrawGridBackground(true);
        lineChart.getDescription().setEnabled(true);

        Description description = new Description();
        description.setText("時間");
        description.setTextSize(12);
        lineChart.setDescription(description);
        Legend legend = lineChart.getLegend();
        // hide legend
        legend.setEnabled(false);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setLabelCount(TIME_COUNT, true);
        xAxis.enableGridDashedLine(10f, 10f, 0);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new ClaimsXAxisValueFormatter(x_values));


        YAxis leftAxis = lineChart.getAxisLeft();
        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setEnabled(false);


        leftAxis.setAxisMinimum(heartRateMin - minBias);
        leftAxis.setAxisMaximum(heartRateMax + maxBias); // Heart beat maximum value


        leftAxis.enableGridDashedLine(10f, 10f, 0);
        leftAxis.setDrawZeroLine(true);

    }

    private void initViews() {
        //TODO: init views here
        mHeartRateLineChart = findViewById(R.id.lineChartHeartRate);

        mHeartRateLineChart.setTouchEnabled(true);
        mHeartRateLineChart.setPinchZoom(true);
        mHeartRateLineChart.fitScreen();
    }
}