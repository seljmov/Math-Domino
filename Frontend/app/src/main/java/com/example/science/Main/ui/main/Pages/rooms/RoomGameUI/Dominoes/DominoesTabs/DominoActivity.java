package com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.Dominoes.DominoesTabs;

import android.os.Bundle;

import com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.Dominoes.DominoesTabs.main.Adapters.SectionsPagerAdapter2;
import com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.Dominoes.DominoesTabs.main.Adapters.SectionsPagerAdapter3;
import com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.Dominoes.DominoesTabs.main.Adapters.SectionsPagerAdapter4;
import com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.Dominoes.DominoesTabs.main.Adapters.SectionsPagerAdapter5;
import com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.Dominoes.DominoesTabs.main.Adapters.SectionsPagerAdapter6;
import com.example.science.Main.ui.main.Pages.rooms.RoomGameUI.Dominoes.DominoesTabs.main.DominoFragments.OnBackPressedListener;
import com.example.science.R;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

public class DominoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_domino);

        // О Боже, простите меня за этот костыль...
        int stdCount = getIntent().getIntExtra("stdCount", 0);
        switch (stdCount) {
            case 2:
                SectionsPagerAdapter2 sectionsPagerAdapter2 = new SectionsPagerAdapter2(this, getSupportFragmentManager());
                ViewPager viewPager2 = findViewById(R.id.view_pager);
                viewPager2.setAdapter(sectionsPagerAdapter2);
                TabLayout tabs2 = findViewById(R.id.tabs);
                tabs2.setupWithViewPager(viewPager2);
                break;
            case 3:
                SectionsPagerAdapter3 sectionsPagerAdapter3 = new SectionsPagerAdapter3(this, getSupportFragmentManager());
                ViewPager viewPager3 = findViewById(R.id.view_pager);
                viewPager3.setAdapter(sectionsPagerAdapter3);
                TabLayout tabs3 = findViewById(R.id.tabs);
                tabs3.setupWithViewPager(viewPager3);
                break;
            case 4:
                SectionsPagerAdapter4 sectionsPagerAdapter4 = new SectionsPagerAdapter4(this, getSupportFragmentManager());
                ViewPager viewPager4 = findViewById(R.id.view_pager);
                viewPager4.setAdapter(sectionsPagerAdapter4);
                TabLayout tabs4 = findViewById(R.id.tabs);
                tabs4.setupWithViewPager(viewPager4);
                break;
            case 5:
                SectionsPagerAdapter5 sectionsPagerAdapter5 = new SectionsPagerAdapter5(this, getSupportFragmentManager());
                ViewPager viewPager5 = findViewById(R.id.view_pager);
                viewPager5.setAdapter(sectionsPagerAdapter5);
                TabLayout tabs5 = findViewById(R.id.tabs);
                tabs5.setupWithViewPager(viewPager5);
                break;
            case 6:
                SectionsPagerAdapter6 sectionsPagerAdapter6 = new SectionsPagerAdapter6(this, getSupportFragmentManager());
                ViewPager viewPager6 = findViewById(R.id.view_pager);
                viewPager6.setAdapter(sectionsPagerAdapter6);
                TabLayout tabs6 = findViewById(R.id.tabs);
                tabs6.setupWithViewPager(viewPager6);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        OnBackPressedListener backPressedListener = null;
        for (Fragment fragment: fm.getFragments()) {
            if (fragment instanceof  OnBackPressedListener) {
                backPressedListener = (OnBackPressedListener) fragment;
                break;
            }
        }

        if (backPressedListener != null) {
            backPressedListener.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }
}