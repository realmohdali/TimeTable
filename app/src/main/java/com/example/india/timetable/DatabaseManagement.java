package com.example.india.timetable;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManagement {
    private SQLiteDatabase database;

    DatabaseManagement(SQLiteDatabase database) {
        this.database = database;
        database.execSQL("CREATE TABLE IF NOT EXISTS Monday (_id INTEGER PRIMARY KEY AUTOINCREMENT, frm VARCHAR, t VARCHAR, subject VARCHAR)");
        database.execSQL("CREATE TABLE IF NOT EXISTS Tuesday (_id INTEGER PRIMARY KEY AUTOINCREMENT, frm VARCHAR, t VARCHAR, subject VARCHAR)");
        database.execSQL("CREATE TABLE IF NOT EXISTS Wednesday (_id INTEGER PRIMARY KEY AUTOINCREMENT, frm VARCHAR, t VARCHAR, subject VARCHAR)");
        database.execSQL("CREATE TABLE IF NOT EXISTS Thursday (_id INTEGER PRIMARY KEY AUTOINCREMENT, frm VARCHAR, t VARCHAR, subject VARCHAR)");
        database.execSQL("CREATE TABLE IF NOT EXISTS Friday (_id INTEGER PRIMARY KEY AUTOINCREMENT, frm VARCHAR, t VARCHAR, subject VARCHAR)");
        database.execSQL("CREATE TABLE IF NOT EXISTS Saturday (_id INTEGER PRIMARY KEY AUTOINCREMENT, frm VARCHAR, t VARCHAR, subject VARCHAR)");
    }

    public List<ListData> showAll(String table) {
        List<ListData> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM " + table, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                int id = cursor.getInt(0);
                String frm = cursor.getString(1);
                String t = cursor.getString(2);
                String subject = cursor.getString(3);
                ListData data = new ListData(frm, t, subject, id);
                list.add(data);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public void insert(String table, String from, String to, String subject) {
        database.execSQL("INSERT INTO " + table + " (frm, t, subject) VALUES ('" + from + "','" + to + "','" + subject + "')");
    }

    public void edit(String table, String from, String to, String subject, int id) {
        database.execSQL("UPDATE " + table + " SET frm = '" + from + "', t = '" + to + "', subject = '" + subject + "' WHERE _id = '" + id + "'");
    }

    public void remove(String table, int id) {
        database.execSQL("DELETE FROM " + table + " WHERE _id = '" + id + "'");
    }
}
