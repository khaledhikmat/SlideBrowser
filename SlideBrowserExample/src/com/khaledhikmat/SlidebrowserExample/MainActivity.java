package com.khaledhikmat.SlidebrowserExample;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity implements ISlideFragmentManager {
	private final static String LOG_TAG = "Main activity";
	private static final long SLIDE_DELAY = 5000;
	
	private boolean isSliding = true;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
	public void onDrillThrough(int resourceId) {
    	
    }
}
