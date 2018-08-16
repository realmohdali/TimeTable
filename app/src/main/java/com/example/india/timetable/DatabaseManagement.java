package com.example.india.timetable;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManagement {
    private SQLiteDatabase database;

    DatabaseManagement(SQLiteDatabase database) {
        this.database = database;
        database.execSQL("CREATE TABLE IF NOT EXISTS Monday (_id, time VARCHAR, subject VARCHAR)");
        database.execSQL("CREATE TABLE IF NOT EXISTS Tuesday (_id, time VARCHAR, subject VARCHAR)");
        database.execSQL("CREATE TABLE IF NOT EXISTS Wednesday (_id, time VARCHAR, subject VARCHAR)");
        database.execSQL("CREATE TABLE IF NOT EXISTS Thursday (_id, time VARCHAR, subject VARCHAR)");
        database.execSQL("CREATE TABLE IF NOT EXISTS Friday (_id, time VARCHAR, subject VARCHAR)");
        database.execSQL("CREATE TABLE IF NOT EXISTS Saturday (_id, time VARCHAR, subject VARCHAR)");
    }

    public List<ListData> showAll(String table) {
        List<ListData> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM " + table, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                int id = cursor.getInt(0);
                String time = cursor.getString(1);
                String subject = cursor.getString(2);
                ListData data = new ListData(time, subject, id);
                list.add(data);
            } while (cursor.moveToNext());
        }
        return list;
    }

    public void insert(String table, String time, String subject) {
        database.execSQL("INSERT INTO " + table + " (time, subject) VALUES ('" + time + "','" + subject + "')");
    }

    public void edit(String table, String time, String subject, int id) {
        database.execSQL("UPDATE " + table + " SET time = '" + time + "', subject = '" + subject + "' WHERE _id = '" + id + "'");
    }

    public void remove(String table, int id) {
        database.execSQL("DELETE FROM " + table + " WHERE _id = '" + id + "'");
    }
}
