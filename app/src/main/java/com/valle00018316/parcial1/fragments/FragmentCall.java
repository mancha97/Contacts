package com.valle00018316.parcial1.fragments;


import android.Manifest;


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
    public int code=1997;

    public FragmentCall() {

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {

        if (requestCode == code
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            CallRvAdapter adapter = new CallRvAdapter(getContext(), getCallLogs());

            recyclerView.setAdapter(adapter);
        }
        else{
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
        }
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


        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {

                requestPermission();


            }
        else {
            Cursor cursor = getContext().getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, CallLog.Calls.DATE + " DESC");

            int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
            int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);
            int date = cursor.getColumnIndex(CallLog.Calls.DATE);

            cursor.moveToFirst();

            while (cursor.moveToNext()) {

                Date d = new Date(Long.valueOf(cursor.getString(date)));

                DateFormat longD = DateFormat.getDateInstance(DateFormat.LONG);
                DateFormat shortDf = DateFormat.getTimeInstance(DateFormat.SHORT);

                list.add(new ModelCall(cursor.getString(number), cursor.getString(duration), longD.format(d) + "\n" + shortDf.format(d)));


            }

        }
        return list;
    }

    public void requestPermission(){
        requestPermissions(new String[]{Manifest.permission.READ_CALL_LOG},code);

    }

        }

