package com.example.finalapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.savvi.rangedatepicker.CalendarPickerView;
import com.savvi.rangedatepicker.SubTitle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DatePicker extends AppCompatActivity {
    CalendarPickerView calendar;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);

        final Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 10);

        final Calendar lastYear = Calendar.getInstance();
        lastYear.add(Calendar.YEAR, - 10);

        calendar = findViewById(R.id.calendar_view);
        button = findViewById(R.id.get_selected_dates);
        ArrayList<Integer> list = new ArrayList<>();
        list.add(2);


        ArrayList<Date> arrayList = new ArrayList<>();
        try {
            SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");

            String strdate = "22-4-2019";
            String strdate2 = "26-4-2019";

            Date newdate = dateformat.parse(strdate);
            Date newdate2 = dateformat.parse(strdate2);
            arrayList.add(newdate);
            arrayList.add(newdate2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        calendar.init(lastYear.getTime(), nextYear.getTime(), new SimpleDateFormat("MMMM, YYYY", Locale.getDefault())) //
                .inMode(CalendarPickerView.SelectionMode.RANGE) //
                .withSubTitles(getSubTitles())
                .withHighlightedDates(arrayList);

        calendar.scrollToDate(new Date());


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                Date startdate = calendar.getSelectedDates().get(0);
                Date enddate = calendar.getSelectedDates().get(calendar.getSelectedDates().size() - 1);
                int startdd = 0,startmm = 0,startyy = 0;
                int enddd = 0,endmm = 0,endyy = 0;
                String pattern = "yyyy-MM-dd";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                String startDate = simpleDateFormat.format(startdate);
                String endDate = simpleDateFormat.format(enddate);
                for(int i = 0;i < 4;i++){
                    startyy = startyy * 10 + (startDate.charAt(i) - 48);
                }
                for(int i = 5;i < 7;i++){
                    startmm = startmm * 10 + (startDate.charAt(i) - 48);
                }
                for(int i = 8;i < 10;i++){
                    startdd = startdd * 10 + (startDate.charAt(i) - 48);
                }
                for(int i = 0;i < 4;i++){
                    endyy = endyy * 10 + (endDate.charAt(i) - 48);
                }
                for(int i = 5;i < 7;i++){
                    endmm = endmm * 10 + (endDate.charAt(i) - 48);
                }
                for(int i = 8;i < 10;i++){
                    enddd = enddd * 10 + (endDate.charAt(i) - 48);
                }
                intent.putExtra("sday",startdd);
                intent.putExtra("smon",startmm);
                intent.putExtra("syear",startyy);
                intent.putExtra("eday",enddd);
                intent.putExtra("emon",endmm);
                intent.putExtra("eyear",endyy);
                Toast.makeText(DatePicker.this, "list " + calendar.getSelectedDates().toString(), Toast.LENGTH_LONG).show();
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });
    }

    private ArrayList<SubTitle> getSubTitles() {
        final ArrayList<SubTitle> subTitles = new ArrayList<>();
        final Calendar tmrw = Calendar.getInstance();
        tmrw.add(Calendar.DAY_OF_MONTH, 1);
        subTitles.add(new SubTitle(tmrw.getTime(), "₹1000"));
        return subTitles;
    }
}
