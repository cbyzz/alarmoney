package alarmoney;

import insomnia.alarmoney.R;
import alarmoney.data.alarm.AlarmData;
import alarmoney.data.alarm.AlarmDataManager;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

public class AlarmEditActivity extends Activity {
	
	private static final String LOG_TAG = AlarmEditActivity.class.getSimpleName();

	private TimePicker mTimePicker;
	private Button mToggleSunday;
	private Button mToggleMonday;
	private Button mToggleTuesday;
	private Button mToggleWednesday;
	private Button mToggleThursday;
	private Button mToggleFriday;
	private Button mToggleSaturday;
	private Button mSaveButton;
	
	private AlarmData mAlarmData;
	
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(LOG_TAG, "onCreate");
		
		mAlarmData = new AlarmData();
		
        setContentView(R.layout.activity_alarm_edit);
		super.onCreate(savedInstanceState);
		
		mTimePicker = (TimePicker)findViewById(R.id.time_picker);
		mToggleSunday = (Button)findViewById(R.id.toggle_sunday);
		mToggleMonday = (Button)findViewById(R.id.toggle_monday);
		mToggleTuesday = (Button)findViewById(R.id.toggle_tuesday);
		mToggleWednesday = (Button)findViewById(R.id.toggle_wednesday);
		mToggleThursday = (Button)findViewById(R.id.toggle_thursday);
		mToggleFriday = (Button)findViewById(R.id.toggle_friday);
		mToggleSaturday = (Button)findViewById(R.id.toggle_saturday);
		
		mTimePicker.setOnTimeChangedListener(new OnTimeChangedListener() {

			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				mAlarmData.setHour(hourOfDay);
				mAlarmData.setMinute(minute);
			}
			
		});
		
		mSaveButton = (Button)findViewById(R.id.save);
		
		mSaveButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AlarmDataManager.getInstance().addAlarm(mAlarmData);
				AlarmDataManager.getInstance().setNearestAlarm(AlarmEditActivity.this);
				
				finish();
			}
			
		});
		
	}

}
