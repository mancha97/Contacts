package com.valle00018316.parcial1.adapter;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.valle00018316.parcial1.R;
import com.valle00018316.parcial1.models.ModelCall;

import java.util.List;

public class CallRvAdapter extends RecyclerView.Adapter<CallRvAdapter.ViewHolder>{

    private LayoutInflater layoutInflater;
    private Context nContext;

    private List<ModelCall> nlistCall;
    Dialog mDialog;

    public CallRvAdapter(Context context, List<ModelCall> listCall){
            nContext=context;
            nlistCall= listCall;
    }


    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        layoutInflater= LayoutInflater.from(nContext);

        View view= layoutInflater.inflate(R.layout.item_call, parent, false);

        ViewHolder viewHolder= new ViewHolder(view);
        mDialog = new Dialog(nContext);
        mDialog.setContentView(R.layout.display);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        TextView name, duration, date;
        Button llamada;
        LinearLayout contacto;
        name = holder.name;
        duration= holder.duration;
        date = holder.date;

        name.setText(nlistCall.get(position).getNumber());
        duration.setText(nlistCall.get(position).getDuration());
        date.setText(nlistCall.get(position).getDate());
        llamada = holder.llamada;
        contacto = holder.contacto;

        llamada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialContactPhone(nlistCall.get(position).getNumber());
            }
        });



    }

    @Override
    public int getItemCount() {
        return nlistCall.size();
    }

    private void dialContactPhone(final String phoneNumber) {
        if (ActivityCompat.checkSelfPermission(nContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//              requestPermissions(mcontext,Manifest.permission.CALL_PHONE,1);
        }else{
//            mcontext.startActivity(new Intent(Intent.ACTION_CALL, Uri.fromParts("tel", phoneNumber, null)));
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:"+phoneNumber));
            nContext.startActivity(intent);
        }

    }

    public class ViewHolder extends  RecyclerView.ViewHolder {

        TextView name, duration, date;
        Button llamada;
        LinearLayout contacto;
        Button share;
        public ViewHolder(View itemView) {
            super(itemView);
            contacto = itemView.findViewById(R.id.contact_disp);
            share = itemView.findViewById(R.id.display_share);
            name= itemView.findViewById(R.id.contact_name);
            duration= itemView.findViewById(R.id.call_duration);
            date= itemView.findViewById(R.id.call_date);
            llamada=itemView.findViewById(R.id.contact_call);
        }
    }

}
