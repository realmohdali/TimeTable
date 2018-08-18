package com.example.india.timetable;

public class ListData {
    private String frm, t, subject;
    private int id;

    ListData(String frm, String t, String subject, int id) {
        this.frm = frm;
        this.t = t;
        this.subject = subject;
        this.id = id;
    }

    public String getFrm() {
        return frm;
    }

    public String getT() {
        return t;
    }

    public String getSubject() {
        return subject;
    }

    public int getId() {
        return id;
    }
}
