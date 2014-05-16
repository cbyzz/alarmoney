package alarmoney.fragments;

import java.util.Date;
import java.util.Random;

import insomnia.alarmoney.R;
import alarmoney.AlarmEditActivity;
import alarmoney.data.alarm.AlarmData;
import alarmoney.data.alarm.AlarmDataManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class AlarmFragment extends Fragment {
	
	private ListView mAlarmList = null;
	private Button mAddButton = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View ret = inflater.inflate(R.layout.fragment_alarm, container, false);
	
		mAlarmList = (ListView)ret.findViewById(R.id.alarm_list);
		mAlarmList.setAdapter(new AlarmListAdapter());
		
		mAddButton = (Button)ret.findViewById(R.id.add);
		
		mAddButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), AlarmEditActivity.class);
				getActivity().startActivity(intent);
			}
		});
		
		
		return ret;
	}
	
	private class AlarmListAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return AlarmDataManager.getInstance().size();
		}

		@Override
		public Object getItem(int position) {
			return AlarmDataManager.getInstance().getAlarm(position);
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			AlarmData alarm = AlarmDataManager.getInstance().getAlarm(position);
			
			LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.listitem_alarm, null);
			
			TextView alarmText = (TextView) convertView.findViewById(R.id.text);
			alarmText.setTextSize(60);
			
			Date now = new Date();
			Date alarmTime = (Date)now.clone();
			
			alarmTime.setHours(alarm.getHour());
			alarmTime.setMinutes(alarm.getMinute());
			
			alarmText.setText(DateUtils.formatDateTime(getActivity(), alarmTime.getTime(), DateUtils.FORMAT_SHOW_TIME));
		
			ImageView toggle = (ImageView) convertView.findViewById(R.id.toggle);
			Random random = new Random();
			if (random.nextInt(2) == 0) {
				toggle.setImageResource(R.drawable.on_btn);
			} else {
				toggle.setImageResource(R.drawable.off_btn);
			}
			
			return convertView;
		}
		
	}
}
