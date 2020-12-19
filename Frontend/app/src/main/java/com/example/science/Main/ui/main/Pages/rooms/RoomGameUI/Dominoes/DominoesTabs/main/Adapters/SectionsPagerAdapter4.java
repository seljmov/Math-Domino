package com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.Dominoes.DominoesTabs.main.Adapters;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.Dominoes.DominoesTabs.main.DominoFragments.DominoListFour;
import com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.Dominoes.DominoesTabs.main.DominoFragments.DominoListOne;
import com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.Dominoes.DominoesTabs.main.DominoFragments.DominoListThree;
import com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.Dominoes.DominoesTabs.main.DominoFragments.DominoListTwo;
import com.example.science.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter4 extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;

    public SectionsPagerAdapter4(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new DominoListOne();
                break;
            case 1:
                fragment = new DominoListTwo();
                break;
            case 2:
                fragment = new DominoListThree();
                break;
            case 3:
                fragment = new DominoListFour();
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return "Задачи";
    }

    @Override
    public int getCount() {
        return 4;
    }
}