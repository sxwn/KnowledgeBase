package com.xiaowei.xiaobai.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * 懒加载fragment
 * @author weip
 */
public abstract class LazyFragment extends Fragment {
    /**
     * 根布局
     */
    private View mRootView;

    /**
     * 根布局是否已经创建
     */
    private boolean mIsViewCreated = false;

    /**
     * 当前view可见状态
     */
    private boolean mCurrentVisiableState = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutRes(),container,false);
        }
        initView(mRootView);
        mIsViewCreated = true;
        if (getUserVisibleHint()) {
            dispatchUserVisiableHint(true);
        }
        return mRootView;
    }

    protected abstract void initView(View rootView);

    protected abstract int getLayoutRes();

    // 判断fragment是否可见
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (mIsViewCreated) {
            if (!mCurrentVisiableState && isVisibleToUser) {
                dispatchUserVisiableHint(true);
            } else if (mCurrentVisiableState && !isVisibleToUser) {
                dispatchUserVisiableHint(false);
            }
        }
    }

    // 用于统一控制页面数据加载
    private void dispatchUserVisiableHint(boolean visiableState) {
        if (mCurrentVisiableState == visiableState) {
            return;
        }
        mCurrentVisiableState = visiableState;
        if (visiableState) {
            onFragmentLoad();
        } else {
            onFragmentLoadStop();
        }
    }

    //停止加载数据，可选项，不是必选项
    private void onFragmentLoadStop() {
    }

    //加载数据，可选项，不是必选项
    private void onFragmentLoad() {
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!mCurrentVisiableState && getUserVisibleHint()) {
            dispatchUserVisiableHint(false);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        // fragment跳转到一个新的activity的回调
        if (mCurrentVisiableState && getUserVisibleHint()) {
            dispatchUserVisiableHint(false);
        }
    }
}
