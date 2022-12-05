package com.example.mysitreview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterFreePost extends RecyclerView.Adapter<AdapterFreePost.PlaceViewHolder> {
    private ArrayList<BoardAdapt> arrayList;
    private final rvInterface rvInterface;

    //Construct
    public AdapterFreePost(ArrayList<BoardAdapt> arrayList, rvInterface rvInterface) {
        this.arrayList = arrayList;
        this.rvInterface = rvInterface;
    }

    @NonNull
    @Override
    public AdapterFreePost.PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        PlaceViewHolder holder = new PlaceViewHolder(view, rvInterface);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFreePost.PlaceViewHolder holder, int position) {
        //arrayList의 요소를 item 레이아웃의 객체에 지정
        BoardAdapt ba = arrayList.get(position);
        //holder.pic.setImageResource(arrayList.get(position).getPic());
        holder.title.setText(ba.getTitle());
        holder.intro.setText(ba.getIntro());
        String dldate = ba.getStime()+" - "+ba.getEtime();
        holder.deadline.setText(dldate);
        holder.remain.setText(ba.getMax_popul());

        //클릭 이벤트 구현?
        //holder.itemView.setTag(position);
        //holder.itemView.setOnClickListener(new View.OnClickListener() {
        //@Override
        //public void onClick(View view) {
        //String curName = holder.title.getText().toString();
        //Toast.makeText(view.getContext(), curName, Toast.LENGTH_SHORT).show();
        //}
        //});

        /*holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return true;
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class PlaceViewHolder extends RecyclerView.ViewHolder {
        //recyclerview의 item 레이아웃의 구성 요소를 담을 참조 선언
        //protected ImageView pic;
        protected TextView title;
        protected TextView intro;
        protected TextView deadline;
        protected TextView remain;

        public PlaceViewHolder(@NonNull View itemView, rvInterface rvInterface) {
            super(itemView);    //참조할 item 레이아웃

            //super로 지정한 레이아웃의 구성 요소를 불러옴
            // this.pic = (ImageView) itemView.findViewById(R.id.pic);
            this.title = (TextView) itemView.findViewById(R.id.tv_title);
            this.intro = (TextView) itemView.findViewById(R.id.tv_introduce);
            this.deadline = (TextView) itemView.findViewById(R.id.date);
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
