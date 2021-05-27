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
    套图优化：SVG图，矢量图替代位图。矢量图加载是更加消耗CPU的。 200dp * 200dp = 40000 一旦超过4wdp就比较消耗时间，