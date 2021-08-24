package com.xiaowei.xiaobai.timetask;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: 判断网络,如果有网络就获取数据
        // TODO: 我们需要1小时获取一次，为了节省用户的流量
    }
}
