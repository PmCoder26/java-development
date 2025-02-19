FROM ubuntu:latest

WORKDIR /workspace

RUN apt update && apt install -y \
    openjdk-21-jdk \
    maven \
    git \
    curl \
    unzip \
    postgresql \
    postgresql-client

ENV JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
ENV PATH=$JAVA_HOME/bin:$PATH

USER root

CMD ["/bin/bash"]