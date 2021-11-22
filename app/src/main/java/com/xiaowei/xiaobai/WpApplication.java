package com.xiaowei.xiaobai;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

public class WpApplication extends Application {
    /**
     * ARouter调试开关
     */
    private boolean mIsDebugARouter = true;

    @Override
    public void onCreate() {
        if (BuildConfig.DEBUG) {
            // 线程检测策略
//            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
//                    .detectDiskReads()
//                    .detectDiskWrites()
//                    .detectNetwork()
//                    .penaltyDeath() //违规则崩溃
//                    .penaltyLog() //违规则打日志
//                    .build()
//            );
            // 虚拟机检测策略
//            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
//                    .detectLeakedSqlLiteObjects() // SqlLite对象泄漏
//                    .detectLeakedClosableObjects() // 未关闭的 Closable对象泄漏
//                    .penaltyDeath() //违规则崩溃
//                    .penaltyLog() //违规则打日志
//                    .build()
//            );
        }
        super.onCreate();
        if (mIsDebugARouter) {
            // 打印日志
            ARouter.openLog();
            // 开启调试模式
            ARouter.openDebug();
        }
        ARouter.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ARouter.getInstance().destroy();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }
}
