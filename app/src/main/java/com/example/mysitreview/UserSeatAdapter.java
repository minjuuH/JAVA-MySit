package com.example.mysitreview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserSeatAdapter extends RecyclerView.Adapter<UserSeatAdapter.CustomViewHolder> {
    private ArrayList<UserSeatData> arrayList;
    private final rvInterface rvInterface;

    //Construct
    public UserSeatAdapter(ArrayList<UserSeatData> arrayList, rvInterface rvInterface) {
        this.arrayList = arrayList;
        this.rvInterface = rvInterface;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_seatview, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view, rvInterface);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        String sdate = "예약확정일 "+arrayList.get(position).getSdate();
        holder.title.setText(arrayList.get(position).getTitle());
        holder.seatingdate.setText(sdate);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView title;
        protected TextView seatingdate;

        public CustomViewHolder(@NonNull View itemView, rvInterface rvInterface) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.seatName);
            this.seatingdate = (TextView) itemView.findViewById(R.id.seatingDate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (rvInterface != null){
                        int pos = getBindingAdapterPosition();

                        if(pos!=RecyclerView.NO_POSITION){
                            rvInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
