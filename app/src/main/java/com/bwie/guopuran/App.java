package com.bwie.guopuran;

import android.app.Application;
import android.media.Image;
import android.os.Environment;

import com.bwie.guopuran.dao.DaoMaster;
import com.bwie.guopuran.dao.DaoSession;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import org.greenrobot.greendao.database.Database;

public class App extends Application {
    private static DaoSession mDaoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        //自定义缓存路径
        initFresco();
        DaoMaster.DevOpenHelper openHelper=new DaoMaster.DevOpenHelper(this,null);
        Database database=openHelper.getWritableDb();
        DaoMaster daoMaster=new DaoMaster(database);
         mDaoSession = daoMaster.newSession();
    }

    public static DaoSession getmDaoSession() {
        return mDaoSession;
    }

    public void setmDaoSession(DaoSession mDaoSession) {
        this.mDaoSession = mDaoSession;
    }

    private void initFresco() {
        DiskCacheConfig diskCacheConfig=DiskCacheConfig.newBuilder(this)
                .setBaseDirectoryPath(Environment.getExternalStorageDirectory())
                .setBaseDirectoryName("cache")
                .build();
        ImagePipelineConfig config=ImagePipelineConfig.newBuilder(this)
                .setMainDiskCacheConfig(diskCacheConfig)
                .build();
        Fresco.initialize(this,config);
    }
}
