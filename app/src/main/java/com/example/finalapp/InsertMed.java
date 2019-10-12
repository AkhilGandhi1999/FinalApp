package com.example.finalapp;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

public class InsertMed extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

     private EditText med_name;
     private EditText med_time;
     private EditText med_type;
     DatabaseReference meduser;

    public int startdd,startmm,startyy,enddd,endmm,endyy;


    private LinkedList<String> times[] = new LinkedList[24 * 60];
    private LinkedList<Medicine> MyMeds;

     LinkedList<Integer> HH;
     LinkedList<Integer> MM;

    public static final String SHARED_PEF = "timepref";

    DatabaseReference time_m ;
    int up = 0;


    int set = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_med);

        meduser = FirebaseDatabase.getInstance().getReference("med_string");

        init();
        for(int i = 0;i < 24 * 60;i++){
            times[i] = new LinkedList<String>();
        }
        MyMeds = new LinkedList<Medicine>();
        HH = new LinkedList<>();
        MM = new LinkedList<>();
        findViewById(R.id.pick_time).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new TimePicker();
                newFragment.show(getSupportFragmentManager(),"TimePicker");
            }
        });

        findViewById(R.id.pick_days).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(InsertMed.this,DatePicker.class);
                startActivityForResult(intent,500);
            }
        });

        findViewById(R.id.submit1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Submit();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 500:
                 startdd = data.getIntExtra("sday",0);
                 startmm = data.getIntExtra("smon",0);
                 startyy = data.getIntExtra("syear",0);

                 enddd = data.getIntExtra("eday",0);
                 endmm = data.getIntExtra("emon",0);
                 endyy = data.getIntExtra("eyear",0);
                break;
        }
    }


    public String intToStr(Integer n){
        String num = "";
        while(n > 0){
            char x = (char)(n % 10 + 48);
            num = x + num;
            n = n / 10;
        }
        if(num.length() <= 1){
            num = '0' + num;
        }
        return num;
    }
    public Medicine ReverseParse(String str){
        String Medname = "";
        int timePos = -1;
        for(int i = 0;i < str.length();i++){
            if(str.charAt(i) == '['){
                timePos = i;
                break;
            }
            Medname = Medname + str.charAt(i);
        }
        LinkedList<Integer> hours = new LinkedList<Integer>();
        LinkedList<Integer> minutes = new LinkedList<Integer>();
        int i;
        for(i = timePos;str.charAt(i) != ']';i += 6){
            int hour = ((int)str.charAt(i + 1) - 48) * 10 + ((int)str.charAt(i + 2) - 48);
            int minute = ((int)str.charAt(i + 4) - 48) * 10 + ((int)str.charAt(i + 5) - 48);
            hours.add(hour);
            minutes.add(minute);
        }
        int sdd,smm,syy = 0,edd,emm,eyy = 0;
        sdd = ((int)str.charAt(i + 1) - 48) * 10 + ((int)str.charAt(i + 2) - 48);
        i += 2;
        smm = ((int)str.charAt(i + 1) - 48) * 10 + ((int)str.charAt(i + 2) - 48);
        i += 3;
        int four = 4;
        while(four-- > 0){
            syy = syy * 10 + (int)(str.charAt(i++) - 48);
        }
        i--;
        edd = ((int)str.charAt(i + 1) - 48) * 10 + ((int)str.charAt(i + 2) - 48);
        i += 2;
        emm = ((int)str.charAt(i + 1) - 48) * 10 + ((int)str.charAt(i + 2) - 48);
        i += 3;
        four = 4;
        while(four-- > 0){
            eyy = eyy * 10 + (int)(str.charAt(i++) - 48);
        }
        Medicine Med = new Medicine(Medname,hours,minutes,sdd,smm,syy,edd,emm,eyy);
        return Med;
    }
    public String PleaseParse(Medicine Med){
        String myStr = Med.MedName;
        Iterator hhitr = Med.hh.iterator();
        Iterator mmitr = Med.mm.iterator();
        myStr = myStr + '[';
        while(hhitr.hasNext() && mmitr.hasNext()){
            int hours = (Integer) hhitr.next();
            int minutes = (Integer) mmitr.next();
            String timeq = intToStr(hours) + ":" + intToStr(minutes) + ",";
            myStr = myStr + timeq;
        }
        myStr = myStr.substring(0,myStr.length() - 1);
        myStr = myStr + ']';
        myStr = myStr + intToStr(Med.startdd) + intToStr(Med.startmm) + intToStr(Med.startyy);
        myStr = myStr + intToStr(Med.enddd) + intToStr(Med.endmm) + intToStr(Med.endyy);
        return myStr;
    }
    private void Submit() throws ParseException {
        String med_T = med_time.getText().toString();
        String med_n = med_name.getText().toString();
        String med_ty = med_type.getText().toString();

        Medicine medicine = new Medicine(med_n,HH,MM,startdd,startmm,startyy,enddd,endmm,endyy);
        String MedToStr = PleaseParse(medicine);
        String id = meduser.push().getKey();
        meduser.child(id).setValue(MedToStr);
        Log.i("good times",HH.toString() + " " + MM.toString());
        update();
       // startAlarm();
        Intent submit = new Intent();
        submit.putExtra("key1", med_n);
        submit.putExtra("key2", med_T);
        submit.putExtra("key3",med_ty);
        setResult(Activity.RESULT_OK, submit);
        finish();
    }

    private void init() {
        med_name = (EditText) findViewById(R.id.name_med);
        med_time = (EditText) findViewById(R.id.med_des) ;
        med_type = (EditText) findViewById(R.id.time_med1);
    }

    @Override
    public void onTimeSet(android.widget.TimePicker timePicker, int i, int i1) {
       // Calendar c = Calendar.getInstance();
            HH.add(i);
            MM.add(i1);
        //c.set(2019,9,5,i,i1,0);
        //startAlarm(c);
    }

    public boolean valid_date(Medicine tempMed){
        String Start = new String("");
        Start = Start + Integer.toString(tempMed.startdd) + "/" + Integer.toString(tempMed.startmm) + "/" + Integer.toString(tempMed.startyy);
        Date StartDate = new Date();


        try{
            StartDate = new SimpleDateFormat("dd/MM/yyyy").parse(Start);
        }
        catch(Exception E){
            Log.i("Error : ",E.toString());
        }
        Date Today = new Date();



        String End = new String("");
        End = End + Integer.toString(tempMed.enddd) + "/" + Integer.toString(tempMed.endmm) + "/" + Integer.toString(tempMed.endyy);
        Date EndDate = new Date();
        try{
            EndDate = new SimpleDateFormat("dd/MM/yyyy").parse(End);
            Log.i("fucku",StartDate.toString() + "  " +  Today.toString() + "  " + EndDate.toString());

        }
        catch(Exception E){
            Log.i("Error : " , E.toString());
        }
        return (Today.after(StartDate) && Today.before(EndDate)) || (StartDate == Today) || (EndDate == Today);
    }
    public void update(){
        MyMeds.clear();
        meduser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot m : dataSnapshot.getChildren())
                {
                    String m1 = m.getValue(String.class);
                    Log.i("working string",m1);
                    Medicine n_med = new Medicine();
                    n_med = ReverseParse(m1);
                    MyMeds.add(n_med);
                    Log.i("doctor", String.valueOf(MyMeds.get(up).startdd) + " "  + String.valueOf(MyMeds.get(up).enddd)
                    +  " "  + String.valueOf(MyMeds.get(up).startmm) + "  " + " " + String.valueOf(MyMeds.get(up).endmm)
                            + "  " + String.valueOf(MyMeds.get(up).startyy) + " " + String.valueOf(MyMeds.get(up).endyy)
                            + " " + MyMeds.get(up).MedName
                    );
                    up++;

                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                int no = 0;
                for(int i = 0;i < 24 * 60;i++){
                    if((times[i].size())>0)
                    {
                        no +=1;
                        Intent myIntent = new Intent(getApplicationContext(), BroadcastRec.class);
                        myIntent.putExtra("cancel",900);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                                getApplicationContext(), no, myIntent,
                                PendingIntent.FLAG_UPDATE_CURRENT);

                        alarmManager.cancel(pendingIntent);

                    }
                    times[i].clear();
                }
                Iterator MedItr = MyMeds.iterator();
                while(MedItr.hasNext()){
                    Log.i("op","in");

                    Medicine tempMed = (Medicine) MedItr.next();
                    if(valid_date(tempMed)){
                        //if(true) {
                        Log.i("opopo","ininin");
                        Iterator HourItr = tempMed.hh.iterator();
                        Iterator MinuteItr = tempMed.mm.iterator();
                        while(HourItr.hasNext() && MinuteItr.hasNext()){
                            int TimeHH = (Integer) HourItr.next();
                            int TimeMM = (Integer) MinuteItr.next();
                            times[TimeHH * 60 + TimeMM].add(tempMed.MedName);
                        }
                    }
                }
                setAlarm();
            }
        }, 7000);

    }

    public void setAlarm(){
        for(int i = 0;i < 24 * 60;i++){
            int hours = i / 60,minutes = i % 60;
            String AllMeds = new String("");
            Iterator Meds = times[i].iterator();
            while(Meds.hasNext()){
                AllMeds = AllMeds + Meds.next() + "\n" ;
            }
            if((times[i].size())>0)
            {
                set++;
                Calendar c = Calendar.getInstance();
                c.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DATE),hours,minutes,0);
                Log.i("godyes", String.valueOf(hours ) + " " + String.valueOf(minutes));
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                Intent intt = new Intent(getApplicationContext(), BroadcastRec.class);
                intt.putExtra("final_not",AllMeds);
                Log.i("godohh",AllMeds);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(this, set, intt, 0);
                if (c.before(Calendar.getInstance())) {
                        c.add(Calendar.DATE, 1);
                }
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);

            }
            Log.i("OHH ","Alarm Set On : " + Integer.toString(i / 60) + ":" + Integer.toString(i % 60) + "\n" + "Medicine Names : \n" + AllMeds);
        }
        //saveSet();
    }

}
