
FROM java:8

MAINTAINER cooper 178625120@qq.com

VOLUME /tmp 
# jar包更名
ADD eureka-0.0.1-SNAPSHOT.jar eureka.jar
# 运行Jar
RUN bash -c 'touch /eureka.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/eureka.jar"]