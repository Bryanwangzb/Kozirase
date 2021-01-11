package com.kozirase.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import java.text.DecimalFormat;

public class HeartRateMarkerView extends MarkerView {


    private TextView mXValueTv;
    private TextView mYValueTv;
    private DecimalFormat df = new DecimalFormat("##0");

    public HeartRateMarkerView(Context context) {
        super(context, R.layout.activity_heart_rate_markview);

        mXValueTv = findViewById(R.id.xValues_tv);
        mYValueTv = findViewById(R.id.yValue_tv);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        //mXValueTv.setText("X = " + df.format(e.getX()));
        mYValueTv.setText("心拍数: " + df.format(e.getY()));
        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }


}
