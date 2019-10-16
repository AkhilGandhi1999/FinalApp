package com.example.finalapp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class GraphFragment extends Fragment {

    private View view;
    private EditText editText;
    private Button bt1;
    private Button save_bt;
    private Switch aSwitch;
    private TextView textView ;

    private String text,message,tak,dd;
    private Boolean onoffswitch;

    Model m = new Model();
    Model m2 = new Model();

    public static final String ARRAY_LIST = "ArrayList";


    int i=0,t=0,nt=0;

    ArrayList<Model> models = new ArrayList<>();
    ArrayList<Model> models2 = new ArrayList<>();


    //  ArrayList<Model> models1;

    RecyclerView recyclerView;
    MyAdaptor myAdaptor;

    DatabaseReference mod;
    DatabaseReference get_mod;
    Context thiscontext;

    public static final String SHARED_PEF = "sharedprefs";
    public static final String TEXT = "text";
    public static final String Switch1 = "switch1";

    public GraphFragment() {

    }

    public GraphFragment(String m,String d,String t) {
        mod = FirebaseDatabase.getInstance().getReference("final_up");

        message = m;
        dd = d;
        tak = t;
        set_all();
     }

     public void set_all()
     {
         //m = new Model();
        // recyclerView = recyclerView.findViewById(R.id.new_top);
         //recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
         models = new ArrayList<Model>();
       //  models1 = new ArrayList<>();
         models = getList(message,dd,tak);
         saveData();

     }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_graph, null);
        mod = FirebaseDatabase.getInstance().getReference("final_up");
        get_mod = FirebaseDatabase.getInstance().getReference("final_up");

         thiscontext = container.getContext();
        recyclerView = view.findViewById(R.id.new_top);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));

        LoadData();


        return view ;
    }
    private ArrayList<Model> getList(String title, String des,String start_end) {
        m = new Model();
        m.setTitle(title);
        m.setDescription(des);
        m.setStart_e(start_end);
        m.setImg(R.drawable.medicine);
        models.add(m);
        return models;
    }

    public void saveData()
    {

        Gson gson = new Gson();
        String json = gson.toJson(models);
        String id = mod.push().getKey();
        mod.child(id).setValue(json);
       // editor.putString(ARRAY_LIST, json);
        //editor.apply();

    }

    public void LoadData()
    {

        get_mod.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot m : dataSnapshot.getChildren())
                {
                    String json1 = m.getValue(String.class);
                    Gson gson = new Gson();
                    Type type = new TypeToken<ArrayList<Model>>() {
                    }.getType();
                    models = gson.fromJson(json1, type);
                    m2 = models.get(0);
                    if(m2.getStart_e().equals("TAKEN"))
                    {
                        t++;
                    }
                    else
                    {
                        nt++;
                    }
                    i++;
                    models2.add(m2);
                    Log.i("fff",Integer.toString(models.size()));

                    //  Toast.makeText(getContext(),models.toString(),Toast.LENGTH_LONG).show();
                }
                myAdaptor = new MyAdaptor(getContext(), models2);
                recyclerView.setAdapter(myAdaptor);
                Log.i("final",Integer.toString(t) + " " + Integer.toString(nt));
                Pie pie = AnyChart.pie();
                float tot = t + nt;
                float t1 = t/tot;
                float nt1 = nt/tot;

                List<DataEntry> data = new ArrayList<>();
                data.add(new ValueDataEntry("TAKEN", t1));
                data.add(new ValueDataEntry("NOT TAKEN", nt1));
              //  data.add(new ValueDataEntry("Peter", 18000));

                pie.data(data);

                AnyChartView anyChartView = (AnyChartView) view.findViewById(R.id.any_chart_view);
                anyChartView.setChart(pie);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
