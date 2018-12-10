# JCallGraph
- Compare the call graph of the Java program under different versions.

### 环境依赖
- Java: 1.8.0_192
- Python: 3.6.7
- maven: 10.14.1

### 如何运行
- (1)克隆本代码仓库[JCallGraph](https://github.com/njucjc/JCallGraph)；
- (2)下载用于测试的程序[Weka](https://github.com/bnjmn/weka)；
- (3)利用[JCallGraph](https://github.com/njucjc/JCallGraph)下的[gen_waka_jar.py](https://github.com/njucjc/JCallGraph/blob/master/gen_waka_jar.py)脚本生成[Weka](https://github.com/bnjmn/weka)的JAR包；
- (4)利用[JCallGraph](https://github.com/njucjc/JCallGraph)下的[gen_files.py](https://github.com/njucjc/JCallGraph/blob/master/gen_files.py)脚本生成JAR包所对应的静态Java调用图；
- (5)利用[IntelliJ IDEA](https://www.jetbrains.com/idea/)导入[JCallGraph](https://github.com/njucjc/JCallGraph)工程，配置参数运行程序。

### 参考资料
- [Java调用图生成工具](https://github.com/gousiosg/java-callgraph)
