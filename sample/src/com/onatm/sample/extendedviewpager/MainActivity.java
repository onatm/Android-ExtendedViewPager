package com.onatm.sample.extendedviewpager;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.onatm.library.extendedviewpager.ParallaxImageViewPager;
import com.onatm.sample.extendedviewpager.adapter.DescriptionPagerAdapter;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParallaxImageViewPager parallaxViewPager = ((ParallaxImageViewPager) findViewById(R.id.parallax_image_viewpager_login));
        parallaxViewPager.setOverlapPercentage(0.50f).setAdapter(new DescriptionPagerAdapter(getSupportFragmentManager()));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}
