package com.xiaowei.xiaobai.memory;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class WeakReferenceTest {

    public static void main(String[] args) {
        ReferenceQueue referenceQueue = new ReferenceQueue();

        //强引用
        Object object = new Object();

        //创建object的弱引用对象，并和一个ReferenceQueue关联
        WeakReference weakReference = new WeakReference(object, referenceQueue);

        //把obj置空 让它没有强引用
        object = null;

        //gc，让可以回收的对象回收
        Runtime.getRuntime().gc();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Reference findRef = null;

        do {
            findRef = referenceQueue.poll();

            // 如果能找到上面的weakReference ==>说明它盛放的obj被gc回收了
            System.out.println("findRef = " + findRef + ", 是否等于上面的weakReference = " + (findRef == weakReference));
        } while (findRef != null);// 把所有放到 referenceQuene的引用容器找出来
    }
}
