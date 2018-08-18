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

    private String from;
    private String to;
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

        Intent intent = getIntent();
        s = intent.getExtras().getInt("day");

        getSupportActionBar().setTitle(days[s]);

        frm = findViewById(R.id.from);
        t = findViewById(R.id.to);
        sub = findViewById(R.id.sub);

        from = frm.getText().toString();
        to = t.getText().toString();

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
                from = "";
                String am_pm;
                if (hourOfDay < 12) {
                    am_pm = "AM";
                } else {
                    am_pm = "PM";
                }
                if (hourOfDay > 12) {
                    hourOfDay = hourOfDay - 12;
                }
                from += String.valueOf(hourOfDay) + ":";
                if (minute < 10) {
                    from += "0" + String.valueOf(minute);
                } else {
                    from += String.valueOf(minute);
                }
                from += am_pm;

                frm.setText(from);

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
                to = "";
                String am_pm;
                if (hourOfDay < 12) {
                    am_pm = "AM";
                } else {
                    am_pm = "PM";
                }
                if (hourOfDay > 12) {
                    hourOfDay = hourOfDay - 12;
                }
                to += String.valueOf(hourOfDay) + ":";

                if (minute < 10) {
                    to += "0" + String.valueOf(minute);
                } else {
                    to += String.valueOf(minute);
                }
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
            databaseManagement.insert(table, from, to, subject);
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
