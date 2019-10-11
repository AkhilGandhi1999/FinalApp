package com.example.finalapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;

import java.util.ArrayList;
import java.util.List;

public class MedDes extends AppCompatActivity {

    TextView t1, t2;
    ImageView i1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_des);

        //ActionBar actionBar = getSupportActionBar();
        init();

        Intent intent = getIntent();
        String ititle = intent.getStringExtra("title");
        String ides = intent.getStringExtra("des");
        byte[] mbytes = getIntent().getByteArrayExtra("image");


        findViewById(R.id.delete_node).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Bitmap bitmap = BitmapFactory.decodeByteArray(mbytes, 0, mbytes.length);

        //   actionBar.setTitle(ititle);

        t1.setText(ititle);
        t2.setText(ides);
        i1.setImageBitmap(bitmap);

        AnyChartView anyChartView = findViewById(R.id.any_chart_view);

        Cartesian cartesian = AnyChart.column();

        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("Day 1", 0));
        data.add(new ValueDataEntry("Day 2 ", 1));
        data.add(new ValueDataEntry("Day 3", 1));
        data.add(new ValueDataEntry("Day 4", 0));

        data.add(new ValueDataEntry("Day 5", 1));

        data.add(new ValueDataEntry("Day 6", 0));
        data.add(new ValueDataEntry("Day 7", 1));
        data.add(new ValueDataEntry("Day 8", 0));

        data.add(new ValueDataEntry("Day 9", 1));
        data.add(new ValueDataEntry("Day 10", 0));
        data.add(new ValueDataEntry("Day 11", 1));


        Column column = cartesian.column(data);

        column.tooltip()
                .titleFormat("{%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .offsetX(0d)
                .offsetY(0d)
                .format("{%Value}");

        cartesian.animation(true);
        cartesian.title("Your Intake Record");

        //  cartesian.yScale().minimum(0);

        cartesian.yAxis(0).labels().format("{%Value}");

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.interactivity().hoverMode(HoverMode.BY_X);

        cartesian.xAxis(0).title("Days");
        cartesian.yAxis(0).title("Consumed");

        anyChartView.setChart(cartesian);

    }

    private void init() {
        t1 = (TextView) findViewById(R.id.des_txt1);
        t2 = (TextView) findViewById(R.id.des_txt2);
        i1 = (ImageView) findViewById(R.id.img_des1);
    }
}
