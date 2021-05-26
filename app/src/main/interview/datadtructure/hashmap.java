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
