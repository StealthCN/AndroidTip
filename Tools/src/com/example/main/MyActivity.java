package com.example.main;

import com.example.gps.GPSActivity;
import com.example.tip.R;
import com.example.tip.TipActivity;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

public class MyActivity extends FragmentActivity implements TabListener
{
	ViewPager myViewPager;
	MyPagerAdapter myFragmentPagerAdapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.main);
        
        final ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(false);

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        myViewPager = (ViewPager) findViewById(R.id.pager);
        myFragmentPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        myViewPager.setAdapter(myFragmentPagerAdapter);
        actionBar.addTab(actionBar.newTab().setText(R.string.tip).setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText(R.string.gps).setTabListener(this));
        myViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When swiping between different app sections, select the corresponding tab.
                // We can also use ActionBar.Tab#select() to do this if we have a reference to the
                // Tab.
                actionBar.setSelectedNavigationItem(position);
            }
        });

    }
    
	public static class MyPagerAdapter extends FragmentStatePagerAdapter 
	{
		public MyPagerAdapter(FragmentManager fm) 
		{
			super(fm);
		}

		@Override
		public Fragment getItem(int i) 
		{
			Fragment f = new Fragment();
			switch(i)
			{
			case 0:
				f = new TipActivity(); 
				break;
			case 1:
				f = new GPSActivity(); 
				break;
			}
			return f;
		}

		@Override
		public int getCount() 
		{
			return 2;
		}
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction arg1) {}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction arg1) 
	{
		myViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction arg1) {}
}
