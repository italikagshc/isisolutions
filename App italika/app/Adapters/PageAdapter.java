package com.example.italika.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.italika.Fragments.AsistanceFragment;
import com.example.italika.Fragments.MessageFragment;
import com.example.italika.Fragments.ServicesFragment;
import com.example.italika.Fragments.ShopOnlineFragment;
import com.example.italika.Fragments.StadisticsFragment;
import com.example.italika.Fragments.StartFragment;

public class PageAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    public PageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new StartFragment();
            case 1:
                return new ServicesFragment();
            case 2:
                return new StadisticsFragment();
            case 3:
                return new AsistanceFragment();
            case 4:
                return new ShopOnlineFragment();
            case 5:
                return new MessageFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
