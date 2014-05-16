package alarmoney.fragments;

import insomnia.alarmoney.R;
import alarmoney.AlarmEditActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

public class AlarmFragment extends Fragment {
	
	private ListView mAlarmList = null;
	private Button mAddButton = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View ret = inflater.inflate(R.layout.fragment_alarm, container, false);
	
		mAlarmList = (ListView)ret.findViewById(R.id.alarm_list);
		mAddButton = (Button)ret.findViewById(R.id.add);
		
		mAddButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), AlarmEditActivity.class);
				getActivity().startActivity(intent);
			}
		});
		
		
		ret.setBackgroundColor(0xff0000ff);
		
		return ret;
	}
}
