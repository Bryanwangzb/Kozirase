package com.kozirase.app;


import android.annotation.SuppressLint;
import android.os.Build;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventHolder> {
    private List<Event> events = new ArrayList<>();
    private OnItemClickListener listener;


    @NonNull
    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_item, parent, false);
        return new EventHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull EventHolder holder, int position) {
        Event currentEvent = events.get(position);

        holder.textViewDateTime.setText(currentEvent.getEventHour() + ":" + currentEvent.getEventMinute());
        holder.textViewEventName.setText(currentEvent.getEventName());
        holder.textViewMember1.setText(currentEvent.getFirstMember());
        holder.textViewMember2.setText(currentEvent.getSecondMember());
        holder.textViewMember3.setText(currentEvent.getThirdMember());
        holder.textViewMember4.setText(currentEvent.getFourthMember());

        if (currentEvent.isExpanded()) {
            TransitionManager.beginDelayedTransition(holder.eventParent);
            holder.expandedRelLayout.setVisibility(View.VISIBLE);
            holder.downArrow.setVisibility(View.GONE);
        } else {
            TransitionManager.beginDelayedTransition(holder.eventParent);
            holder.expandedRelLayout.setVisibility(View.GONE);
            holder.downArrow.setVisibility(View.VISIBLE);
        }

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
        private CardView eventParent;
        private RelativeLayout expandedRelLayout;
        private ImageView upArrow;
        private ImageView downArrow;


        public EventHolder(@NonNull View itemView) {
            super(itemView);
            textViewDateTime = itemView.findViewById(R.id.text_view_date_time);
            textViewEventName = itemView.findViewById(R.id.text_view_event);
            textViewMember1 = itemView.findViewById(R.id.text_view_member1);
            textViewMember2 = itemView.findViewById(R.id.text_view_member2);
            textViewMember3 = itemView.findViewById(R.id.text_view_member3);
            textViewMember4 = itemView.findViewById(R.id.text_view_member4);
            expandedRelLayout = itemView.findViewById(R.id.expandedRelLayout);
            eventParent = itemView.findViewById(R.id.event_parent);
            upArrow = itemView.findViewById(R.id.btn_up_arrow);
            downArrow = itemView.findViewById(R.id.btn_down_arrow);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(events.get(position));
                    }
                }
            });

            downArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Event event = events.get(getAdapterPosition());
                    event.setExpanded(!event.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });

            upArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Event event = events.get(getAdapterPosition());
                    event.setExpanded(!event.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });

        }
    }

    public interface OnItemClickListener {
        void onItemClick(Event event);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
