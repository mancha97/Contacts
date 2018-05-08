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

import static com.valle00018316.parcial1.MainActivity.updatevp;
import static com.valle00018316.parcial1.MainActivity.viewPager;
import static com.valle00018316.parcial1.fragments.FragmentContact.list;

public class FavRvAdapter extends RecyclerView.Adapter<FavRvAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private Context mcontext;
    public  List<ModelContact> mListFavs;

    boolean favo;

    public FavRvAdapter(Context mcontext, List<ModelContact> mListFavs) {
        this.mcontext = mcontext;
        this.mListFavs = mListFavs;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.item_contact, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        TextView contact_name, contact_number;
        ImageView boton;

        contact_name = holder.contact_name;
        contact_number = holder.contact_number;
        boton =holder.boton;

        contact_name.setText(mListFavs.get(position).getName());
        contact_number.setText(mListFavs.get(position).getNumber());
        boton.setImageResource(mListFavs.get(position).isFav()?R.drawable.starfull:R.drawable.star);



        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (!favo) {
                    if (!holder.fav) {
//                        mListFavs.get(position).setFav(false);

                        for (int i = 0; i<list.size();i++){

                            if(mListFavs.get(position).getName().equals(list.get(i).getName()) && mListFavs.get(position).getNumber().equals(list.get(i).getNumber() )) {
                                list.get(i).setFav(false);
                            }
                        }




                        holder.boton.setImageResource(R.drawable.star);
                        holder.fav=false;



                    } else {
//                        mListFavs.get(position).setFav(true);
                        for (int i = 0; i<list.size();i++){

                            if(mListFavs.get(position).getName().equals(list.get(i).getName()) && mListFavs.get(position).getNumber().equals(list.get(i).getNumber() )) {
                                list.get(i).setFav(true);
                            }
                        }
                        holder.boton.setImageResource(R.drawable.starfull);
                        holder.fav=true;



                    }

                } else {
                    if (holder.fav) {
                        mListFavs.get(position).setFav(false);
                        for (int i = 0; i<list.size();i++){

                            if(mListFavs.get(position).getName().equals(list.get(i).getName())) {
                                list.get(i).setFav(false);
                            }
                        }
                        holder.boton.setImageResource(R.drawable.starfull);
                        holder.fav = true;



                    } else {
                        mListFavs.get(position).setFav(false);

                        holder.boton.setImageResource(R.drawable.star);
                        holder.fav = false;


                    }

                }
                updatevp(2);
//

            }
        });


    }

    @Override
    public int getItemCount() {
        return mListFavs.size();
    }


    public  class ViewHolder extends RecyclerView.ViewHolder {


        TextView contact_name, contact_number;
        ImageView boton;
        boolean fav;

        public ViewHolder(View itemView){
            super(itemView);

            contact_name = itemView.findViewById(R.id.contact_name);
            contact_number = itemView.findViewById(R.id.contact_num);
            boton = itemView.findViewById(R.id.contact_fav);

        }
    }
}
