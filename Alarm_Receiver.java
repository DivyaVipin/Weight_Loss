package com.acadgild.weightlossmagic.reminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by DivyaVipin on 2/28/2017.
 */

public class Alarm_Receiver  extends BroadcastReceiver {



    @Override

    public void onReceive(Context context, Intent intent) {



        String state = intent.getExtras().getString("extra");

        Log.e("MyActivity", "In the receiver with " + state);



        String message = intent.getExtras().getString("reminder_message");

        //Log.e("Richard quote is" , richard_id);



        Intent serviceIntent = new Intent(context,RingtonePlayingService.class);

        serviceIntent.putExtra("extra", state);

        serviceIntent.putExtra("reminder_message",message);



        context.startService(serviceIntent);

    }



}