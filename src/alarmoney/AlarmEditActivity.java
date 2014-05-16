package alarmoney;

import insomnia.alarmoney.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TimePicker;

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
	
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(LOG_TAG, "onCreate");
		
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
		
	}

}
