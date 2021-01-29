package com.kozirase.app;


import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventHolder> {
    private List<Event> events = new ArrayList<>();

    @NonNull
    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_item, parent, false);
        return new EventHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull EventHolder holder, int position) {
        Event currentEvent = events.get(position);

        holder.textViewDateTime.setText(String.valueOf(currentEvent.getEventHour()) + ":"+String.valueOf(currentEvent.getEventMinute()));
        holder.textViewEventName.setText(currentEvent.getEventName());
        holder.textViewMember1.setText(currentEvent.getFirstMember());
        holder.textViewMember2.setText(currentEvent.getSecondMember());
        holder.textViewMember3.setText(currentEvent.getThirdMember());
        holder.textViewMember4.setText(currentEvent.getFourthMember());

    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public void setEvents(List<Event> events) {
        this.events = events;
        notifyDataSetChanged();
    }

    public Event getEventAt(int position) {
        return events.get(position);
    }

    class EventHolder extends RecyclerView.ViewHolder {
        private TextView textViewDateTime;
        private TextView textViewEventName;
        private TextView textViewMember1;
        private TextView textViewMember2;
        private TextView textViewMember3;
        private TextView textViewMember4;

        public EventHolder(@NonNull View itemView) {
            super(itemView);
            textViewDateTime = itemView.findViewById(R.id.text_view_date_time);
            textViewEventName = itemView.findViewById(R.id.text_view_event);
            textViewMember1 = itemView.findViewById(R.id.text_view_member1);
            textViewMember2 = itemView.findViewById(R.id.text_view_member2);
            textViewMember3 = itemView.findViewById(R.id.text_view_member3);
            textViewMember4 = itemView.findViewById(R.id.text_view_member4);
        }
    }
}
