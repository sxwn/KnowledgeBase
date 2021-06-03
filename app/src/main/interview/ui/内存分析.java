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

OOM 怎么解决？
    解决方案？
    1、LeakCanary的使用（square公司出台的）  百度自行依赖，就一句话,debug模式下检测泄漏的
    2、场景的内存分析工具：
        2.1 Memory Analyzer Tools
        2.2 Menory Profiler
        2.3 LeakCanary  sdcard/download/文件,直接双击打开

java：内存不用管理，为啥还会导致OOM?  GC？
    gcroot 可达性分析
    可回收对象的判定
    gcroot被直接或紧接引用，不会被 GC 回收。

    堆 和 栈
    A a = new A();  // a保存在栈里面的，a其实就是个引用， new A()这个对象放在堆里面的。

    a:gcroot    虚拟机栈 VM Stack

    一般能作为gcroot 的是方法区（存放静态变量、常量）、虚拟机栈、本地方法栈（jni）

    堆里面的内存被上面三个gcroot引用着，GC就不会回收堆里面的内存。

    内存分析：
    Shallowsize
    浅堆 ：本身占用的内存大小
    RetainedSize 深堆：内存之和

    (RetainedSize - Shallowsize)：就是需要回收的内存大小。

    ContentProvider.onCreate();
    Apllication.onCreate();
    打包:merge 资源打包

    手写leakcanary:
    Activity Fragment ， 由于这两个含有生命周期的属性
    onDestroy GC来了回收，如果回收不了就说明内存泄漏了
    refwatcher.watch(activity):触发gc回收。

    比如：门卫登记
        观察列表：A、B、C、D。。。
        问题：A、B、C、D  --->怀疑列表：A、B、C、D
        报警---可达性分析

    怎么知道变量或者是对象是否被GC回收了？  ---》弱引用： 引用队列

    弱引用：GC一旦扫到就回收。
    软引用：GC一旦扫到不一定回收，仅仅是内存不足的时候才会回收。

    泄漏了引用队列为空，反之亦然。
    引用队列不为空，移除观察列表。
            
    持久活好

    LMK：保活

    流畅不卡
    内存抖动：内存频繁地分配和回收。它伴随着频繁的GC。
    卡顿：
    profile工具 ：  cpu memory network检测

    default heap -- Arrange by class -- 排序
    FinalizerReference 大量的对象
    Object
        hashcode()
        finlizer()
    demo:
        onDraw里不能new
        path每次设置之前reset
        path.reset();
        path.setColor();
        path.moveTo();
        path.lineTo();
        cavas.drawPath(path)

    Color.parseColor()里面:
    subString:会创建新的String对象
    预防数组下标越界  colors[currentColor++ % 8]

    profile依赖于DDMServer:伴随一些对象的创建

    内存抖动：
    卡顿：STW
    程序崩溃oom：和具体的垃圾回收算法有关系
            标记清除算法
            CMS垃圾回收器老年代标记 --清除算法：内存碎片

    垃圾收集器：CMS
    G1

    垃圾收集机制的实现

注意事项：
1、尽量避免在循环和频繁调用的方法中创建对象
2、合理考虑使用对象池 (复用)
    Handler  new Message/obtainMessage

    内存泄漏:
    GC回收
    可达性分析法 GC Roots
        GCRoots Set

    强引用：
    软引用：
    弱引用：
    概念搞清楚，都是new出来的。 不要说new出来的就是强引用。

    引用计数：

    android中软引用和弱引用表现一致，java中则有区别。
    downmap java:内存快照

    mat工具帮助我们排除强、软、弱、虚引用。