package com.xiaowei.xiaobai;

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
}
