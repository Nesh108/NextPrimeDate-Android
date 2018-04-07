package com.kulplex.nesh.nextprimedate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity
{
    private TextView textViewDate1;
    private TextView textViewDate2;
    private TextView textViewDate3;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewDate1 = findViewById(R.id.textViewDate1);
        textViewDate2 = findViewById(R.id.textViewDate2);
        textViewDate3 = findViewById(R.id.textViewDate3);
        onClickUpdate(null);
    }

    void onClickUpdate(View v)
    {
        String[] upcomingPrimeDates = getUpcomingPrimeDates();
        textViewDate1.setText(upcomingPrimeDates[0]);
        textViewDate2.setText(upcomingPrimeDates[1]);
        textViewDate3.setText(upcomingPrimeDates[2]);
    }

    String[] getUpcomingPrimeDates()
    {
        String[] upcomingDates = new String[3];
        int primeDatesFound = 0;
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        // Move calendar to Yesterday
        c.add(Calendar.DATE, -1);

        while (primeDatesFound < 3)
        {
            c.add(Calendar.DATE, 1);
            dt = c.getTime();
            if (isPrime(convertDateToInteger(dt)))
            {
                upcomingDates[primeDatesFound] = convertDateToString(dt, "dd/MM/yyy");
                primeDatesFound++;
            }
        }
        return upcomingDates;
    }

    String convertDateToString(Date dt, String datePattern)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
        try
        {
            return dateFormat.format(dt);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return "";
    }

    int convertDateToInteger(Date dt)
    {
        return Integer.parseInt(convertDateToString(dt, "ddMMyyy"));
    }

    boolean isPrime(int num)
    {
        if (num > 2 && num % 2 == 0)
        {
            return false;
        }
        int top = (int) Math.sqrt(num) + 1;
        for (int i = 3; i < top; i += 2)
        {
            if (num % i == 0)
            {
                return false;
            }
        }
        return true;
    }
}
