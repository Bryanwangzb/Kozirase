package com.kozirase.app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TextAnalysisAdapter extends RecyclerView.Adapter<TextAnalysisAdapter.TextAnalysisHolder> {
    private List<TextAnalysis> textAnalysis = new ArrayList<>();

    @NonNull
    @Override
    public TextAnalysisHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.text_analysis_item,parent,false);
        return new TextAnalysisHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TextAnalysisHolder holder, int position) {
        TextAnalysis currentTextAnalysis = textAnalysis.get(position);
        holder.textViewMember.setText(currentTextAnalysis.getMemberName());
        holder.textViewDialogue.setText(currentTextAnalysis.getChatContents());
        holder.textViewAnalysisResult.setText(currentTextAnalysis.getAnalysisResult());
    }

    @Override
    public int getItemCount() {
        return textAnalysis.size();
    }

    public void setTextAnalysis(List<TextAnalysis> textAnalyses){
        this.textAnalysis = textAnalyses;
        notifyDataSetChanged();
    }

    class TextAnalysisHolder extends RecyclerView.ViewHolder{
        private TextView textViewMember;
        private TextView textViewDialogue;
        private TextView textViewAnalysisResult;

        public TextAnalysisHolder(@NonNull View itemView) {
            super(itemView);
            textViewMember = itemView.findViewById(R.id.text_member_name);
            textViewDialogue = itemView.findViewById(R.id.text_dialogue_content);
            textViewAnalysisResult = itemView.findViewById(R.id.text_analysis_result);
        }

    }
}
