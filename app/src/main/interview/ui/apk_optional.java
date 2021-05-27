apk瘦身: 代码质量

1、apk打包流程 -->重要
    apt 注解处理器
    javac  .class
    dex    .dex
    apkbuilder apk包
    签名
    zip压缩  4字节对齐

    resources.arsc: 资源映射表
2、apk的压缩包变小，瘦身
    2.1 文件内容变小
    2.2 更换压缩工具

    图片:
    套图优化：SVG图，矢量图替代位图。矢量图加载是更加消耗CPU的。 200dp * 200dp = 40000 一旦超过4wdp就比较消耗时间。

    svg2vector:批量转换svg到vector图。  javac --

    如果需要更改矢量图的颜色，如何搞？
    一般默认就是黑色，如果要修改，就得渲染，通过颜色着色器来进行渲染进行加载。
    如 ：android:tint="@color/red"

    颜色选择器:
    res/color/
    tint_selector:
    <selector>
        <item ...
        <item ...
    </selector>
        如 ：android:tint="@color/tint_selector"

    矢量图向后兼容,指定svg打包目录：
    android{
        defaultConfig{
            generatedDesities = ['xhdpi','xxhdpi']
        }
    }

    支持库
        android{
        defaultConfig{
        generatedDesities = ['xhdpi','xxhdpi']
            //支持库
            vectorDrawables.useSupportLibrary = true
        }
        }

        dependencies {
            // 支持库版本需要是23.2或者更高版本
            compile:'com.android.support:appcompoat-v7:23.2.0'
        }

        //使用
        app:srcCompoat:"..."

     启动界面：
    图：
    webp格式  --android
    png  透明度 --->jpg