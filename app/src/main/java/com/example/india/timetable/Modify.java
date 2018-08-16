package com.example.india.timetable;

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
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class Modify extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String days[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private Spinner spinner;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        spinner = findViewById(R.id.spinner);
        ArrayAdapter spinnerAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, days);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(this);

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);

        List<ListData> list = new ArrayList<>();

        //Generate Dummy data
        for (int i = 0; i < 10; i++) {
            ListData listData = new ListData("8:00AM To 10AM", "Some Subject");
            list.add(listData);
        }
        RecyclerView.Adapter adapter = new ModifyAdapter(list, Modify.this);
        recyclerView.setAdapter(adapter);
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
        Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
