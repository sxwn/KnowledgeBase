package com.xiaowei.xiaobai;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.xiaowei.xiaobai.fragment.ItemFragment;
import com.xiaowei.xiaobai.fragment.MainFragment;

public class FragmentActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "FragmentActivity";

    private MainFragment mainFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main_activity);
        Button changeBtn = findViewById(R.id.btn_change);
        Button replaceBtn = findViewById(R.id.btn_replace);
        changeBtn.setOnClickListener(this);
        replaceBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_change:
                Bundle bundle = new Bundle();
                bundle.putString("message", "i love enjoy cource");
                mainFragment = new MainFragment();
                mainFragment.setArguments(bundle);
                // 实现一个接口
                IFragmentCallBack callBack = new IFragmentCallBack() {
                    @Override
                    public void sendMessageToActivity(String msg) {
                        Log.d(TAG, "sendMessageToActivity: msg = " + msg);
                    }

                    @Override
                    public String getMessageFromActivity(String msg) {
                        return "hello, i'm FragmentActivity ";
                    }
                };
                mainFragment.setIFragmentCallBack(callBack);
                replaceFragment(mainFragment);
                break;
            case R.id.btn_replace:
                replaceFragment(new ItemFragment());
                break;
        }
    }

    /**
     * 动态切换fragment
     *
     * @param fragment 业务fragment
     */
    private void replaceFragment(Fragment fragment) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity_main_fragment,fragment);
        // 添加到管理栈中
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void changeFragment(Fragment mainFragment) {
    }

    // https://developer.android.google.cn/reference/android/app/job/JobScheduler.html  Android 5.0	21	LOLLIPOP
    public void scheduleJob(View view) {
        ComponentName componentName = new ComponentName(this, ExampleJobService.class);
        JobInfo jobInfo = new JobInfo.Builder(123,componentName)
            .setRequiresCharging(true)// 充电
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)//表示设备不是蜂窝网络
            .setPersisted(true)//  这个方法告诉系统当设备重启之后任务是否还要继续执行
            .setPeriodic(15 * 60 * 1000) //任务运行的周期
            .build();
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = scheduler.schedule(jobInfo);
        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            // 如果此作业已成功安排，则返回
            Log.d(TAG, "scheduleJob: Job scheduled");
        } else {
            // 如果作业未成功安排，则返回
            Log.d(TAG, "scheduleJob: Job scheduling failed");
        }
    }

    public void cancelJob(View view) {
        JobScheduler scheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        scheduler.cancel(123);
        Log.d(TAG, "scheduleJob: Job canceled");
    }
}
