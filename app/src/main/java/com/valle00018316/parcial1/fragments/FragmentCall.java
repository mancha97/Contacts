package com.valle00018316.parcial1.fragments;


import android.Manifest;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;

import android.support.v4.app.ActivityCompat;

import android.support.v4.app.Fragment;

import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.valle00018316.parcial1.R;
import com.valle00018316.parcial1.adapter.CallRvAdapter;
import com.valle00018316.parcial1.models.ModelCall;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FragmentCall extends Fragment {
    private View v;
    private RecyclerView recyclerView;
    public int code;

    public FragmentCall() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.frag_call, container, false);
        recyclerView = v.findViewById(R.id.rv_call);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager layoutManager = linearLayoutManager;
        recyclerView.setLayoutManager(layoutManager);

        CallRvAdapter adapter = new CallRvAdapter(getContext(), getCallLogs());

        recyclerView.setAdapter(adapter);

        return v;
    }

    public List<ModelCall> getCallLogs() {

        List<ModelCall> list = new ArrayList<>();

        // Here, thisActivity is the current activity
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {


            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.READ_CALL_LOG)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage(R.string.Permissions)
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                requestPermission();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();


            } else {
                requestPermission();
            }
        } else {
            Cursor cursor = getContext().getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, CallLog.Calls.DATE + " ASC");

            int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
            int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);
            int date = cursor.getColumnIndex(CallLog.Calls.DATE);

            cursor.moveToFirst();

            while (cursor.moveToNext()) {

                Date d = new Date(Long.valueOf(cursor.getString(date)));

                DateFormat longDF = DateFormat.getDateInstance(DateFormat.LONG);
                DateFormat shortDfH = DateFormat.getTimeInstance(DateFormat.SHORT);

                list.add(new ModelCall(cursor.getString(number), cursor.getString(duration), longDF.format(d) + " " + shortDfH.format(d)));

                Log.d("MiC::", cursor.getString(number));
            }

        }
        return list;
    }

    public void requestPermission(){
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_CALL_LOG}, code);
    }


        }

