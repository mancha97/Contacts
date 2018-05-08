package com.valle00018316.parcial1;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.TextView;

import com.valle00018316.parcial1.Helper.LocaleHelper;
import com.valle00018316.parcial1.adapter.ContactRvAdapter;
import com.valle00018316.parcial1.adapter.ViewPagerAdapter;
import com.valle00018316.parcial1.fragments.FragmentCall;
import com.valle00018316.parcial1.fragments.FragmentContact;
import com.valle00018316.parcial1.fragments.FragmentFav;
import com.valle00018316.parcial1.models.ModelContact;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    private static TabLayout tabLayout;
    public static ViewPager viewPager;
    private int code=16;
    public static int c=1;


    private final static int[] ICONS={R.drawable.phone,R.drawable.avatar , R.drawable.star};
    ViewPagerAdapter adapter= new ViewPagerAdapter(getSupportFragmentManager());
    public final FragmentCall fragC=new FragmentCall();
    public final FragmentContact fragC2=new FragmentContact();
    public final FragmentFav fragF=new FragmentFav();

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase,"en"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        requestPermission();
        tabLayout= findViewById(R.id.tablayout);
        viewPager= findViewById(R.id.viewpager);


        //Init Paper
        Paper.init(this);

        //Default language is English
        String language= Paper.book().read("language");
        if (language==null){
            Paper.book().write("language","en");
        }


        adapter.addFragment(fragC,getString(R.string.Calls));
        adapter.addFragment(fragC2,getString(R.string.Contacts));


        adapter.addFragment(fragF,getString(R.string.Favorites));

//        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        updateView((String)Paper.book().read("language"));






    }

    private void updateView(String lang) {
            Context context=LocaleHelper.setLocale(this,lang);
            Resources resources = context.getResources();

            adapter.setFragList(0,resources.getString(R.string.Calls),fragC);
            adapter.setFragList(1,resources.getString(R.string.Contacts),fragC2);
            adapter.setFragList(2,resources.getString(R.string.Favorites),fragF);


            for(int i=0; i<tabLayout.getTabCount(); i++){
                TabLayout.Tab tab= tabLayout.getTabAt(i);
            tab.setIcon(ICONS[i]);
            }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.language_en){
            Paper.book().write("language","en");
            updateView((String)Paper.book().read("language"));
        }
        else if(item.getItemId()==R.id.language_es){
            Paper.book().write("language","es");
            updateView((String)Paper.book().read("language"));
        }
        else if(item.getItemId()==R.id.searchbar) {
            final SearchView searchView = (SearchView) item.getActionView();


            searchView.setOnCloseListener(new SearchView.OnCloseListener() {
                @Override
                public boolean onClose() {

                    FragmentContact.allist();
                    updatevp(1);
                    return false;
                }
            });

            searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener()
            {
                @Override
                public void onFocusChange(View v, boolean newViewFocus)
                {
                    if (!newViewFocus)
                    {
                        FragmentContact.allist();
                        updatevp(1);

                    }
                }
            });





            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {



                    return false;
                }



                @Override
                public boolean onQueryTextChange(String newText) {
                    Log.d("ww", newText);

                        fragC2.filter(newText);
                        viewPager.setCurrentItem(1);
                        FragmentContact.updatelist();
                        updatevp(1);

                    return false;
                }
            });




        }



        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {

        if (requestCode == code
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


            viewPager.setAdapter(adapter);
            for(int i=0; i<tabLayout.getTabCount(); i++){
                TabLayout.Tab tab= tabLayout.getTabAt(i);
                tab.setIcon(ICONS[i]);
            }



        }
        if (requestCode == code
                && grantResults[1] == PackageManager.PERMISSION_GRANTED) {



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

    public static void updatevp(int d){
        viewPager.setAdapter(viewPager.getAdapter());
        for(int i=0; i<tabLayout.getTabCount(); i++){
            TabLayout.Tab tab= tabLayout.getTabAt(i);
            tab.setIcon(ICONS[i]);
        }
        viewPager.setCurrentItem(d);
    }

//    private void askPermissions(){
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG)
//                != PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},1);
//            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CALL_LOG},1);
//        }
//    }
}
