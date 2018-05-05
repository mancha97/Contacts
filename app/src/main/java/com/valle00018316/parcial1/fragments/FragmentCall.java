package com.valle00018316.parcial1.fragments;


import android.Manifest;


import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;

import android.support.v4.app.ActivityCompat;

import android.support.v4.app.Fragment;

import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
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

//                requestPermission();


            }
        else {
            Cursor cursor = getContext().getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, CallLog.Calls.DATE + " DESC");


            int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);
            int date = cursor.getColumnIndex(CallLog.Calls.DATE);
            int type = cursor.getColumnIndex(CallLog.Calls.TYPE);




            while (cursor.moveToNext()) {
                int name = cursor.getColumnIndex(CallLog.Calls.CACHED_NAME);
                Date d = new Date(Long.valueOf(cursor.getString(date)));

                DateFormat longD = DateFormat.getDateInstance(DateFormat.LONG);
                DateFormat shortDf = DateFormat.getTimeInstance(DateFormat.SHORT);

                String callType = cursor.getString(type);
                String nam= cursor.getString(name);

                if(nam==null){
                    name=cursor.getColumnIndex(CallLog.Calls.NUMBER);
                }

                String dir = null;
                int dircode = Integer.parseInt(callType);
                switch (dircode) {
                    case CallLog.Calls.OUTGOING_TYPE:
                        dir = getString(R.string.outgoing);

                        break;

                    case CallLog.Calls.INCOMING_TYPE:
                        dir = getString(R.string.incoming);
                        break;

                    case CallLog.Calls.MISSED_TYPE:
                        dir = getString(R.string.missed);
                        break;
                }



                list.add(new ModelCall(cursor.getString(name), CalcularTiempo(cursor.getString(duration)), longD.format(d) + "\n" + shortDf.format(d)));


            }

        }
        return list;
    }


    private String CalcularTiempo(String segundos)
    {
        String txtH, txtM, txtS;
        int tsegundos = Integer.parseInt(segundos);

        int horas = (tsegundos / 3600);
        int minutos = ((tsegundos-horas*3600)/60);
        int segundo = tsegundos-(horas*3600)-(minutos*60);

        String duracion;

        if(horas <= 9){
            txtH = "0" + Integer.toString(horas);
        } else{ txtH = Integer.toString(horas); }

        if(minutos <= 9){
            txtM = "0" + Integer.toString(minutos);
        } else{ txtM = Integer.toString(minutos); }

        if(segundo <= 9){
            txtS = "0" + Integer.toString(segundo);
        } else{ txtS = Integer.toString(segundo); }


        duracion = txtH + ": " + txtM + ": " + txtS;
        return duracion;
    }

        }

