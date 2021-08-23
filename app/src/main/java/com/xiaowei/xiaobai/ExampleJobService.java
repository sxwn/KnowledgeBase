package com.xiaowei.xiaobai;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

public class ExampleJobService extends JobService {
    private static final String TAG = "ExampleJobService";

    private boolean mJobCanceled = false;

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "onStartJob: Job started");
        // 返回true，表示该工作耗时，同时工作处理完成后需要调用onStopJob销毁（jobFinished）
        // 返回false，任务运行不需要很长时间，到return时已完成任务处理
        doBackgroundWork(params);
        return true;
    }

    private void doBackgroundWork(JobParameters params) {
        new Thread(() -> {
            for (int i = 0; i< 10;i++) {
                Log.d(TAG, "run: " + i);
                if (mJobCanceled)
                    return;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Log.d(TAG, "Job finished");

            // 返回值为true，我们需要手动调用jobFinished来停止该任务
            jobFinished(params,false);
        }).start();
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "onStopJob: Job canceled before completion");
        // 有且仅有onStartJob返回值为true时，才会调用onStopJob来销毁job
        // 返回false来销毁这个工作
        mJobCanceled = true;
        return true;
    }
}
