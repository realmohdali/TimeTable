package com.example.india.timetable;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {
    private List<ListData> list;

    myAdapter(List<ListData> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.time.setText(list.get(position).getTime());
        holder.subject.setText(list.get(position).getSubject());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView time, subject;

        ViewHolder(View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.time);
            subject = itemView.findViewById(R.id.subject);
        }
    }
}
