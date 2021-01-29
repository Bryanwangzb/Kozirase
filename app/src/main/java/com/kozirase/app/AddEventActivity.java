package com.kozirase.app;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;

public class AddEventActivity extends AppCompatActivity {
    public static final String EXTRA_DATE_TIME_HOUR =
            "com.kozirase.app.EXTRA_DATE_TIME_HOUR";
    public static final String EXTRA_DATE_TIME_MINUTE =
            "com.kozirase.app.EXTRA_DATE_TIME_MINUTE";
    public static final String EXTRA_EVENT =
            "com.kozirase.app.EXTRA_EVENT";
    public static final String EXTRA_FIRST_MEMBER =
            "com.kozirase.app.EXTRA_FIRST_MEMBER";
    public static final String EXTRA_SECOND_MEMBER =
            "com.kozirase.app.EXTRA_SECOND_MEMBER";
    public static final String EXTRA_THIRD_MEMBER =
            "com.kozirase.app.EXTRA_THIRD_MEMBER";
    public static final String EXTRA_FOURTH_MEMBER =
            "com.kozirase.app.EXTRA_FOURTH_MEMBER";

    //private EditText editTextDateTime;
    private TimePicker timePickerEventTime;
    private EditText editTextEventName;
    private EditText editTextFirstName;
    private EditText editTextSecondName;
    private EditText editTextThirdName;
    private EditText editTextFourthName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        timePickerEventTime = findViewById(R.id.time_picker_date_time);
        editTextEventName = findViewById(R.id.edit_text_event_name);
        editTextFirstName = findViewById(R.id.edit_text_first_member_name);
        editTextSecondName = findViewById(R.id.edit_text_second_member_name);
        editTextThirdName = findViewById(R.id.edit_text_third_member_name);
        editTextFourthName = findViewById(R.id.edit_text_fourth_member_name);


        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Event");

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void saveEvent() {
        int eventDateTimeHour = timePickerEventTime.getHour();
        int eventDateTimeMinute = timePickerEventTime.getMinute();
        String eventName = editTextEventName.getText().toString();
        String memberFirst = editTextFirstName.getText().toString();
        String memberSecond =editTextSecondName.getText().toString();
        String memberThird = editTextThirdName.getText().toString();
        String memberFourth = editTextFourthName.getText().toString();

        if (eventName.trim().isEmpty() || memberFirst.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a event name and one member", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_DATE_TIME_HOUR,eventDateTimeHour);
        data.putExtra(EXTRA_DATE_TIME_MINUTE,eventDateTimeMinute);
        data.putExtra(EXTRA_EVENT, eventName);
        data.putExtra(EXTRA_FIRST_MEMBER, memberFirst);
        data.putExtra(EXTRA_SECOND_MEMBER, memberSecond);
        data.putExtra(EXTRA_THIRD_MEMBER,memberThird);
        data.putExtra(EXTRA_FOURTH_MEMBER,memberFourth);

        setResult(RESULT_OK, data);
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_event_menu, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_event:
                saveEvent();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}