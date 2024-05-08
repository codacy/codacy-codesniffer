FROM sbtscala/scala-sbt:eclipse-temurin-jammy-11.0.22_7_1.9.9_2.13.13 as builder
WORKDIR /app
COPY project project
COPY src src
COPY build.sbt build.sbt
RUN sbt stage

FROM php:8.2-alpine

WORKDIR /app

# Set environment variables
ENV COMPOSER_HOME /app/.composer
ENV COMPOSER_ALLOW_SUPERUSER 1 
ENV PATH ${COMPOSER_HOME}/vendor/bin:${PATH}

# Install necessary packages
RUN apk --no-cache add git openssh-client openjdk11-jre \
    php82-openssl php82-phar php82-simplexml php82-json php82-curl \
    php82-iconv php82-zlib php82-simplexml php82-tokenizer \
    php82-xmlwriter php82-mbstring php82-xml php82-dom php82-xmlreader

# Configure PHP settings
RUN ln -s /etc/php82/php.ini /etc/php82/conf.d/php.ini && \
    sed 's/.*short_open_tag.*=.*/short_open_tag=On/' /etc/php82/php.ini -i

# Install Composer
RUN curl -sS https://getcomposer.org/installer | php

# Install packages
COPY composer.* ${COMPOSER_HOME}
RUN php composer.phar global install

# Clean up temporary files and cache
RUN rm -rf /tmp/* && \
    adduser -u 2004 -D docker

# Copy codacy-codesniffer binary and docs
COPY --chown=docker:docker --from=builder /app/target/universal/stage/bin /app/scala/bin
COPY --chown=docker:docker --from=builder /app/target/universal/stage/lib /app/scala/lib
COPY --chown=docker:docker docs /docs

WORKDIR /src

USER docker
ENTRYPOINT ["/app/scala/bin/codacy-codesniffer"]