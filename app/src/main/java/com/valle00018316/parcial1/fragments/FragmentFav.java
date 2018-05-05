package com.valle00018316.parcial1.fragments;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.valle00018316.parcial1.R;

public class FragmentFav extends Fragment {

    private View v;

    public FragmentFav() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        v=inflater.inflate(R.layout.frag_fav,container,false);
        Log.d("dd", "se inflo fragmentfav: ");
        return v;
    }
}
