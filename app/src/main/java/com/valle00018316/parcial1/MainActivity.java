package com.valle00018316.parcial1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.valle00018316.parcial1.adapter.ViewPagerAdapter;
import com.valle00018316.parcial1.fragments.FragmentCall;
import com.valle00018316.parcial1.fragments.FragmentContact;
import com.valle00018316.parcial1.fragments.FragmentFav;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private final int[] ICONS={R.drawable.phone, R.drawable.avatar, R.drawable.star};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout= findViewById(R.id.tablayout);
        viewPager= findViewById(R.id.viewpager);

        ViewPagerAdapter adapter= new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new FragmentCall(),getString(R.string.Calls));
        adapter.addFragment(new FragmentContact(),getString(R.string.Contacts));
        adapter.addFragment(new FragmentFav(),getString(R.string.Favorites));

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);



        for(int i=0; i<tabLayout.getTabCount(); i++){
            TabLayout.Tab tab= tabLayout.getTabAt(i);
            tab.setIcon(ICONS[i]);
        }
    }

//    private void askPermissions(){
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG)
//                != PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},1);
//            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CALL_LOG},1);
//        }
//    }
}
