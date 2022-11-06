package com.example.mysitreview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CustomViewHolder> {
    private ArrayList<MainData> arrayList;
    private final rvInterface rvInterface;

    //Construct
    public MainAdapter(ArrayList<MainData> arrayList, rvInterface rvInterface) {
        this.arrayList = arrayList;
        this.rvInterface = rvInterface;
    }

    @NonNull
    @Override
    public MainAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view, rvInterface);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.CustomViewHolder holder, int position) {
        holder.pic.setImageResource(arrayList.get(position).getPic());
        holder.title.setText(arrayList.get(position).getTitle());
        holder.remain.setText(arrayList.get(position).getRemain());

        //클릭 이벤트 구현?
        //holder.itemView.setTag(position);
        //holder.itemView.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View view) {
                //String curName = holder.title.getText().toString();
                //Toast.makeText(view.getContext(), curName, Toast.LENGTH_SHORT).show();
            //}
        //});

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
        protected ImageView pic;
        protected TextView title;
        protected TextView remain;

        public CustomViewHolder(@NonNull View itemView, rvInterface rvInterface) {
            super(itemView);
            this.pic = (ImageView) itemView.findViewById(R.id.pic);
            this.title = (TextView) itemView.findViewById(R.id.title);
            this.remain = (TextView) itemView.findViewById(R.id.remain);

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
