package com.valle00018316.parcial1.fragments;


import android.content.Context;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.valle00018316.parcial1.R;
import com.valle00018316.parcial1.adapter.FavRvAdapter;
import com.valle00018316.parcial1.models.ModelContact;

import java.util.ArrayList;
import java.util.List;

import static com.valle00018316.parcial1.fragments.FragmentContact.list;

public class FragmentFav extends Fragment {


    private View v;
    public static RecyclerView recyclerView;

    public static List<ModelContact> favs = new ArrayList<>();
    public static List<Integer> favsref = new ArrayList<>();
    public Context mcontext;


    public FragmentFav() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.frag_fav, container, false);
        recyclerView = v.findViewById(R.id.rv_fav);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager layoutManager = linearLayoutManager;
        recyclerView.setLayoutManager(layoutManager);
        FavRvAdapter adapter = new FavRvAdapter(getContext(), getFavs());
        recyclerView.setAdapter(adapter);


        return v;
    }



    private List<ModelContact> getFavs(){
        favs = new ArrayList<>();
        favsref= new ArrayList<>();

        for (int i = 0; i<list.size();i++){
            if(list.get(i).isFav()) {
                favs.add(new ModelContact(list.get(i).getName(), list.get(i).getNumber(), true));
                favsref.add(i);
            }
        }


        return favs;
    }
}
