1、查看App内存限制
adb shell cat /system/build.prop
adb shell getprop dalvik.vm.heapsize

OutOfMemoryError 申请内存，没有足够
每一个进程  内存 大小限制
多进程

Binder

AM.getMenoryClass()； ---  <= （已经分配 + 准备分配的内存）

进程剩余

4M  1M  图片   OOM

jvm

adj算法 ---- app的状态  打分  越高

app 保活

-17  16

10 --- app 占用的内存

ulimit -a
Pthread_thread_max = 1024

    场景的内存泄漏：
    1、单例造成的内存泄漏
        传入Application的Context
    2、非静态内部类创建静态实例造成的内存泄漏
        解决方案：
        将这个非静态的内部类改成静态内部类，这样就不会持有外部类的引用（内部类里面有个外部类this的引用）
    3、Handler造成的内存泄漏
        解决方案：
        1、将Handler生命为静态的
        2、通过弱引用的方式持有Activity
        private static class MyHandler extends Handler {
            private WeakReference<Context> refrence;

            public MyHandler(Context context){
                refrence = new WeakReference<>(context);
            }

            @Override
            public void handleMessage(Message msg) {
                MainActivity activity = (MainActivity)refrence.get();
                if (activity != null) {
                    // ...
                }
            }
        }

        @Override
        public void onDestory() {
            super.onDestory();
            MyHandler.removeCallbackAndMessages(this);
        }
    4、线程造成的内存泄漏
            和Handler解决方案一致
    5、WebView造成的内存泄漏
        单独放一个进程。

    预防：
    1、较少对象的内存占用
    2、内存对象的复用
    3、避免对象的内存泄漏