FROM maven:3.9-amazoncorretto-8 as build

WORKDIR /tmp/build

COPY . .

RUN --mount=type=cache,target=/root/.m2/repository mvn package

FROM tomcat:8.5.78-jdk8-corretto as package

# HTTP
EXPOSE 8080

# Java debugging
EXPOSE 8000

ENV JPDA_TRANSPORT="dt_socket"
ENV JPDA_ADDRESS="localhost:8000"
ENV JPDA_SUSPEND="n"
ENV JPDA_OPTS="-agentlib:jdwp=transport=$JPDA_TRANSPORT,address=$JPDA_ADDRESS,server=y,suspend=$JPDA_SUSPEND"
ENV CATALINA_OPTS="$JPDA_OPTS $CATALINA_OPTS"

WORKDIR /usr/local/tomcat

RUN rm -rf ./webapps/*

COPY --from=build /tmp/build/target/server*.war ./webapps/ROOT.war

CMD ["catalina.sh", "run"]