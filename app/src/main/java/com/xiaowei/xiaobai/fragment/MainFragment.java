package com.xiaowei.xiaobai.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.xiaowei.xiaobai.IFragmentCallBack;
import com.xiaowei.xiaobai.R;

/**
 * bundle
 * 接口回调（java中）
 * 观察者模式 eventbus、liveData 发布订阅模式
 * 生命周期：
 * 打开主界面：
 * onCreate ---> onCreateView -->onActivityCreate -->onStart --->onResume
 * 按下主屏键
 * onPause --->onStop
 * 重新打开界面
 * onStart --->onResume
 * 按后退键
 * onPause --->onStop --->onDestroyView---->onDestroy--->onDetach
 */
public class MainFragment extends LazyFragment {

    private static final String TAG = "MainFragment";

    private IFragmentCallBack mIFragmentCallBack;

    public void setIFragmentCallBack(IFragmentCallBack callBack) {
        mIFragmentCallBack = callBack;
    }

    public MainFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            String message = arguments.getString("message");
            Log.d(TAG, "onCreate: " + message);
        }
    }

    @Override
    protected void initView(View rootView) {
        TextView titleView = rootView.findViewById(R.id.fragment_main_title);
        rootView.findViewById(R.id.fragment_main_btn).setOnClickListener(v -> {
            titleView.setText("i'm work| and you?");
            if (mIFragmentCallBack != null) {
                mIFragmentCallBack.sendMessageToActivity("hello, i'm from main fragment");
                String messageFromActivity = mIFragmentCallBack.getMessageFromActivity(null);
                Log.d(TAG, "initView: " + messageFromActivity);
            }
        });
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_main;
    }
}
