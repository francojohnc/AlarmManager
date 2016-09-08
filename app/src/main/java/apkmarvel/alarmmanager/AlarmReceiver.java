package apkmarvel.alarmmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {
	public static final String TAG = AlarmReceiver.class.getSimpleName();
	public static final String ACTION_ALARM_RECEIVER = "ACTION_ALARM_RECEIVER";
	@Override
	public void onReceive(Context context, Intent intent) {
		Intent i = new Intent(context,MainActivity.class);
		UtilNotification.create("Alarm","Message",context,i);
	}
}