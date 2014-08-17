package com.onatm.library.extendedviewpager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

public class ParallaxImageViewPager extends ViewPager {

    private Bitmap mBitmap;
    private Rect mSource, mDestination;
    private int mChunkWidth;
    private int mProjectedWidth;

    private float mOverlap = 0.5f;

    private OnPageChangeListener mExternalOnPageChangeListener;

    public ParallaxImageViewPager(Context context) {
        super(context);
        init();
    }

    public ParallaxImageViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mSource = new Rect();
        mDestination = new Rect();

        setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (mBitmap != null) {
                    mSource.left = (int) Math.floor((position + positionOffset) * mChunkWidth);
                    mSource.right = (int) Math.ceil((position + positionOffset) * mChunkWidth + mProjectedWidth);
                    mDestination.left = (int) Math.floor((position + positionOffset) * getWidth());
                    mDestination.right = (int) Math.ceil((position + positionOffset + 1) * getWidth());
                    invalidate();
                }

                if (mExternalOnPageChangeListener != null) {
                    mExternalOnPageChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
                }
            }

            @Override
            public void onPageSelected(int position) {
                if (mExternalOnPageChangeListener != null) {
                    mExternalOnPageChangeListener.onPageSelected(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (mExternalOnPageChangeListener != null) {
                    mExternalOnPageChangeListener.onPageScrollStateChanged(state);
                }
            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)  {
        super.onSizeChanged(w, h, oldw, oldh);
        mDestination.top = 0;
        mDestination.bottom = h;
        if (getAdapter() != null && mBitmap != null) {
            calculateParallaxParameters();
        }
    }

    private void calculateParallaxParameters() {
        if (mBitmap.getWidth() < getWidth() && mBitmap.getWidth() < mBitmap.getHeight()) {
            //throw new InvalidPropertiesFormatException("Invalid bitmap bounds for the current device, parallax effect will not work.");
        }

        float ratio = (float) getHeight() / mBitmap.getHeight();

        if (ratio != 1) {
            mSource.top = 0;
            mSource.bottom = mBitmap.getHeight();
            mProjectedWidth = (int) Math.ceil(getWidth() / ratio);
            mChunkWidth = (int) Math.ceil((mBitmap.getWidth() - mProjectedWidth) / (float) getAdapter().getCount() * mOverlap);
        }
    }

    @Override
    public void setBackgroundResource(int resid) {
        mBitmap = BitmapFactory.decodeResource(getResources(), resid);
    }

    @Override
    public void setBackground(Drawable background) {
        mBitmap = ((BitmapDrawable) background).getBitmap();
    }

    public ParallaxImageViewPager setBackground(Bitmap bitmap) {
        mBitmap = bitmap;
        return this;
    }

    public ParallaxImageViewPager setOverlapPercentage(final float percentage) {
        if (percentage <= 0 || percentage >= 1) {
            throw new IllegalArgumentException("Illegal argument: percentage must be between 0 and 1");
        }

        mOverlap = percentage;
        return this;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mBitmap != null) {
            canvas.drawBitmap(mBitmap, mSource, mDestination, null);
        }
    }

    public void setExternalOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
         mExternalOnPageChangeListener = listener;
    }
}
