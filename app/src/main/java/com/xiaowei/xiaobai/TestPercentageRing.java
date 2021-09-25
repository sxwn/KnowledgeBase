package com.xiaowei.xiaobai;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.xiaowei.xiaobai.view.PercentageRing;

public class TestPercentageRing extends AppCompatActivity {
    private PercentageRing mPercentageRing1;
    private PercentageRing mPercentageRing2;
    private PercentageRing mPercentageRing3;
    private PercentageRing mPercentageRing4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.percentage_ring);

        mPercentageRing1 = (PercentageRing) findViewById(R.id.Circle);
        //设置目标百分比为30
        mPercentageRing1.setTargetPercent(30);

        mPercentageRing2 = (PercentageRing) findViewById(R.id.Circle2);
        //设置目标百分比为50
        mPercentageRing2.setTargetPercent(50);

        mPercentageRing3 = (PercentageRing) findViewById(R.id.Circle3);
        //设置目标百分比为70
        mPercentageRing3.setTargetPercent(70);

        mPercentageRing4 = (PercentageRing) findViewById(R.id.Circle4);
        //设置目标百分比为100
        mPercentageRing4.setTargetPercent(100);
    }
}
