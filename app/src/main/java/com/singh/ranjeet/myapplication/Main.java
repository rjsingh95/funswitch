package com.singh.ranjeet.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


public class Main extends AppCompatActivity {

    private static final String TAG = "Main";
    private static final int REQUEST_CODE = 1;

    //widgets
    private TabLayout mTabLayout;
    public ViewPager mViewPager;


    public SectionsPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager  = (ViewPager) findViewById(R.id.viewpager_container);
     setupViewPager();

    }

    private void setupViewPager(){
        mPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mPagerAdapter.addFragment(new Today());
        mPagerAdapter.addFragment(new later());

        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setText(getString(R.string.fragment_search));
        mTabLayout.getTabAt(1).setText(getString(R.string.fragment_watch_list));

    }


}
