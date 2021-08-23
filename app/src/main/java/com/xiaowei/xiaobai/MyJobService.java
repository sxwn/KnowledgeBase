package com.xiaowei.xiaobai;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.PersistableBundle;

import androidx.annotation.NonNull;

import com.xiaowei.xiaobai.utils.LogUtil;
import com.xiaowei.xiaobai.utils.StorageUtil;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 *   Google从Android SDK 21之后添加了JobScheduler来执行一些满足特定条件但不紧急的后台任务，我们可以利用JobScheduler来执行这些特殊的后台任务时来减少电量的消耗。JobService则是一个抽象类，其中包含两个抽象方法：
 *
 * 作者：猴子搬来的救兵
 * 链接：https://juejin.cn/post/6844903446559244296
 * 来源：掘金
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * APP有可以推迟的非面向用户的工作
 * APP有当插入设备时您希望优先执行的工作
 * APP有需要访问网络或 Wi-Fi 连接的任务
 * APP有希望作为一个批次定期运行的许多任务
 *
 *
 */

public class MyJobService extends JobService {
    private OkHttpClient mOkHttpClient;

    private class JobServiceHandler extends Handler {
        public JobServiceHandler(@NonNull Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case ImageActivity.JOB_ID_DOWNLOAD_MEIZI:
                    JobParameters parameters = (JobParameters) msg.obj;
                    final PersistableBundle bundle = parameters.getExtras();
                    String url = bundle.getString(ImageActivity.JOBINFO_EXTRA_KEY_URL);
                    String name = bundle.getString(ImageActivity.JOBINFO_EXTRA_KEY_FILE_NAME);
                    if ( null == url || null == name) {
                        return;
                    }
                    Request request = new Request.Builder()
                            .url(url)
                            .build();
                    try {
                        final Response response = mOkHttpClient.newCall(request).execute();
                        if (response.isSuccessful()) {
                            // 保存下载的图片
                            final ResponseBody body = response.body();
                            if (body != null) {
                                if (StorageUtil.saveImage(name, body.bytes())) {
                                    // 发送广播通知Activity显示图片
                                    Intent intent = new Intent(ImageActivity.RECEIVER_ACTION);
                                    intent.setPackage(getPackageName());
                                    intent.putExtra(ImageActivity.JOBINFO_EXTRA_KEY_FILE_NAME, name);
                                    sendBroadcast(intent);
                                    // 通知系统任务已经完成
                                    jobFinished(parameters, false);
                                    LogUtil.d("Job[" + msg.what + "] finished.");
                                }
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    private JobServiceHandler mHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        HandlerThread handlerThread = new HandlerThread("JobService_thread");
        handlerThread.start();
        mHandler = new JobServiceHandler(handlerThread.getLooper());
        mOkHttpClient = new OkHttpClient();
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        LogUtil.d("onStartJob");
        // 把任务交给工作线程处理
        final Message message = mHandler.obtainMessage();
        message.what = params.getJobId();
        message.obj = params;
        message.sendToTarget();
        // 返回true表明任务在工作线程中继续执行
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        LogUtil.d("onStopJob");
        // 下载图片需要网络，如果网络断开，下载任务会自动发生IO异常，任务就自动中断，因此这里不需要手动停止任务

        // 返回false表示任务不需要再次调度执行
        return false;
    }
}
