//package com.example.mysitreview;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//
//public class AdapterPlace extends RecyclerView.Adapter<AdapterPlace.CustomViewHolder> {
//    //===== [Click 이벤트 구현을 위해 추가된 코드] ==========================
//    // OnItemClickListener 인터페이스 선언
//    public interface OnItemClickListener {
//        void onItemClicked(int position, String data);
//    }
//
//    // OnItemClickListener 참조 변수 선언
//    private OnItemClickListener itemClickListener;
//
//    // OnItemClickListener 전달 메소드
//    public void setOnItemClickListener (OnItemClickListener listener) {
//        itemClickListener = listener;
//    }
//    //======================================================================
//    private ArrayList<DataPlace> arrayList;
//    private final rvInterface rvInterface;
//
//    //Construct
//    public AdapterPlace(ArrayList<DataPlace> arrayList, rvInterface rvInterface) {
//        this.arrayList = arrayList;
//        this.rvInterface = rvInterface;
//    }
//
//    @NonNull
//    @Override
//    public AdapterPlace.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_placeselect, parent, false);
//        CustomViewHolder holder = new CustomViewHolder(view, rvInterface);
//        //===== [Click 이벤트 구현을 위해 추가된 코드] =====================
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String data = "";
//                int position = viewHolder.getAdapterPosition();
//                if (position != RecyclerView.NO_POSITION) {
//                    data = viewHolder.getTextView().getText().toString();
//                }
//                itemClickListener.onItemClicked(position, data);
//            }
//        });
//        //==================================================================
//        return holder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull AdapterPlace.CustomViewHolder holder, int position) {
//        //arrayList의 요소를 item 레이아웃의 객체에 지정
//        //holder.pic.setImageResource(arrayList.get(position).getPic());
//        holder.title.setText(arrayList.get(position).getTitle());
//        holder.introduce.setText(arrayList.get(position).getIntroduce());
//        //클릭 이벤트 구현?
//        //holder.itemView.setTag(position);
//        //holder.itemView.setOnClickListener(new View.OnClickListener() {
//        //@Override
//        //public void onClick(View view) {
//        //String curName = holder.title.getText().toString();
//        //Toast.makeText(view.getContext(), curName, Toast.LENGTH_SHORT).show();
//        //}
//        //});
//
//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) { return true; }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return (null != arrayList ? arrayList.size() : 0);
//    }
//
//    public static class CustomViewHolder extends RecyclerView.ViewHolder {
//        //recyclerview의 item 레이아웃의 구성 요소를 담을 참조 선언
//        //protected ImageView pic;
//        protected TextView title;
//        protected TextView introduce;
//        //protected TextView starttime;
//        //protected TextView endtime;
//
//        public CustomViewHolder(@NonNull View itemView, rvInterface rvInterface) {
//            super(itemView);    //참조할 item 레이아웃
//
//            //super로 지정한 레이아웃의 구성 요소를 불러옴
//            //this.pic = (ImageView) itemView.findViewById(R.id.pic);
//            this.title = (TextView) itemView.findViewById(R.id.tv_title);
//            this.introduce = (TextView) itemView.findViewById(R.id.tv_introduce);
//            //this.starttime = (TextView) itemView.findViewById(R.id.date);
//            //this.endtime = (TextView) itemView.findViewById(R.id.remain);
//
//            //필요없는 textView 삭제
//            //starttime.setEnabled(false);
//            //starttime.setVisibility(View.GONE);
//            //endtime.setEnabled(false);
//            //endtime.setVisibility(View.GONE);
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (rvInterface != null){
//                        int pos = getBindingAdapterPosition();
//
//                        if(pos!=RecyclerView.NO_POSITION){
//                            rvInterface.onItemClick(pos);
//                        }
//                    }
//                }
//            });
//        }
//    }
//}
//
//
