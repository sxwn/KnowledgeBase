package com.xiaowei.xiaobai;

import android.app.Application;
import android.os.StrictMode;

public class WpApplication extends Application {

    @Override
    public void onCreate() {
        if (BuildConfig.DEBUG) {
            // 线程检测策略
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()
                    .penaltyDeath() //违规则崩溃
                    .penaltyLog() //违规则打日志
                    .build()
            );
            // 虚拟机检测策略
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects() // SqlLite对象泄漏
                    .detectLeakedClosableObjects() // 未关闭的 Closable对象泄漏
                    .penaltyDeath() //违规则崩溃
                    .penaltyLog() //违规则打日志
                    .build()
            );
        }

        super.onCreate();
    }
}
