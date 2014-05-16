package alarmoney.views;

import insomnia.alarmoney.R;

import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

@SuppressLint("ResourceAsColor")
public class TabBarView extends View implements OnPageChangeListener, OnTouchListener {
	
	private static final String LOG_TAG = TabBarView.class.getSimpleName();
	
	public interface OnTabListener {
		public void onTabSelected(int position);
	}
	
	private static final int SELECTED_INDICATOR_HEIGHT = 20;
	private static final int UNSELECTED_INDICATOR_HEIGHT = 10;
	
	private ArrayList<View> _childList = new ArrayList<View>();
	
	private HashMap<View, Bitmap> _bitmapCache = new HashMap<View, Bitmap>();
	
	private int _selected = 0;
	
	private float _slideX = 0;
	private Paint _indicatorPaint = null;
	private int _indicatorColor = 0xffff0000;
	
	private OnTabListener _listener = null;
	
	public TabBarView(Context context) {
		super(context);
		initView();
	}
	
	public TabBarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public TabBarView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}
	
	private void initView() {
		_indicatorPaint = new Paint();
		_indicatorPaint.setColor(android.R.color.white);
		addTabItemImage(R.drawable.alarm_s);
		addTabItemImage(R.drawable.point_n);
		addTabItemImage(R.drawable.store_n);
		setWillNotDraw(false);
		
		this.setOnTouchListener(this);
	}
	
	public int getChildCount() {
		return _childList.size();
	}
	
	public View getChildAt(int position) {
		return _childList.get(position);
	}
	
	public void addView(View v) {
		_childList.add(v);
	}
	
	public void setOnTabListener(OnTabListener listener) {
		_listener = listener;
	}
	
	public int getPosition(View v) {
		for (int i = 0; i < getChildCount(); i++) {
			if (getChildAt(i) == v) {
				return i;
			}
		}
		
		return -1;
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
	     int itemWidth = (r-l)/getChildCount();
	     for(int i=0; i< this.getChildCount(); i++){
	         View v = getChildAt(i);
	         v.layout(itemWidth*i, t, (i+1)*itemWidth, b);
	     }
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		int height = 0;
		for(int i=0; i<getChildCount(); i++){
			View v = getChildAt(i);
			v.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
			height = v.getMeasuredHeight();
		}
		
		Log.d("proxima", String.format("measured height = %d", height));
		
		WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		
		height = (int)(((float) display.getHeight()) * 0.15);
		
		setMeasuredDimension(getMeasuredWidth(), height);
	}
	
	public void addTabItemView(View v) {
		addView(v);
	}
	
	public void addTabItemImage(int res) {
		ImageView imageView = new ImageView(getContext());
		//imageView.setPadding(40, 40, 40, 40);
		imageView.setImageResource(res);
		imageView.setScaleType(ScaleType.CENTER);
		addTabItemView(imageView);
	}
	
	public Bitmap loadBitmapFromView(View v) {
		
	    Bitmap b = Bitmap.createBitmap( v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);                
	    Canvas c = new Canvas(b);
	    v.layout(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
	    v.draw(c);
	    
	    return b;
	}

	@Override
	public void onDraw(Canvas canvas) {
		
		int width = canvas.getWidth();
		int height = canvas.getHeight();
		int indicatorWidth = getChildAt(0).getWidth();
		
		for (int i = 0; i < getChildCount(); i++) {
			View v = getChildAt(i);
			Bitmap bitmap = loadBitmapFromView(v);
			canvas.drawBitmap(bitmap, i * indicatorWidth, 0, null);
		}
		
		Log.d("proxima", String.format("slidex = %f, indicatorWidth = %d, width = %d, height = %d", _slideX, indicatorWidth, width, height));
	
		if (getChildCount() <= 0) {
			return;
		}
		
		canvas.drawRect(0, 0, width, UNSELECTED_INDICATOR_HEIGHT, _indicatorPaint);
		canvas.drawRect(_slideX , 0, _slideX + indicatorWidth, SELECTED_INDICATOR_HEIGHT, _indicatorPaint);
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		if (getChildCount() <= 0) {
			return;
		}
		
		int indicatorWidth = getChildAt(0).getWidth();
		
		_slideX = indicatorWidth * (position + positionOffset);
		invalidate();
	
		if (positionOffset == 0.0f || positionOffset == 1.0f) {
			if (this._listener != null) {
				this._listener.onTabSelected(position);
			}
			
			this.onPageSelected(position);
		}
	}

	@Override
	public void onPageSelected(int position) {
		
		Log.d(LOG_TAG, "page " + position + " selected");
		_selected = position;
		
		if (_selected == 0) {
			ImageView imageView = (ImageView)this.getChildAt(0);
			imageView.setImageResource(R.drawable.alarm_s);
			imageView.invalidate();
		} else {
			ImageView imageView = (ImageView)this.getChildAt(0);
			imageView.setImageResource(R.drawable.alarm_n);
			imageView.invalidate();
		}
		
		if (_selected == 1) {
			ImageView imageView = (ImageView)this.getChildAt(1);
			imageView.setImageResource(R.drawable.point_s);
			imageView.invalidate();
		} else {
			ImageView imageView = (ImageView)this.getChildAt(1);
			imageView.setImageResource(R.drawable.point_n);
			imageView.invalidate();
		}
		
		if (_selected == 2) {
			ImageView imageView = (ImageView)this.getChildAt(2);
			imageView.setImageResource(R.drawable.store_s);
			imageView.invalidate();
		} else {
			ImageView imageView = (ImageView)this.getChildAt(2);
			imageView.setImageResource(R.drawable.store_n);
			imageView.invalidate();
		}
		invalidate();
		
		_bitmapCache.clear();
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		int indicatorWidth = getChildAt(0).getWidth();
		
		int position = (int)(event.getX() / indicatorWidth);
		
		if (_listener != null) {
			_listener.onTabSelected(position);
		}
		
		return true;
	}

}
