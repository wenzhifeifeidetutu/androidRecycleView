package com.example.wenzhi.androidrecycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by wenzhi on 2018/1/18.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    private List<String>  mList;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public HomeAdapter(Context mContext, List<String>mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    public void removeData(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_recycler, parent, false));
                return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.textView.setText(mList.get(position));
        if (mOnItemClickListener != null) {
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.textView, pos);
                }
            });

            holder.textView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(holder.textView, pos);

                    return false;

                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyViewHolder(View inflate) {
            super(inflate);
            textView = (TextView)inflate.findViewById(R.id.text_view);

        }
    }


    //自定义点击事件
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);


    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

}
