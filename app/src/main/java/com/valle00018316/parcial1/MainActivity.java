package com.valle00018316.parcial1;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;

import com.valle00018316.parcial1.adapter.ContactRvAdapter;
import com.valle00018316.parcial1.adapter.ViewPagerAdapter;
import com.valle00018316.parcial1.fragments.FragmentCall;
import com.valle00018316.parcial1.fragments.FragmentContact;
import com.valle00018316.parcial1.fragments.FragmentFav;
import com.valle00018316.parcial1.models.ModelContact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int code=16;

    private final int[] ICONS={R.drawable.phone, R.drawable.avatar, R.drawable.star};
    ViewPagerAdapter adapter= new ViewPagerAdapter(getSupportFragmentManager());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        requestPermission();
        tabLayout= findViewById(R.id.tablayout);
        viewPager= findViewById(R.id.viewpager);



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


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {

        if (requestCode == code
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d("ddd", "ggg: ");





        }
        if (requestCode == code
                && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            Log.d("gggg", "zzz: ");


        }

      if(grantResults[0]==PackageManager.PERMISSION_DENIED || grantResults[1]==PackageManager.PERMISSION_DENIED){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.Permissions)
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            requestPermission();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    public void requestPermission(){
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CALL_LOG,Manifest.permission.READ_CONTACTS},code);

    }

//    private void askPermissions(){
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG)
//                != PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},1);
//            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CALL_LOG},1);
//        }
//    }
}
