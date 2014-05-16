package alarmoney.fragments;

import insomnia.alarmoney.R;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class StoreFragment extends Fragment {
	private ListView listView;
	private ArrayList<String> arrayList;
	private ListAdapter adapter;
	private TextView tv1;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View ret = inflater.inflate(R.layout.fragment_store, container, false);
		
		ret.setBackgroundColor(0xfffffff);
		arrayList = new ArrayList<String>();
		arrayList.add("1");
		arrayList.add("2");
		arrayList.add("3");
		arrayList.add("4");
		arrayList.add("5");
		
		
		
		return ret;
	}
}
