package com.xiaowei.xiaobai;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.xiaowei.xiaobai.constants.Constance;
import com.xiaowei.xiaobai.dao.User;
import com.xiaowei.xiaobai.view.QQStepView;

@Route(path = Constance.ACTIVITY_URL_MAIN)
public class MainActivity extends AppCompatActivity {
    private QQStepView mQQStepView;
    private ValueAnimator mValueAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ARouter.getInstance().inject(this);
        mQQStepView = findViewById(R.id.step_view);
        mQQStepView.setStepMax(4000);

        // 属性动画
        mValueAnimator = ObjectAnimator.ofInt(0, 3000);
        mValueAnimator.setDuration(1000);
        mValueAnimator.setInterpolator(new DecelerateInterpolator());
        mValueAnimator.addUpdateListener(animation -> {
            int currentStep = (int) animation.getAnimatedValue();
            mQQStepView.setCurrentStep(currentStep);
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
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void go(View view) {
        ARouter.getInstance().build(Constance.ACTIVITY_URL_TEST)
            .withString("name","zhangsan")
            .withInt("age",3)
            .withParcelable("user", new User(23,"lisi"))
            .navigation();
    }
}