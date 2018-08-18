package com.example.india.timetable;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.List;

class ModifyAdapter extends RecyclerView.Adapter<ModifyAdapter.ViewHolder> {
    private List<ListData> list;
    private Context context;
    private SQLiteDatabase database;
    private String time;
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
        holder.frm.setText(list.get(position).getFrm());
        holder.to.setText(list.get(position).getT());
        holder.subject.setText(list.get(position).getSubject());
        final String subject = list.get(position).getSubject();
        final int id = list.get(position).getId();
        holder.del.setVisibility(View.VISIBLE);

        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delete(position, id);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });

        holder.frm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time = "";
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
                        time += String.valueOf(hourOfDay) + ":";
                        if (minute < 10) {
                            time += "0" + String.valueOf(minute);
                        } else {
                            time += String.valueOf(minute);
                        }
                        time += am_pm;
                        holder.frm.setText(time);

                        String table = days[day];
                        String to = holder.to.getText().toString();
                        databaseManagement.edit(table, time, to, subject, id);

                    }
                }, hour, minute, false);
                pickerDialog.setTitle("From");
                pickerDialog.show();
            }
        });
        holder.to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time = "";
                Calendar mCurrentTime = Calendar.getInstance();
                final int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mCurrentTime.get(Calendar.MINUTE);


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
                        time += String.valueOf(hourOfDay) + ":";
                        if (minute < 10) {
                            time += "0" + String.valueOf(minute);
                        } else {
                            time += String.valueOf(minute);
                        }
                        time += am_pm;

                        holder.to.setText(time);

                        String table = days[day];
                        String frm = holder.frm.getText().toString();
                        databaseManagement.edit(table, frm, time, subject, id);
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
                input.setText(list.get(position).getSubject());
                builder.setView(input);

                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String table = days[day];
                        String frm = holder.frm.getText().toString();
                        String t = holder.to.getText().toString();
                        String sub = input.getText().toString();
                        holder.subject.setText(input.getText().toString());
                        databaseManagement.edit(table, frm, t, sub, id);
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

    private void delete(int pos, int id) {
        String table = days[day];
        list.remove(pos);
        databaseManagement.remove(table, id);
        notifyItemRemoved(pos);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView frm, to, subject;
        ImageView del;

        public ViewHolder(View itemView) {
            super(itemView);
            frm = itemView.findViewById(R.id.frm);
            to = itemView.findViewById(R.id.to);
            subject = itemView.findViewById(R.id.subject);
            del = itemView.findViewById(R.id.del);
        }
    }
}
