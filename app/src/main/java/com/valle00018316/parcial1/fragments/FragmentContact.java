package com.valle00018316.parcial1.fragments;


import android.os.Bundle;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;

import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.valle00018316.parcial1.R;
import com.valle00018316.parcial1.adapter.ContactRvAdapter;
import com.valle00018316.parcial1.models.ModelContact;

import java.util.ArrayList;
import java.util.List;

public class FragmentContact extends Fragment {

    private View v;
    private RecyclerView recyclerView;
    private int request = 14;

    public FragmentContact() {


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.frag_contact, container, false);
        recyclerView = v.findViewById(R.id.rv_contact);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager layoutManager = linearLayoutManager;
        recyclerView.setLayoutManager(layoutManager);
        ContactRvAdapter adapter = new ContactRvAdapter(getContext(), getContacts());
        recyclerView.setAdapter(adapter);



        return v;


    }

    private List<ModelContact> getContacts() {

        List<ModelContact> list = new ArrayList<>();

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
//            requestPermission();
        } else {

            Cursor cursor = getContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, ContactsContract.Contacts.DISPLAY_NAME + " ASC");

            while (cursor.moveToNext()) {

                list.add(new ModelContact(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)),
                        cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))));

            }

            cursor.close();
        }

        return list;
    }

//    public void requestPermission() {
//        requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, request);
//    }
//
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//
//        if (requestCode == request && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            ContactRvAdapter adapter = new ContactRvAdapter(getContext(), getContacts());
//            recyclerView.setAdapter(adapter);
//        } else {
//            requestPermission();
//        }
//
//    }
}
