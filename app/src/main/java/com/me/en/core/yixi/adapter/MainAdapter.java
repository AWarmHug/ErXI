package com.me.en.core.yixi.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.me.en.core.yixi.ui.AlbumFragment;

/**
 * 作者: 51hs_android
 * 时间: 2017/5/3
 * 简介:
 */

public class MainAdapter extends FragmentStatePagerAdapter {




    public MainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        return AlbumFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 1;
    }
}
