FROM adoptopenjdk:16-jre-hotspot as builder
WORKDIR application
COPY target/final-exam-0.0.1-SNAPSHOT.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM adoptopenjdk:16-jre-hotspot
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", \
  "org.springframework.boot.loader.JarLauncher"]






#FROM adoptopenjdk:16-jre-hotspot
#RUN mkdir /opt/app
#ADD target/*.jar /opt/app/app.jar
#CMD ["java", "-jar", "/opt/app/app.jar"]