package com.khaledhikmat.SlidebrowserExample;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SlidePageAdapter extends FragmentPagerAdapter {
	String[] mSlideTitles;
	String[] mSlideTitlesShort;
	String[] mSlideDescriptions;
	
	public SlidePageAdapter(FragmentManager fm, Context context) {
		super(fm);
		
	    Resources resources = context.getResources();
	    mSlideTitles = resources.getStringArray(R.array.course_titles);
	    mSlideTitlesShort = resources.getStringArray(R.array.course_titles_short);
	    mSlideDescriptions = resources.getStringArray(R.array.course_descriptions);
	}

	@Override
	public Fragment getItem(int i) {
		Bundle arguments = new Bundle();
	    arguments.putString(SlideFragment.SLIDE_TITLE, mSlideTitles[i]);
	    arguments.putString(SlideFragment.SLIDE_DESCRIPTIONS, mSlideDescriptions[i]);
	    arguments.putInt(SlideFragment.TOP_CARD, translateTopCardIndex(i));
	    arguments.putInt(SlideFragment.SLIDE_TYPE_LOGO, R.drawable.ps_android_logo);

	    SlideFragment courseFragment = new SlideFragment();
	    courseFragment.setArguments(arguments);
		return courseFragment;
	}

	@Override
	public int getCount() {
		return mCourseTitlesShort.length;
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		return mCourseTitlesShort[position];
	}

	private int translateTopCardIndex(int i) {
		int resourceId = 0;
		switch (i) {
		case 0:
			resourceId = R.drawable.ps_top_card_01;
			break;
		case 1:
			resourceId = R.drawable.ps_top_card_02;
			break;
		case 2:
			resourceId = R.drawable.ps_top_card_03;
			break;
		case 3:
			resourceId = R.drawable.ps_top_card_04;
			break;
		case 4:
			resourceId = R.drawable.ps_top_card_05;
			break;
		case 5:
			resourceId = R.drawable.ps_top_card_06;
			break;
		case 6:
			resourceId = R.drawable.ps_top_card_07;
			break;
		}
		
		return resourceId;
	}
}
