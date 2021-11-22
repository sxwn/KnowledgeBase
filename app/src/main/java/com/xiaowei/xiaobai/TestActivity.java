package com.xiaowei.xiaobai;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.xiaowei.xiaobai.constants.Constance;
import com.xiaowei.xiaobai.dao.User;

@Route(path = Constance.ACTIVITY_URL_TEST)
public class TestActivity extends Activity {

    @Autowired(name = "name")
    String mName;

    @Autowired(name = "age")
    int mAge;

    @Autowired(name = "user")
    User mUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        linearLayout.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
        setContentView(linearLayout);
        ARouter.getInstance().inject(this);
        Log.e("weip", "name:" + mName + "age:" +mAge + "user:" + mUser.getName() + mUser.getAge());
    }
}