package com.gazal.navnotif;

import com.crittercism.app.Crittercism;

import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class NotifActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Crittercism.initialize(getApplicationContext(), "52eca9ba8b2e337979000004");
		//setContentView(R.layout.activity_notif);
	}

	public void sendNotification(String title, String content){
		Intent startMain = new Intent(Intent.ACTION_MAIN);
		startMain.addCategory(Intent.CATEGORY_HOME);
		startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		
		PendingIntent pendingIntent = PendingIntent.getActivity(NotifActivity.this, 0, startMain, Intent.FILL_IN_ACTION);
		
		// build notification
		// the addAction re-use the same intent to keep the example short
		Notification n  = new NotificationCompat.Builder(this)
		        .setContentTitle(title)
		        .setContentText(content)
		        .setSmallIcon(R.drawable.ic_launcher)
		        .setContentIntent(pendingIntent)
		        .setAutoCancel(false).build();

		n.flags |= Notification.FLAG_ONGOING_EVENT;
		NotificationManager notificationManager = 
		  (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		notificationManager.notify(0, n);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		sendNotification("Home", "");
	}
	
	@Override
	public void onResume() {
		super.onResume();
		finish();
	}
}
