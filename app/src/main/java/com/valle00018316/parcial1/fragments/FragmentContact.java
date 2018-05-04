package com.valle00018316.parcial1.fragments;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.valle00018316.parcial1.R;

public class FragmentContact extends Fragment {

    private View v;

    public FragmentContact() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v=inflater.inflate(R.layout.frag_contact,container,false);
        return v;
    }
}
