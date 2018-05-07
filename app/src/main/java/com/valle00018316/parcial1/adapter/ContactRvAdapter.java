package com.valle00018316.parcial1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.valle00018316.parcial1.R;
import com.valle00018316.parcial1.models.ModelContact;

import java.util.List;

import static com.valle00018316.parcial1.MainActivity.viewPager;

public class ContactRvAdapter extends RecyclerView.Adapter<ContactRvAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private Context mcontext;
    public  List<ModelContact> mListContacts;
    boolean favo;

    public ContactRvAdapter(Context context, List<ModelContact> listContacts) {
        mcontext = context;
        mListContacts = listContacts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.item_contact, parent, false);
        ViewHolder viewHolder = new ViewHolder(view,this.favo);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        TextView contact_name, contact_number;
        ImageView boton;

        contact_name = holder.contact_name;
        contact_number = holder.contact_number;
        boton = holder.boton;

        contact_name.setText(mListContacts.get(position).getName());
        contact_number.setText(mListContacts.get(position).getNumber());
        boton.setImageResource(mListContacts.get(position).isFav()?R.drawable.starfull:R.drawable.star);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(favo){
                    Log.d("MIC", "Esta estrella esta true");}
                else{Log.d("MIC", "Esta estrella esta false");}
                if (!favo) {
                    if (!holder.fav) {
                        mListContacts.get(position).setFav(true);
                        holder.boton.setImageResource(R.drawable.starfull);
                        holder.fav=true;
                        Log.d("MIC", "Llena la estrella");


                    } else {
                        mListContacts.get(position).setFav(false);
                        holder.boton.setImageResource(R.drawable.star);
                        holder.fav=false;
                        Log.d("MIC", "quita la estrella que no existe");


                    }

                } else {
                    if (!holder.fav) {
                        mListContacts.get(position).setFav(true);
                        holder.boton.setImageResource(R.drawable.starfull);
                        holder.fav = true;
                        Log.d("MIC", "Llena la estrella llena222");


                    } else {
                        mListContacts.get(position).setFav(false);
                        holder.boton.setImageResource(R.drawable.star);
                        holder.fav = false;
                        Log.d("MIC", "quita la estrella");

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

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView contact_name, contact_number;
        ImageView boton;
        boolean fav;

        public ViewHolder(View itemView, boolean favo){
            super(itemView);

            contact_name = itemView.findViewById(R.id.contact_name);
            contact_number = itemView.findViewById(R.id.contact_num);
            boton = itemView.findViewById(R.id.contact_fav);
            fav = favo;

        }
    }
}
