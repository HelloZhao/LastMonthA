package com.bwie.montha;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.bwie.montha.adapter.MyAdapter;
import com.bwie.montha.bean.Bean;
import com.bwie.montha.utils.GlideCacheUtil;
import com.bwie.montha.utils.HttpUtils;
import com.bwie.montha.utils.UrlUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button locationBtn;
    private MyAdapter adapter;
    private Button clearBtn;
    private RecyclerView recyclerView;
    private GlideCacheUtil cacheUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationBtn = (Button) findViewById(R.id.locationBtn);
        clearBtn = (Button) findViewById(R.id.clearBtn);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        locationBtn.setOnClickListener(this);
        clearBtn.setOnClickListener(this);
        cacheUtil = GlideCacheUtil.getInstance();
        getDataFromNet();
    }

    /**
     * 从网络获取数据
     */
    private void getDataFromNet() {
        HttpUtils httpUtils = HttpUtils.getHttpUtils();
        httpUtils.loadDataFromNet(UrlUtils.URL_PATH, Bean.class, new HttpUtils.CallBackListener<Bean>() {

            @Override
            public void onSuccess(Bean result) {
                List<Bean.DataBean> dataList = result.getData();
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                adapter = new MyAdapter(dataList,MainActivity.this);
                recyclerView.setAdapter(adapter);
                String cacheSize = cacheUtil.getCacheSize(MainActivity.this);
                clearBtn.setText("清楚缓存"+cacheSize);
            }

            @Override
            public void onFail() {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //清除缓存
            case R.id.clearBtn:
                cacheUtil.clearImageDiskCache(this);
                break;
            //定位按钮
            case R.id.locationBtn:
                startActivity(new Intent(this,LocationActivity.class));
                break;
        }
    }
}
