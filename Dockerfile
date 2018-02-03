FROM java:8
EXPOSE 8080

COPY build/libs/accountservice.jar /opt

ENV JAVA_OPTS="-XMS256m -XX:PermSize=1024m -XX:MaxPermSize=512m"

ENTRYPOINT ["java", "-jar", "/opt/accountservice.jar"]