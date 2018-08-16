package com.example.india.timetable;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

class ModifyAdapter extends RecyclerView.Adapter<ModifyAdapter.ViewHolder> {
    private List<ListData> list;
    private Context context;

    public ModifyAdapter(List<ListData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.time.setText(list.get(position).getTime());
        holder.subject.setText(list.get(position).getSubject());

        holder.time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mCurrentTime = Calendar.getInstance();
                final int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mCurrentTime.get(Calendar.MINUTE);
                TimePickerDialog pickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String time = "";
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
                        holder.time.setText(time);
                    }
                }, hour, minute, false);
                pickerDialog.setTitle("Time");
                pickerDialog.show();
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
