package com.khaledhikmat.SlidebrowserExample;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;

/*
 * The code idea and structure are taken from Mr. Jim Wilson's Pluralsight Course.
 */
public class MainActivity extends FragmentActivity implements ISlideFragmentManager {
	private final static String LOG_TAG = "Main activity";
	private static final long SLIDE_DELAY = 5000;
	
	private boolean isSliding = true;
	private SlidePageAdapter mSlidePagerAdapter;
	private ViewPager mViewPager;
	private Handler mHandler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSlidePagerAdapter = new SlidePageAdapter(getSupportFragmentManager(), this);

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSlidePagerAdapter);
		
		mHandler = new Handler();
		mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				Log.d(LOG_TAG, "onPageSelected: " + arg0);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				Log.d(LOG_TAG, "onPageScrolled: " + arg0);
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				Log.d(LOG_TAG, "onPageScrollStateChanged: " + arg0);
			}
		});
		
		// Doing so slides the main viewer 300f over 7 seconds
		//mViewPager.animate().translationX(300f).setDuration(7000);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if (mHandler != null)
			mHandler.removeCallbacks(slideViewPager);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (mHandler != null) {
			mHandler.postDelayed(slideViewPager,  SLIDE_DELAY);
			isSliding = true;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public void onDrillThrough(int resourceId) {
		// TODO Auto-generated method stub
		Log.d(LOG_TAG, "onDrillThrough: " + resourceId);
		isSliding = false;
		
		switch (resourceId) {
			case R.drawable.ps_top_card_01: Log.d(LOG_TAG, "card 1 selected"); break;
			case R.drawable.ps_top_card_02: Log.d(LOG_TAG, "card 2 selected"); break;
			case R.drawable.ps_top_card_03: Log.d(LOG_TAG, "card 3 selected"); break;
			case R.drawable.ps_top_card_04: Log.d(LOG_TAG, "card 4 selected"); break;
			case R.drawable.ps_top_card_05: Log.d(LOG_TAG, "card 5 selected"); break;
			case R.drawable.ps_top_card_06: Log.d(LOG_TAG, "card 6 selected"); break;
			case R.drawable.ps_top_card_07: Log.d(LOG_TAG, "card 7 selected"); break;
			default: Log.d(LOG_TAG, "card UNKNOWN selected!!!!"); break;
		}
	}

	private Runnable slideViewPager = new Runnable () {
		public void run() {
			if (isSliding) {
				if ((mViewPager.getCurrentItem() + 1) < mSlidePagerAdapter.getCount()) {
					mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
				} else {
					mViewPager.setCurrentItem(0, true);
				}

				mHandler.postDelayed(slideViewPager,  SLIDE_DELAY);
			}
		}
	};
}
