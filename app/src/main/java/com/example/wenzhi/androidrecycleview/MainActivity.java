package com.example.wenzhi.androidrecycleview;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    HomeAdapter mHomeAdaper;
    List<String> mList = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) this.findViewById(R.id.id_recyclerview);
        //设置布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //设置item增加和删除时候的动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        init();
        // 设置自定义分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL_LIST));
        mHomeAdaper = new HomeAdapter(this, mList);

        mRecyclerView.setAdapter(mHomeAdaper);

        mHomeAdaper.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "点击第"+(position + 1)+ "条", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, final int position) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("确认删除吗？")
                        .setNegativeButton("取消",null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mHomeAdaper.removeData(position);
                            }
                        })
                        .show();
            }
        });

    }

    public List<String> init(){
        for (Integer i= 0; i< 20; i++){

            mList.add(i.toString());
        }

        return mList;
    }




}
