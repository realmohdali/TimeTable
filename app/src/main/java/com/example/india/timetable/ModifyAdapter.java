package com.example.india.timetable;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.List;

class ModifyAdapter extends RecyclerView.Adapter<ModifyAdapter.ViewHolder> {
    private List<ListData> list;
    private Context context;
    private String time;
    private SQLiteDatabase database;
    private int day;
    private String days[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private DatabaseManagement databaseManagement;

    public ModifyAdapter(List<ListData> list, Context context, SQLiteDatabase database, int day) {
        this.list = list;
        this.context = context;
        time = "";
        this.database = database;
        this.day = day;
        databaseManagement = new DatabaseManagement(database);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.time.setText(list.get(position).getTime());
        holder.subject.setText(list.get(position).getSubject());
        final String subject = list.get(position).getSubject();
        final int id = list.get(position).getId();

        holder.time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mCurrentTime = Calendar.getInstance();
                final int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mCurrentTime.get(Calendar.MINUTE);
                TimePickerDialog pickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String am_pm;
                        if (hourOfDay < 12) {
                            am_pm = "AM";
                        } else {
                            am_pm = "PM";
                        }
                        if (hourOfDay > 12) {
                            hourOfDay = hourOfDay - 12;
                        }
                        if (hourOfDay < 10) {
                            time += "0" + String.valueOf(hourOfDay) + ":";
                        } else {
                            time += String.valueOf(hourOfDay) + ":";
                        }
                        if (minute < 10) {
                            time += "0" + String.valueOf(minute);
                        } else {
                            time += String.valueOf(minute);
                        }
                        time += am_pm;
                        time += " To ";
                    }
                }, hour, minute, false);
                pickerDialog.setTitle("From");
                pickerDialog.show();

                TimePickerDialog pickerDialog1 = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String am_pm;
                        if (hourOfDay < 12) {
                            am_pm = "AM";
                        } else {
                            am_pm = "PM";
                        }
                        if (hourOfDay > 12) {
                            hourOfDay = hourOfDay - 12;
                        }
                        if (hourOfDay < 10) {
                            time += "0" + String.valueOf(hourOfDay) + ":";
                        } else {
                            time += String.valueOf(hourOfDay) + ":";
                        }
                        if (minute < 10) {
                            time += "0" + String.valueOf(minute);
                        } else {
                            time += String.valueOf(minute);
                        }
                        time += am_pm;

                        String table = days[day];
                        databaseManagement.edit(table, time, subject, id);

                        holder.time.setText(time);
                    }
                }, hour, minute, false);
                pickerDialog1.setTitle("To");
                pickerDialog1.show();
            }
        });
        holder.subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Subject");
                final EditText input = new EditText(context);
                builder.setView(input);

                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String table = days[day];
                        if (time == "") {
                            list.get(position).getTime();
                        }
                        databaseManagement.edit(table, time, subject, id);
                        holder.subject.setText(input.getText().toString());
                    }
                });

                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView time, subject;

        public ViewHolder(View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.time);
            subject = itemView.findViewById(R.id.subject);
        }
    }
}
