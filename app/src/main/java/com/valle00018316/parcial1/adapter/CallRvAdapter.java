package com.valle00018316.parcial1.adapter;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.valle00018316.parcial1.R;
import com.valle00018316.parcial1.models.ModelCall;

import java.util.List;

public class CallRvAdapter extends RecyclerView.Adapter<CallRvAdapter.ViewHolder>{

    private LayoutInflater layoutInflater;
    private Context nContext;

    private List<ModelCall> nlistCall;

    public CallRvAdapter(Context context, List<ModelCall> listCall){
            nContext=context;
            nlistCall= listCall;
    }


    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        layoutInflater= LayoutInflater.from(nContext);

        View view= layoutInflater.inflate(R.layout.item_call, parent, false);

        ViewHolder viewHolder= new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        TextView name, duration, date;
        name = holder.name;
        duration= holder.duration;
        date = holder.date;

        name.setText(nlistCall.get(position).getNumber());
        duration.setText(nlistCall.get(position).getDuration());
        date.setText(nlistCall.get(position).getDate());

    }

    @Override
    public int getItemCount() {
        return nlistCall.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder {

        TextView name, duration, date;
        public ViewHolder(View itemView) {
            super(itemView);

            name= itemView.findViewById(R.id.contact_name);
            duration= itemView.findViewById(R.id.call_duration);
            date= itemView.findViewById(R.id.call_date);
        }
    }

}
