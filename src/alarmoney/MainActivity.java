package alarmoney;

import insomnia.alarmoney.R;

import java.util.Date;
import java.util.Locale;

import alarmoney.data.alarm.AlarmData;
import alarmoney.data.alarm.AlarmDataManager;
import alarmoney.fragments.AlarmFragment;
import alarmoney.fragments.MenuFragment;
import alarmoney.fragments.StoreFragment;
import alarmoney.fragments.WatchFragment;
import alarmoney.views.TabBarView;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.alarmclock.util.AlarmDatabase;
import com.android.alarmclock.util.AlarmDatabase.Record;

public class MainActivity extends FragmentActivity implements TabBarView.OnTabListener {
	private ImageView msplashLogo = null;
	private TextView mainText = null;
	
	private static final String LOG_TAG = MainActivity.class.getSimpleName();
	
	private SectionsPagerAdapter mSectionsPagerAdapter = null;
	private TabBarView mTabBar = null;
	private ViewPager mViewPager = null;
	
	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			
			PowerManager pm = (PowerManager) MainActivity.this.getSystemService(Context.POWER_SERVICE);
			PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
			wl.acquire();

			// Put here YOUR code.
			Toast.makeText(MainActivity.this, "Alarm !!!!!!!!!!", Toast.LENGTH_LONG).show(); // For example

			wl.release();
			
			final MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.vita500);
			mp.start();
		}
	};

	protected void onCreate(Bundle savedInstanceState) {
		Log.i(LOG_TAG, "onCreate");
		
        setContentView(R.layout.activity_main);
		super.onCreate(savedInstanceState);
	
		mTabBar = (TabBarView) findViewById(R.id.tabbar);
		
		Date now = new Date();
		now.setMinutes(now.getMinutes() + 1);
		
		AlarmData alarm = new AlarmData();
		alarm.setHour(7);
		alarm.setMinute(0);
		AlarmDataManager.getInstance().addAlarm(alarm);
		
		alarm = new AlarmData();
		alarm.setHour(7);
		alarm.setMinute(5);
		AlarmDataManager.getInstance().addAlarm(alarm);
		
		alarm = new AlarmData();
		alarm.setHour(7);
		alarm.setMinute(10);
		AlarmDataManager.getInstance().addAlarm(alarm);
		
		alarm = new AlarmData();
		alarm.setHour(now.getHours());
		alarm.setMinute(now.getMinutes());
		AlarmDataManager.getInstance().addAlarm(alarm);
		
		AlarmDataManager.getInstance().setNearestAlarm(this);
		
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		mViewPager.setOnPageChangeListener(mTabBar);

     
        // init();
		
		//findAllAlarms();
	}
	
	public void onResume() {
		super.onResume();
		
		//Message msg = new Message();
		//mHandler.sendMessageDelayed(msg, 10000);
	}
	
	private void findAllAlarms() {
		
		ContentResolver cr = getContentResolver();
		AlarmDatabase ad = new AlarmDatabase(cr);
		Record r = ad.getNearestEnabledAlarm();
		
		Log.d(LOG_TAG, String.format("nearest ararm time = %02d:%02d", r.hour, r.minute));
		
	}

	private void init() {
		Handler mHandler = new Handler();
		mHandler.postDelayed(new Runnable()
		{
		  @Override     public void run()
		  {
			  Intent intent = null;
				intent = new Intent(MainActivity.this, RootActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
				finish();
		  }
		}, 3000);
	}
	
	@Override
	public void onTabSelected(int position) {
		Log.i(LOG_TAG, "onTabSelected");
		mViewPager.setCurrentItem(position);
	}

	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Fragment ret = null;
			
			switch (position) {
			case 0:
				ret = new AlarmFragment();
				break;
			case 1:
				ret = new WatchFragment();
				break;
			case 2:
				ret = new MenuFragment();
				break;
			case 3:
				ret = new StoreFragment();
				break;
			}
			
			return ret;
		}

		@Override
		public int getCount() {
			return 4;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return "0";
			case 1:
				return "1";
			case 2:
				return "2";
			case 3:
				return "3";
			}
			return null;
		}
	}
	
	public static class ColorFragment extends Fragment {
		private int mColor = 0;

		public ColorFragment() {
			mColor = 0xffffffff;
		}
		
		public void setColor(int color) {
			mColor = color;
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View ret = new View(this.getActivity());
			Log.d(LOG_TAG, String.format("0x%08x", mColor));
			ret.setBackgroundColor(mColor);
			
			return ret;
		}
		
	}

}
