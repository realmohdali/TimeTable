package com.example.india.timetable;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class add extends AppCompatActivity {

    private String time;
    private TextView frm, t;
    private EditText sub;
    private DatabaseManagement databaseManagement;
    private String days[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private int s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Add");

        Intent intent = getIntent();
        s = intent.getExtras().getInt("day");

        frm = findViewById(R.id.from);
        t = findViewById(R.id.to);
        sub = findViewById(R.id.sub);

        time = "";

        SQLiteDatabase database = openOrCreateDatabase("data", MODE_PRIVATE, null);
        databaseManagement = new DatabaseManagement(database);
    }

    public void from(View v) {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        TimePickerDialog pickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String from = "";
                String am_pm;
                if (hourOfDay < 12) {
                    am_pm = "AM";
                } else {
                    am_pm = "PM";
                }
                if (hourOfDay > 12) {
                    hourOfDay = hourOfDay - 12;
                }
                time += String.valueOf(hourOfDay) + ":";
                from += String.valueOf(hourOfDay) + ":";
                if (minute < 10) {
                    time += "0" + String.valueOf(minute);
                    from += "0" + String.valueOf(minute);
                } else {
                    time += String.valueOf(minute);
                    from += String.valueOf(minute);
                }
                time += am_pm;
                from += am_pm;

                frm.setText(from);

                time += " To ";
            }
        }, hour, minute, false);
        pickerDialog.setTitle(R.string.from);
        pickerDialog.show();
    }

    public void to(View v) {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        TimePickerDialog pickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String to = "";
                String am_pm;
                if (hourOfDay < 12) {
                    am_pm = "AM";
                } else {
                    am_pm = "PM";
                }
                if (hourOfDay > 12) {
                    hourOfDay = hourOfDay - 12;
                }
                time += String.valueOf(hourOfDay) + ":";
                to += String.valueOf(hourOfDay) + ":";

                if (minute < 10) {
                    time += "0" + String.valueOf(minute);
                    to += "0" + String.valueOf(minute);
                } else {
                    time += String.valueOf(minute);
                    to += String.valueOf(minute);
                }
                time += am_pm;
                to += am_pm;
                t.setText(to);
            }
        }, hour, minute, false);
        pickerDialog.setTitle(R.string.to);
        pickerDialog.show();
    }

    public void save(View v) {
        String subject = sub.getText().toString();
        if (subject.equals("") || subject == null || subject.isEmpty()) {
            Toast.makeText(this, "Don't leave any field blank", Toast.LENGTH_SHORT).show();
        } else {
            String table = days[s];
            databaseManagement.insert(table, time, subject);
            sub.setText("");
            frm.setText(R.string.from);
            t.setText(R.string.to);
            Toast.makeText(this, "Successfully added", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(0, 0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
