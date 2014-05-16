package alarmoney;

import insomnia.alarmoney.R;

import java.util.Locale;

import alarmoney.views.TabBarView;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements TabBarView.OnTabListener {
	private ImageView msplashLogo = null;
	private TextView mainText = null;
	
	private static final String LOG_TAG = MainActivity.class.getSimpleName();
	
	private SectionsPagerAdapter mSectionsPagerAdapter = null;
	private TabBarView mTabBar = null;
	private ViewPager mViewPager = null;

	 protected void onCreate(Bundle savedInstanceState) {
		Log.i(LOG_TAG, "onCreate");
        setContentView(R.layout.activity_main);
		super.onCreate(savedInstanceState);
	
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		
		mTabBar = (TabBarView) findViewById(R.id.tabbar);
		
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		mViewPager.setOnPageChangeListener(mTabBar);
     
        init();
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
			return new Fragment();
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

}
