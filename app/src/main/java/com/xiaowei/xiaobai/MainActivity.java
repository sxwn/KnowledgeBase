package com.xiaowei.xiaobai;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ListView;

import com.xiaowei.xiaobai.view.QQStepView;

public class MainActivity extends AppCompatActivity {
    private QQStepView mQQStepView;
    private ValueAnimator mValueAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mQQStepView = findViewById(R.id.step_view);
        mQQStepView.setStepMax(4000);

        // 属性动画
        mValueAnimator = ObjectAnimator.ofInt(0, 3000);
        mValueAnimator.setDuration(1000);
        mValueAnimator.setInterpolator(new DecelerateInterpolator());
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int currentStep = (int) animation.getAnimatedValue();
                mQQStepView.setCurrentStep(currentStep);
            }
        });
        mValueAnimator.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mValueAnimator != null) {
            mValueAnimator.cancel();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        // 延迟加载放这里
    }
}