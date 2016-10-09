package com.gcy.offsitelanding.offsitelanding;



import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import cn.jpush.android.api.JPushInterface;

public class JpushReceiver extends BroadcastReceiver {
     
    private NotificationManager nm;
    
    @Override
    public void onReceive(Context context, Intent intent) {
        if (null == nm) {
            nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }
         
        Bundle bundle = intent.getExtras();
       // Log.d(TAG, "onReceive - " + intent.getAction() + ", extras: " + AndroidUtil.printBundle(bundle));
         
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            //Log.d(TAG, "JPush用户注册成功");
             
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Log.d("jpush" , bundle.getString(JPushInterface.EXTRA_MESSAGE) + "自定义消息");
            System.out.println(bundle.getString(JPushInterface.EXTRA_MESSAGE) + "自定义消息");
        	Intent mIntent = new Intent(context, MainActivity.class);
        	//mIntent.putExtra("intentType", 0);
        	mIntent.putExtra("MessageContent",bundle.getString(JPushInterface.EXTRA_MESSAGE));
        	mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        	context.startActivity(mIntent);
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            //Log.d(TAG, "接受到推送下来的通知");
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            //Log.d(TAG, "用户点击打开了通知");
        } else {
            //Log.d(TAG, "Unhandled intent - " + intent.getAction());
        }
    }
 
}

