package com.bloc.blocnotes;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

/**
 * Created by Daniel on 10/27/2014.
 */
public class ReminderReceiver extends BroadcastReceiver {

    private static final String TAG = ".ReminderReceiver.java";

    Context mContext;

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;
        if ("SHOW_NOTIFICATION".equals(intent.getAction())) {
            String title = intent.getStringExtra("EXTRA_REMINDER_TITLE");
            String body = intent.getStringExtra("EXTRA_REMINDER_BODY");
            buildNotification(title, body);
        } else if ("ACTION_SNOOZE".equals(intent.getAction())) {
            NotificationManagerCompat nm = NotificationManagerCompat.from(mContext);
            nm.cancel(001);
            String body = intent.getStringExtra("EXTRA_REMINDER_BODY");
            snooze(body);
        }
    }

    private void buildNotification(String title, String body) {
        Log.d(TAG, "entered buildNotification()");
        Notification builder = new NotificationCompat.Builder(mContext)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(android.R.drawable.ic_menu_add)
                .setContentIntent(resultIntent())
                .setDefaults(Notification.DEFAULT_ALL)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(body))
                .addAction(android.R.drawable.ic_delete,
                        mContext.getString(R.string.delete), deleteIntent())
                .addAction(R.drawable.ic_snooze,
                        mContext.getString(R.string.snooze), snoozeIntent(body))
                .setAutoCancel(true)
                .build();

        NotificationManagerCompat nm = NotificationManagerCompat.from(mContext);
        nm.notify(001, builder);
    }

    private void snooze(String body) {
        Intent reminderReceiverIntent = new Intent(mContext, ReminderReceiver.class);
        reminderReceiverIntent.setAction("SHOW_NOTIFICATION");
        reminderReceiverIntent.putExtra("EXTRA_REMINDER_TITLE", "Reminder for Note");
        reminderReceiverIntent.putExtra("EXTRA_REMINDER_BODY", body);

        PendingIntent reminderPendingIntent = PendingIntent.getBroadcast(mContext, 0,
                reminderReceiverIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarmService = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
        alarmService.set(AlarmManager.RTC,
                System.currentTimeMillis() + 10000, //10 seconds
                reminderPendingIntent);
    }

    private PendingIntent resultIntent() {
        Intent resultIntent = new Intent(mContext, BlocNotesActivity.class );

        PendingIntent resultPendingIntent = PendingIntent.getActivity(mContext,
                0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        return resultPendingIntent;
    }

    private PendingIntent snoozeIntent(String body) {
        Intent snoozeIntent = new Intent(mContext,ReminderReceiver.class);
        snoozeIntent.setAction("ACTION_SNOOZE");
        snoozeIntent.putExtra("EXTRA_REMINDER_BODY", body);
        PendingIntent piSnooze = PendingIntent.getBroadcast(mContext, 0,
                snoozeIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        return piSnooze;
    }

    private PendingIntent deleteIntent() {
        return null;
    }
}
