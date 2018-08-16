package com.example.india.timetable;

public class ListData {
    private String time, subject;
    private int id;

    ListData(String time, String subject, int id) {
        this.time = time;
        this.subject = subject;
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public String getSubject() {
        return subject;
    }

    public int getId() {
        return id;
    }
}
