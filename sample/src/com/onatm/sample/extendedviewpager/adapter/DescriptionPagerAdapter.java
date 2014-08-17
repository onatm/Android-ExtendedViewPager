package com.onatm.sample.extendedviewpager.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.onatm.sample.extendedviewpager.DescriptionFragment;
import com.onatm.sample.extendedviewpager.R;

import java.util.ArrayList;

public class DescriptionPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> mFragments;

    private final int[] DESCRIPTIONS = new int[] {
            R.string.hello_world,
            R.string.hello_world
    };

    public DescriptionPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragments = new ArrayList<Fragment>();
        for (int description : DESCRIPTIONS) {
            mFragments.add(new DescriptionFragment(description));
        }
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }
}
