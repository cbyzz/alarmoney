package alarmoney;

import insomnia.alarmoney.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private ImageView msplashLogo = null;
	private TextView mainText = null;

	 protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
     
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
}
