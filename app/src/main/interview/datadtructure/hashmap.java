HashMap数据结构:
数组 + 链表
为什么要这么设计？
<key,value>
数据优势 : 查询、修改

不够：扩容
插入节点，删除节点;时间
hashmap:
put函数:
对象: hashcode int
Key --->HashCode() :int -->hashcode % table.length (hashcode & (table.length -1)) : index(0~table.length)

面试题:key适合使用什么类来做?  String 和Integer
key:index ->存节点

index：hash冲突 ； 链表来解决 

key的价值:
快速的定位元素的位置
hashcode: hash冲突，解决
链表法
index一样的元素：链表里面  轮询

SparseArray:解决hash冲突的方法，折半插入

key：是否一样？ 效率
key: hashcode()

key->int hashCode
hashcode 不一样  -> key必然不一样
integer/String :equals 效率高


阿里面试题:
HashMap初始化
put的时候,HashMap初始化，非构造函数，懒加载的时候初始化，节省内存。

懒加载：思想，用的时候才进行加载。

Hashmap扩容的原理：
2的指数幂，输入17会是多少容量？  字节

Hashmap:每一个节点只有一个元素，效率最高，轮询链表的动作，效率是最低的。
    链表越短越好。

数组的默认长度：16   32
key1 hash1:17  1   17
key2 hash2:1   1   1
空间浪费？

填充因子:  阈值。
0.75 * 16 = 12
12个 阈值，扩容。
扩容：提升效率。
0.6~0.75是最科学的。 所以选择0.75。
new HashMap(17)  32。
17  -> 比17大的2的次幂。 2^n
why?

效率？
2^n-1
        15: 1111
        31: 11111
        下标是均匀的。解决冲突，效率更高的。
        33
        32:  10000
        h & (length -1)
        h & 10000   ->  10000    or   0
        &:两者都是1才是1，其它是0
        2个链表的map
        我们的要求是每个元素都是链表。
求模运算
h & (length - 1)

位运算：基础水平，开发，源码
难：科班，计算机原理

classLoader:
new HashMap():内存  -》 ClassLoader
父类委托

dex[] framwork中:
dexdiff:  dex -->服务器, hook技术，反射和动态代理

5、传统hashmap的缺点：（为什么引入红黑树?）   -->网易
缺点：
5.1：多线程，持锁，线程不安全
        get()：链表死循环问题（java 1.7存在的问题）
5.2：hashmap长度过长
    hash算法不确定算法
    &运算：多对一,导致链表会更长，没办法控制链表的长度
5.3：解决效率？
        链表长度超过8个会引入红黑树
    java 1.8：Hash冲突不再使用链表来保存相同index的节点，相应的采用红黑树（高性能的平衡树）来保存冲突节点。
    o(n)
    2^n
    o(logn)


HashMap和HashTable区别:  CVTE
源码基本一样
HashTable:线程安全，Lock，锁,volate,网络，并发,但是效率太低

如何优化呢？
给每个链表上锁
ConcurrentHashMap:
key--->index --->table[index]
synchronized:锁链表对象节点(node),从锁方法到锁对象

