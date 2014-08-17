package com.onatm.sample.extendedviewpager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class DescriptionFragment extends Fragment {

    private int mDescription;

    public DescriptionFragment(int description)
    {
        mDescription = description;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup mRootView = (ViewGroup) inflater.inflate(R.layout.fragment_description, container, false);

        TextView mTextView = (TextView) mRootView.findViewById(R.id.txt_description);

        mTextView.setText(mDescription);

        return  mRootView;
    }
}
