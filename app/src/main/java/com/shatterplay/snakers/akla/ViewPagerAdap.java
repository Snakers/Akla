package com.shatterplay.snakers.akla;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by snakers on 10/16/2017.
 */

class ViewPagerAdap extends FragmentPagerAdapter {
    public ViewPagerAdap(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragmentUser();
            case 1:
                return new Fragment_res();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
       if(position == 0){
           return "Meals";
       }
       if(position ==1){
           return "Restaurant";
       }
        return null;
    }
}
