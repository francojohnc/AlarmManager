package apkmarvel.alarmmanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    int requestCode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void check(View v) {
        Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);

        boolean isWorking = (PendingIntent.getBroadcast(MainActivity.this, requestCode, intent, PendingIntent.FLAG_NO_CREATE) != null);
        if (isWorking) {
            Log.e(TAG, "is working");
        } else {
            Log.e(TAG, "is not working");
        }


    }
    public void cancel(View v){
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, requestCode, intent, PendingIntent.FLAG_CANCEL_CURRENT);//the same as up
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);//important
        pendingIntent.cancel();//important
    }
    public void create(View v) {
        Calendar cal = Calendar.getInstance();

        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.setAction(AlarmReceiver.ACTION_ALARM_RECEIVER);
        PendingIntent pi = PendingIntent.getBroadcast(this, requestCode, intent, PendingIntent.FLAG_ONE_SHOT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis() + 2000, pi);
    }

    public void repeat(View v) {
        Calendar cal = Calendar.getInstance();

        Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long timeNotif = 999999999*999999999;//Notification every (ms)
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), timeNotif, pendingIntent);

    }
}
