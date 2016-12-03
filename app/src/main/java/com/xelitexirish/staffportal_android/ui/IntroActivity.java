package com.xelitexirish.staffportal_android.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.pixelcan.inkpageindicator.InkPageIndicator;
import com.xelitexirish.staffportal_android.MainActivity;
import com.xelitexirish.staffportal_android.R;
import com.xelitexirish.staffportal_android.utils.IntroManager;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by XeliteXirish on 03/12/2016 (www.xelitexirish.com)
 */

public class IntroActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private IntroManager mIntroManager;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private int[] layouts = {R.layout.fragment_intro_first_page,
            R.layout.fragment_intro_second_page,
            R.layout.fragment_intro_third_page};

    private int page = -1;
    private Timer timer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_stepper);
        mIntroManager = new IntroManager(this);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter();

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        InkPageIndicator indicator = (InkPageIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mViewPager);

        final Button mGetStarted = (Button) findViewById(R.id.get_started);

        mGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntroManager.setFirstTimeLaunch(false);
                startActivity(new Intent(IntroActivity.this, MainActivity.class));
                finish();
            }
        });

        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                timer.cancel();
                return false;
            }
        });
        autoScrollViewPager(3000);
    }

    private void autoScrollViewPager(int milliseconds) {
        timer = new Timer();
        timer.scheduleAtFixedRate(new AutoScroll(), 0, milliseconds);
    }

    class AutoScroll extends TimerTask {

        @Override
        public void run() {

            // As the TimerTask run on a seprate thread from UI thread we have
            // to call runOnUiThread to do work on UI thread.
            runOnUiThread(new Runnable() {
                public void run() {
                    if (page >= 2) {
                        page = 0;
                    } else {
                        page = page + 1;
                    }
                    mViewPager.setCurrentItem(page, true);
                }
            });

        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public SectionsPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}
