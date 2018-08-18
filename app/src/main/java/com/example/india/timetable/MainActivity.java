package com.example.india.timetable;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String days[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private Spinner spinner;
    private Calendar calendar;
    private int s;
    private RecyclerView recyclerView;
    private myAdapter adapter;
    private List<ListData> list;
    private SQLiteDatabase database;
    private DatabaseManagement databaseManagement;
    private int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        database = openOrCreateDatabase("data", MODE_PRIVATE, null);
        databaseManagement = new DatabaseManagement(database);

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

        day = s;


        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        list = new ArrayList<>();
    }

    @Override
    protected void onResume() {
        spinner.setSelection(s);
        super.onResume();
    }

    @Override
    protected void onPause() {
        if (s == 5) {
            spinner.setSelection(s - 1);
        } else {
            spinner.setSelection(s + 1);
        }
        super.onPause();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                list = databaseManagement.showAll(days[position]);
                adapter = new myAdapter(list);
                recyclerView.setAdapter(adapter);
                day = position;
                break;
            case 1:
                list = databaseManagement.showAll(days[position]);
                adapter = new myAdapter(list);
                recyclerView.setAdapter(adapter);
                day = position;
                break;
            case 2:
                list = databaseManagement.showAll(days[position]);
                adapter = new myAdapter(list);
                recyclerView.setAdapter(adapter);
                day = position;
                break;
            case 3:
                list = databaseManagement.showAll(days[position]);
                adapter = new myAdapter(list);
                recyclerView.setAdapter(adapter);
                day = position;
                break;
            case 4:
                list = databaseManagement.showAll(days[position]);
                adapter = new myAdapter(list);
                recyclerView.setAdapter(adapter);
                day = position;
                break;
            case 5:
                list = databaseManagement.showAll(days[position]);
                adapter = new myAdapter(list);
                recyclerView.setAdapter(adapter);
                day = position;
                break;
        }
    }

    public void today(View v) {

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
                intent.putExtra("day", day);
                startActivity(intent);
                overridePendingTransition(0, 0);
                break;
            case R.id.today:
                if (s >= 0) {
                    spinner.setSelection(s);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
