package com.example.india.timetable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String days[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private Spinner spinner;
    private Calendar calendar;
    private int s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        calendar = Calendar.getInstance();

        s = calendar.get(Calendar.DAY_OF_WEEK) - 2;

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>
                (this, R.layout.spinner_item, days);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);

        if (s >= 0) {
            spinner.setSelection(s);
        }


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        List<ListData> list = new ArrayList<>();

        //Generate Dummy data
        for (int i = 0; i < 10; i++) {
            ListData listData = new ListData("8:00AM To 10AM", "Some Subject");
            list.add(listData);
        }

        RecyclerView.Adapter adapter = new myAdapter(list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                Toast.makeText(this, "" + days[position], Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(this, "" + days[position], Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this, "" + days[position], Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(this, "" + days[position], Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(this, "" + days[position], Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Toast.makeText(this, "" + days[position], Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void today(View v) {
        if (s >= 0) {
            spinner.setSelection(s);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit:
                Intent intent = new Intent(this, Modify.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
        }
        return super.onOptionsItemSelected(item);
    }
}
