package com.android;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.TextView;

import com.android.adapter.FragmentAdapterC;
import com.android.ui.fragment.FirstFragment;
import com.android.ui.fragment.SecondFragment;
import com.android.util.DeviceInfo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Context mContext = MainActivity.this;

    private ViewPager viewPager;
    private List<Fragment> fragments;
    private FragmentAdapterC adapterC;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragments();
        initView();
    }

    private void initView() {
        viewPager = this.findViewById(R.id.main_viewpager);
        adapterC = new FragmentAdapterC(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapterC);
        viewPager.setCurrentItem(0);


    }

    /**
     * 初始化fragment的个数
     */
    private void initFragments() {
        fragments = new ArrayList<>();

        Fragment firstFragment = new FirstFragment();
        Fragment secondFragment = new SecondFragment();

        fragments.add(firstFragment);
        fragments.add(secondFragment);
    }

}
