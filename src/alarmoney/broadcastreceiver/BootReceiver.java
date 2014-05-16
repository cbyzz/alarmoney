package alarmoney.broadcastreceiver;

import alarmoney.data.alarm.AlarmDataManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReceiver extends BroadcastReceiver {
	private static final String LOG_TAG = BootReceiver.class.getSimpleName();
	
	@Override
	public void onReceive(Context context, Intent intent) {
		AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		
		Intent newIntent = new Intent(context, AlarmReceiver.class);
		PendingIntent pender = PendingIntent.getBroadcast(context, 0, newIntent, 0);
		
		alarm.set(AlarmManager.RTC_WAKEUP, AlarmDataManager.getInstance().getNextAlarmTimeMillis(), pender);
	}

}
