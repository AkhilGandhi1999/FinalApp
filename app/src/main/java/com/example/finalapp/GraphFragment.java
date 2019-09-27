package com.example.finalapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import java.util.ArrayList;
import java.util.List;


public class GraphFragment extends Fragment {

    private View view;
    private EditText editText;
    private Button bt1;
    private Button save_bt;
    private Switch aSwitch;
    private TextView textView ;

    private String text;
    private Boolean onoffswitch;


    public static final String SHARED_PEF = "sharedprefs";
    public static final String TEXT = "text";
    public static final String Switch1 = "switch1";


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_graph, null);


        Pie pie = AnyChart.pie();

        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("John", 10000));
        data.add(new ValueDataEntry("Jake", 12000));
        data.add(new ValueDataEntry("Peter", 18000));

        pie.data(data);

        AnyChartView anyChartView = (AnyChartView) view.findViewById(R.id.any_chart_view);
        anyChartView.setChart(pie);
     /*   editText = view.findViewById(R.id.input);
        textView = view.findViewById(R.id.dis_text);
        aSwitch = view.findViewById(R.id.switch_1);
        bt1 = view.findViewById(R.id.apply);
                bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    textView.setText(editText.getText().toString());
            }
        });
        save_bt = view.findViewById(R.id.save_Data);
        save_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

        LoadData();
        update_views();*/

        return view ;
    }

    public void saveData()
    {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PEF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(TEXT,textView.getText().toString());
        editor.putBoolean(Switch1,aSwitch.isChecked());
        editor.apply();

        Toast.makeText(getContext(),"Saved",Toast.LENGTH_LONG).show();

    }

    public void LoadData()
    {
        SharedPreferences sharedPreferences1 = this.getActivity().getSharedPreferences(SHARED_PEF,Context.MODE_PRIVATE);
        text = sharedPreferences1.getString(TEXT,"");
        onoffswitch = sharedPreferences1.getBoolean(Switch1,false);
    }

    public void  update_views()
    {
        textView.setText(text);
        aSwitch.setChecked(onoffswitch);
    }

}
