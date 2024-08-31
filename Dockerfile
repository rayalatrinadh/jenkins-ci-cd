FROM openjdk:17
WORKDIR /appContainer
COPY ./target/jenkinsCiCd.jar /appContainer
EXPOSE 8086
CMD ["java", "-jar", "jenkinsCiCd.jar"]