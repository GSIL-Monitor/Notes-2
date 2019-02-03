##java虚拟机参数VM arguments:

-verbose:gc
打印GC日志

-Xms20M
初始化堆

-Xmx20M
最大的堆大小

-Xmn10M
新生代

-XX:+PrintGCDetails
打印GC日志

-XX:SurvivorRatio=8
两个survivor：eden = 2：8

HotSpot 虚拟机提供了- Xnoclassgc 参数进行控制，还可以使用- verbose: class以及-XX:+ TraceClassLoading、-XX:+TraceClassUnLoading 查看类加载和卸载信息，其中-verbose: class 和- XX:+TraceClassLoading 可以在 Product 版的 虚拟机中使用，-XX:+ TraceClassUnLoading 参数需要 FastDebug 版的虚拟机支持。




##新生代
 HotSpot JVM把年轻代分为了三部分：1个Eden区和2个Survivor区（分别叫from和to）。默认比例为8：1。

一般情况下，新创建的对象都会被分配到Eden区(一些大对象特殊处理),这些对象经过第一次Minor GC后，如果仍然存活，将会被移到Survivor区。对象在Survivor区中每熬过一次Minor GC，年龄就会增加1岁，当它的年龄增加到一定程度时，就会被移动到年老代中。