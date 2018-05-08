package com.valle00018316.parcial1.adapter;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.valle00018316.parcial1.R;
import com.valle00018316.parcial1.models.ModelContact;

import java.util.ArrayList;
import java.util.List;

import static com.valle00018316.parcial1.MainActivity.viewPager;
import static com.valle00018316.parcial1.fragments.FragmentContact.list;

public class ContactRvAdapter extends RecyclerView.Adapter<ContactRvAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private Context mcontext;
    public List<ModelContact> mListContacts;
    boolean favo;
    Dialog mDialog;

    public ContactRvAdapter(Context context, List<ModelContact> listContacts) {
        mcontext = context;
        mListContacts = listContacts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.item_contact, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, this.favo);
        mDialog = new Dialog(mcontext);
        mDialog.setContentView(R.layout.display);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        TextView contact_name, contact_number;
        ImageView boton;
        Button llamada;
        LinearLayout contacto;

        contact_name = holder.contact_name;
        contact_number = holder.contact_number;
        boton = holder.boton;
        llamada = holder.llamada;
        contacto = holder.contacto;

        contact_name.setText(mListContacts.get(position).getName());
        contact_number.setText(mListContacts.get(position).getNumber());
        boton.setImageResource(mListContacts.get(position).isFav() ? R.drawable.starfull : R.drawable.star);


        llamada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialContactPhone(mListContacts.get(position).getNumber());
            }
        });

        contacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView tvname = mDialog.findViewById(R.id.display_name);
                final TextView tvnumber = mDialog.findViewById(R.id.display_number);
                tvname.setText(mListContacts.get(position).getName());
                tvnumber.setText(mListContacts.get(position).getNumber());
                mDialog.show();

                ImageView share = mDialog.findViewById(R.id.display_share);
                share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mcontext, "Compartiendo datos", Toast.LENGTH_SHORT).show();

                        Intent shareIntent = new Intent();
                        shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
                        shareIntent.setType("*/*");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, mcontext.getString(R.string.name) + ": " + mListContacts.get(position).getName().toString() + "\n" + mcontext.getString(R.string.num) + ": " + mListContacts.get(position).getNumber().toString());
                        mcontext.startActivity(shareIntent);

                    }
                });
            }




        });



        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!favo) {
                    if (!mListContacts.get(position).isFav()) {
                        mListContacts.get(position).setFav(true);
                        holder.boton.setImageResource(R.drawable.starfull);
                        holder.fav = true;


                    } else {
                        mListContacts.get(position).setFav(false);
                        holder.boton.setImageResource(R.drawable.star);
                        holder.fav = false;


                    }

                } else {
                    if (!holder.fav) {
                        mListContacts.get(position).setFav(true);
                        holder.boton.setImageResource(R.drawable.star);
                        holder.fav = true;


                    } else {
                        mListContacts.get(position).setFav(false);
                        holder.boton.setImageResource(R.drawable.starfull);
                        holder.fav = false;


                    }

                }
//
                viewPager.setCurrentItem(0);
                viewPager.setCurrentItem(1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mListContacts.size();
    }

    private void dialContactPhone(final String phoneNumber) {
        if (ActivityCompat.checkSelfPermission(mcontext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//              requestPermissions(mcontext,Manifest.permission.CALL_PHONE,1);
        }else{
//            mcontext.startActivity(new Intent(Intent.ACTION_CALL, Uri.fromParts("tel", phoneNumber, null)));
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:"+phoneNumber));
            mcontext.startActivity(intent);
        }

    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView contact_name, contact_number;
        ImageView boton;
        Button llamada;
        boolean fav;
        LinearLayout contacto;
        ImageView share;

        public ViewHolder(View itemView, boolean favo){
            super(itemView);

            contacto = itemView.findViewById(R.id.contact_disp);
            share = itemView.findViewById(R.id.display_share);
            contact_name = itemView.findViewById(R.id.contact_name);
            contact_number = itemView.findViewById(R.id.contact_num);
            boton = itemView.findViewById(R.id.contact_fav);
            llamada=itemView.findViewById(R.id.contact_call);
            fav = favo;

        }
    }
}
