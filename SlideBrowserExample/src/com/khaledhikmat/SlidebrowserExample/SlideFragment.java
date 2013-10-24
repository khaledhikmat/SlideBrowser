package com.khaledhikmat.SlidebrowserExample;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/*
 * The code idea and structure are taken from Mr. Jim Wilson's Pluralsight Course.
 */
public class SlideFragment extends Fragment {
	private final static String LOG_TAG = "Slide Fragment";
	
	public final static String SLIDE_TITLE = "Slide title";
	public final static String SLIDE_DESCRIPTIONS = "slide description";
	public final static String TOP_CARD = "top card";
	public final static String SLIDE_TYPE_LOGO = "slide type logo";

	private int mTopCardResourceId = -1;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View theView = inflater.inflate(R.layout.fragment_slide_info,  container, false);
		
		Bundle arguments = getArguments();
	    if (arguments != null) {
	        String courseTitle = arguments.getString(SLIDE_TITLE);
	        String courseDescription = arguments.getString(SLIDE_DESCRIPTIONS);
	        int topCardResourceId = arguments.getInt(TOP_CARD);
	        int courseTypeLogoResourceId = arguments.getInt(SLIDE_TYPE_LOGO);

	        displayValues(theView, courseTitle, courseDescription, topCardResourceId, courseTypeLogoResourceId);
	    }
		
		return theView;
	}

	  private void displayValues(View theView, String courseTitle, String courseDescription, int topCardResourceId, int courseTypeLogoResourceId) {
	    TextView courseTitleTextView = (TextView) theView.findViewById(R.id.courseTitle);
	    TextView courseDescriptionTextView = (TextView) theView.findViewById(R.id.courseDescription);
	    ImageView topCardImageView = (ImageView) theView.findViewById(R.id.topCard);
	    ImageView courseTypeLogoImageView = (ImageView) theView.findViewById(R.id.courseTypeLogo);

	    courseTitleTextView.setText(courseTitle);
	    courseDescriptionTextView.setText(courseDescription);

	    topCardImageView.setImageResource(topCardResourceId);
	    courseTypeLogoImageView.setImageResource(courseTypeLogoResourceId);
	    
	    mTopCardResourceId = topCardResourceId; 
	    topCardImageView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Log.d(LOG_TAG, "Image clicked!!");	
				ISlideFragmentManager manager = (ISlideFragmentManager) getActivity();
				if (manager != null) {
					manager.onDrillThrough(mTopCardResourceId);
				}
			}
		});
	    
	    topCardImageView.setAlpha(1f);
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(topCardImageView, "alpha", 0f);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(topCardImageView, "alpha", 1f);
        AnimatorSet as = new AnimatorSet();
        as.playSequentially(fadeOut,fadeIn);
        as.setDuration(5000); //5 secs
        as.start();

//        ObjectAnimator initialFadeOut = ObjectAnimator.ofFloat(topCardImageView, "alpha", 0f);
//        initialFadeOut.setDuration(0);
//        initialFadeOut.start();
//	    
//	    if (topCardImageView.getAlpha() != 0) {
//	        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(topCardImageView, "alpha", 0f);
//	        fadeOut.setDuration(5000);
//	        fadeOut.start();
//    	} else {
//	        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(topCardImageView, "alpha", 1f);
//	        fadeIn.setDuration(5000);
//	        fadeIn.start();
//    	}
	  }
}
