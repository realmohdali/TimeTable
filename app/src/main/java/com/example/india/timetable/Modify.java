package com.example.india.timetable;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
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
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class Modify extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String days[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private Spinner spinner;
    private RecyclerView recyclerView;
    private int s;
    private ModifyAdapter adapter;
    private List<ListData> list;
    private SQLiteDatabase database;
    private DatabaseManagement databaseManagement;
    private int selectedDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        database = openOrCreateDatabase("data", MODE_PRIVATE, null);
        databaseManagement = new DatabaseManagement(database);

        spinner = findViewById(R.id.spinner);
        ArrayAdapter spinnerAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, days);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(this);

        Intent intent = getIntent();
        s = intent.getExtras().getInt("day");

        if (s >= 0) {
            spinner.setSelection(s);
        }

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);

        list = new ArrayList<>();
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
    protected void onResume() {
        spinner.setSelection(s);
        super.onResume();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(0, 0);
        }
        return super.onOptionsItemSelected(item);
    }

    public void add(View view) {
        Intent intent = new Intent(this, add.class);
        intent.putExtra("day", selectedDay);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                list = databaseManagement.showAll(days[position]);
                adapter = new ModifyAdapter(list, Modify.this, database, position);
                recyclerView.setAdapter(adapter);
                selectedDay = position;
                break;
            case 1:
                list = databaseManagement.showAll(days[position]);
                adapter = new ModifyAdapter(list, Modify.this, database, position);
                recyclerView.setAdapter(adapter);
                selectedDay = position;
                break;
            case 2:
                list = databaseManagement.showAll(days[position]);
                adapter = new ModifyAdapter(list, Modify.this, database, position);
                recyclerView.setAdapter(adapter);
                selectedDay = position;
                break;
            case 3:
                list = databaseManagement.showAll(days[position]);
                adapter = new ModifyAdapter(list, Modify.this, database, position);
                recyclerView.setAdapter(adapter);
                selectedDay = position;
                break;
            case 4:
                list = databaseManagement.showAll(days[position]);
                adapter = new ModifyAdapter(list, Modify.this, database, position);
                recyclerView.setAdapter(adapter);
                selectedDay = position;
                break;
            case 5:
                list = databaseManagement.showAll(days[position]);
                adapter = new ModifyAdapter(list, Modify.this, database, position);
                recyclerView.setAdapter(adapter);
                selectedDay = position;
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
