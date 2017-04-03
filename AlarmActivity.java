package com.acadgild.weightlossmagic.reminder;


import android.annotation.TargetApi;

import android.app.AlarmManager;

import android.app.PendingIntent;

import android.content.Context;

import android.content.Intent;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;

import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;

import android.support.design.widget.Snackbar;

import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;

import android.util.Log;

import android.view.View;

import android.view.Menu;

import android.view.MenuItem;

import android.widget.AdapterView;

import android.widget.ArrayAdapter;

import android.widget.Button;

import android.widget.EditText;
import android.widget.Spinner;

import android.widget.TextView;

import android.widget.TimePicker;

import android.widget.Toast;



import java.util.Calendar;

import com.acadgild.weightlossmagic.R;

/**
 * Created by DivyaVipin on 2/28/2017.
 */

public class AlarmActivity extends AppCompatActivity {

    AlarmManager alarmManager;//Inbuilt class for Alarm
    private PendingIntent pending_intent;//Inbuilt class for Notification
    private TimePicker alarmTimePicker;//Specify time in todays date
    private static AlarmActivity inst;
    private TextView alarmTextView;//To update the text whether alarm is on or off
    private EditText editTextRemainder;//To add the reminder message
    private Context context;
    Toolbar toolbar;
    @TargetApi(Build.VERSION_CODES.M)

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_main);
        toolbar=(Toolbar)findViewById(R.id.toolbar) ;
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        editTextRemainder=(EditText)findViewById(R.id.editTextRemainder) ;
        this.context = this;
        alarmTextView = (TextView) findViewById(R.id.alarmText);
        final Intent myIntent = new Intent(this.context, Alarm_Receiver.class);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        final Calendar calendar = Calendar.getInstance();
        alarmTimePicker = (TimePicker) findViewById(R.id.alarmTimePicker);
        Button start_alarm= (Button) findViewById(R.id.start_alarm);
        start_alarm.setOnClickListener(new View.OnClickListener() {

            @TargetApi(Build.VERSION_CODES.M)



            @Override

            public void onClick(View v) {



                calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getHour());

                calendar.set(Calendar.MINUTE, alarmTimePicker.getMinute());
                final  String message=editTextRemainder.getText().toString();


                final int hour = alarmTimePicker.getHour();

                final int minute = alarmTimePicker.getMinute();;



                String minute_string = String.valueOf(minute);

                String hour_string = String.valueOf(hour);



                if (minute < 10) {

                    minute_string = "0" + String.valueOf(minute);

                }



                if (hour > 12) {

                    hour_string = String.valueOf(hour - 12) ;

                }



                myIntent.putExtra("extra", "yes");

                myIntent.putExtra("reminder_message",message );

                pending_intent = PendingIntent.getBroadcast(AlarmActivity.this, 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pending_intent);


                setAlarmText("Alarm set to " + hour_string + ":" + minute_string);

            }



        });



        Button stop_alarm= (Button) findViewById(R.id.stop_alarm);

        stop_alarm.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                myIntent.putExtra("extra", "no");
                sendBroadcast(myIntent);
                alarmManager.cancel(pending_intent);
                setAlarmText("Alarm canceled");

            }

        });



    }



    public void setAlarmText(String alarmText) {

        alarmTextView.setText(alarmText);

    }
















    @Override

    public void onStart() {

        super.onStart();

        inst = this;

    }





    @Override

    public void onDestroy() {

        super.onDestroy();



        Log.e("MyActivity", "on Destroy");

    }









}