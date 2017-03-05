package com.bwie.montha.app;

import android.app.Application;
import android.os.Environment;

import com.baidu.mapapi.SDKInitializer;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;

/**
 * Created by lenovo on 2017/2/23.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(this);
        GlideBuilder build = new GlideBuilder(getApplicationContext());
        build.setDiskCache(new DiskLruCacheFactory(Environment.getExternalStorageDirectory().getName()+"/zhao", DiskCache.Factory.DEFAULT_DISK_CACHE_SIZE));
    }
}
