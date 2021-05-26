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