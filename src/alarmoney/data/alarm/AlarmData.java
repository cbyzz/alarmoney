package alarmoney.data.alarm;

import java.util.Date;

public class AlarmData {

	private int mDayOfWeeks;
	private int mHour;
	private int mMinute;
	
	public AlarmData() {
		mDayOfWeeks = 0;
		mHour = 0;
		mMinute = 0;
	}
	
	public int getDayOfWeeks() {
		return mDayOfWeeks;
	}

	public void setDayOfWeeks(int dayOfWeeks) {
		this.mDayOfWeeks = dayOfWeeks;
	}

	public int getHour() {
		return mHour;
	}

	public void setHour(int hour) {
		this.mHour = hour;
	}

	public int getMinute() {
		return mMinute;
	}

	public void setMinute(int minute) {
		this.mMinute = minute;
	}
	
	public Date getNextAlarmDate() {
		Date ret = new Date();
		ret.setHours(mHour);
		ret.setMinutes(mMinute);
		
		Date now = new Date();
		if (now.after(ret)) {
			ret.setDate(ret.getDate()+1);
		}
		
		return ret;
	}
}
