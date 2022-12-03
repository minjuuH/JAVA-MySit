package com.example.mysitreview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Placeholder;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder> {
    private ArrayList<DataPlace> list;
    private Context context;
    private rvInterface rvInterface;

    // 생성자로 데이터를 전달받도록함   *
    public PlaceAdapter(ArrayList<DataPlace> list, Context context, rvInterface rvInterface) {
        this.list = list;
        this.context = context;
        this.rvInterface = rvInterface;
    }

    // ViewHolder 객체를 생성하여 리턴한다.
    @NonNull
    @Override
    public PlaceAdapter.PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_placeselect, parent, false);
        PlaceViewHolder holder = new PlaceViewHolder(view, rvInterface);

        return holder;
    }
    //  ViewHolder 안의 내용을 position에 해당하는 데이터로 교체한다.
    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        DataPlace dp = list.get(position);
        holder.title.setText(dp.getTitle());
        holder.introduce.setText(dp.getIntroduce());

    }
    // 전체 데이터의 갯수를 리턴한다.
    @Override
    public int getItemCount() {
        return list.size();
    }

    // 각 뷰를 보관하는 객체
    public static class PlaceViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView introduce;
        public PlaceViewHolder(@NonNull View itemView, rvInterface rvInterface) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            introduce = itemView.findViewById(R.id.tv_introduce);

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
