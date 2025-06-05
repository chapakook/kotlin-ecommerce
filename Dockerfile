# 📄 Dockerfile
FROM openjdk:17-jdk-slim
WORKDIR /app

# 로컬에서 빌드한 JAR만 복사
COPY build/libs/*.jar app.jar

# 포트 열기 (필요 시)
EXPOSE 8080

# 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
