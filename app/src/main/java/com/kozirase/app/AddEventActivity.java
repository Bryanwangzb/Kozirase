package com.kozirase.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class AddEventActivity extends AppCompatActivity {

    public static final String EXTRA_EVENT =
            "com.kozirase.app.EXTRA_EVENT";
    public static final String EXTRA_FIRST_MEMBER =
            "com.kozirase.app.EXTRA_FIRST_MEMBER";

    private EditText editTextEventName;
    private EditText editTextFirstName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        editTextEventName = findViewById(R.id.edit_text_event_name);
        editTextFirstName = findViewById(R.id.edit_text_first_member_name);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Event");

    }

    private void saveEvent() {
        String eventName = editTextEventName.getText().toString();
        String memberFirst = editTextFirstName.getText().toString();

        if (eventName.trim().isEmpty() || memberFirst.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a event name and one member", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_EVENT, eventName);
        data.putExtra(EXTRA_FIRST_MEMBER, memberFirst);

        setResult(RESULT_OK, data);
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_event_menu, menu);
        return true;
    }

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