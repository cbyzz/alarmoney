package alarmoney;

import insomnia.alarmoney.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class RootActivity extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_root);
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
		 				intent = new Intent(RootActivity.this, MainActivity.class);
		 				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		 				startActivity(intent);
		 				finish();
		 		  }
		 		}, 1500);
		  	}
}
