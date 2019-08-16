# 依赖镜像
FROM centos:7

# 创建者信息
MAINTAINER whoiszxl

# 工作目录
WORKDIR /usr
RUN mkdir /usr/local/java

# 将java包添加到容器
ADD jdk-8u141-linux-x64.tar.gz /usr/local/java

# 配置java环境变量
ENV JAVA_HOME /usr/local/java/jdk1.8.0_141
ENV JRE_HOME $JAVA_HOME/jre
ENV CLASSPATH $JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar:$JRE_HOME/lib:$CLASSPATH
ENV PATH $JAVA_HOME/bin:$PATH