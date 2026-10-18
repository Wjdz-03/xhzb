# 基础镜像
FROM eclipse-temurin:17-jdk
# 设定时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 环境变量（敏感值请勿写入镜像，运行容器时通过 -e 或 --env-file 注入）
# docker run -e OSS_ACCESS_KEY_ID=xxx -e OSS_ACCESS_KEY_SECRET=xxx -e OPENAI_API_KEY=xxx ...


# 拷贝jar包
COPY xhzb-admin.jar /app.jar

# 暴露端口
EXPOSE 9000

# 入口
ENTRYPOINT ["java", "-jar", "/app.jar"]