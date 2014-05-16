package alarmoney.fragments;

import insomnia.alarmoney.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class WatchFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View ret = inflater.inflate(R.layout.fragment_watch, container, false);
		
		ret.setBackgroundColor(0xff0ff0ff);

		return ret;
	}
}
