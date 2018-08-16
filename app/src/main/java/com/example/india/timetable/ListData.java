package com.example.india.timetable;

public class ListData {
    private String time, subject;

    ListData(String time, String subject) {
        this.time = time;
        this.subject = subject;
    }

    public String getTime() {
        return time;
    }

    public String getSubject() {
        return subject;
    }
}
