package com.xiaowei.xiaobai.timetask;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;

/**
 * 定时按需执行任务
 * 1、WakeLock + Timer
 *      dozy mode(瞌睡模式) WakeLock让cpu保持唤醒   非常耗电  放弃
 * 2、AlarmManager
 *      是Andorid系统封装的用于管理RTC模块（Real Time Clock）：独立的硬件时钟，可以在cpu休眠的时候执行，在预设的时间到达时，
 *      通过中断唤醒cpu这意味着如果我们用AlarmManager来定时执行任务，cpu可以正常的休眠，只有在需要运行的任务时醒来一段很短的时间。
 * 3、JobScheduler
 *      最优方案
 */
public class TimeTask {

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void startAlarmTimer(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,intent,0);

        // 1个小时从服务器拉取最新的数据
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,100,60 * 60 * 1000,pendingIntent);
        alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,10000,pendingIntent);
    }
}
