package alarmoney;

import insomnia.alarmoney.R;
import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.util.Log;
import android.widget.Toast;

public class AlarmActivity extends Activity {
	
	private static final String LOG_TAG = AlarmActivity.class.getSimpleName();

	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			
			PowerManager pm = (PowerManager) AlarmActivity.this.getSystemService(Context.POWER_SERVICE);
			PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
			wl.acquire();

			// Put here YOUR code.
			Toast.makeText(AlarmActivity.this, "Alarm !!!!!!!!!!", Toast.LENGTH_LONG).show(); // For example

			wl.release();
			
			final MediaPlayer mp = MediaPlayer.create(AlarmActivity.this, R.raw.vita500);
			mp.start();
		}
	};

	protected void onCreate(Bundle savedInstanceState) {
		Log.i(LOG_TAG, "onCreate");
		
	    setContentView(R.layout.activity_alarm);
		super.onCreate(savedInstanceState);
		
		mHandler.sendEmptyMessage(0);
	}

}
