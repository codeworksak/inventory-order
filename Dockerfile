FROM eclipse-temurin:25-jre-alpine

WORKDIR /inventory-order

ARG JAR_FILES=target/*.jar
COPY ${JAR_FILES} ./inventory-order.jar

ENV MONGO_HOST=172.17.0.7
ENV MONGO_PORT=27017

ENV RABBIT_HOST=172.17.0.2
ENV RABBIT_PORT=5672
ENV RABBIT_Q="order-queue"
ENV RABBIT_EXCHANGE="fanout.ordering"
ENV RABBIT_ROUTE_KEY="ordering"
ENV RABBIT_USER=guest
ENV RABBIT_PASS=guest

EXPOSE 8084

ENTRYPOINT ["java", "-jar", "inventory-order.jar"]