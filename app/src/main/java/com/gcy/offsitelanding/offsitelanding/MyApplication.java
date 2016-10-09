package com.gcy.offsitelanding.offsitelanding;

        import android.app.Application;

        import org.xutils.x;

        import cn.jpush.android.api.JPushInterface;



/**
 * Created by gcy71 on 2016/10/9.
 */

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        /*xUtils 初始化*/
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
}
