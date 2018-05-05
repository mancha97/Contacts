package com.valle00018316.parcial1.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter{

    private List<Fragment> listFragment = new ArrayList<>();
    private List<String> listTitle= new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {

        return listTitle.get(position);
    }

    public void addFragment(Fragment fragment, String title){
        listFragment.add(fragment);
        listTitle.add(title);
    }
    public void setFragList(int i,String hola,Fragment frag){
        listTitle.set(i,hola);
        listFragment.set(i,frag);
    }

}
