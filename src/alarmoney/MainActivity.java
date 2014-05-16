package alarmoney;

import insomnia.alarmoney.R;

import java.util.Locale;

import alarmoney.views.TabBarView;
import alarmoney.views.TabBarView.OnTabListener;
import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

public class MainActivity extends FragmentActivity implements OnTabListener {
	
	private static final String LOG_TAG = MainActivity.class.getSimpleName();
	
	private SectionsPagerAdapter mSectionsPagerAdapter = null;
	private TabBarView mTabBar = null;
	private ViewPager mViewPager = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.i(LOG_TAG, "onCreate");
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
	
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		
		mTabBar = (TabBarView) findViewById(R.id.tabbar);
		
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		mViewPager.setOnPageChangeListener(mTabBar);
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
