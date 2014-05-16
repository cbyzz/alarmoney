package alarmoney.data.alarm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import alarmoney.broadcastreceiver.AlarmReceiver;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmDataManager {
	
	private static final String LOG_TAG = AlarmDataManager.class.getSimpleName();
	
/***************************************************
 * class-related
***************************************************/
	private static AlarmDataManager _Instance = null;
	
	public static AlarmDataManager getInstance() {
		
		if (_Instance == null) {
			synchronized(AlarmDataManager.class) {
				if (_Instance == null) {
					_Instance = new AlarmDataManager();
				}
			}
		}
		
		return _Instance;
	}
	
/***************************************************
 * instance-related
***************************************************/
	
	private List<AlarmData> mList = null;
	
	private AlarmDataManager() {
		mList = new ArrayList<AlarmData>();
	}
	
	public void addAlarm(AlarmData alarm) {
		mList.add(alarm);
	}
	
	public int size() {
		return mList.size();
	}
	
	public AlarmData getAlarm(int index) {
		assert(index >= 0 && index < mList.size());
		return mList.get(index);
	}
	
	public void setNearestAlarm(Context context) {
		AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		
		Intent newIntent = new Intent(context, AlarmReceiver.class);
		PendingIntent pender = PendingIntent.getBroadcast(context, 0, newIntent, 0);
		
		alarm.cancel(pender);
		//alarm.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 10000, pender);
		alarm.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 10000, pender);
	}
	
	public long getNextAlarmTimeMillis() {
		Date ret = null;
		
		for (AlarmData alarm : mList) {
			Date next = alarm.getNextAlarmDate();
			Log.d(LOG_TAG, "alarm time = " + next);
			if (ret == null || ret.after(next)) {
				ret = next;
			}
		}
		
		Log.d(LOG_TAG, "next alarm time = " + ret);
	
		return ret.getTime();
	}
	
}
