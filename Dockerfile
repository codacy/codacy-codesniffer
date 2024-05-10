FROM sbtscala/scala-sbt:eclipse-temurin-jammy-11.0.22_7_1.9.9_2.13.13 as builder
WORKDIR /app
COPY build.sbt .
COPY docs /docs
COPY project project
COPY src src

RUN wget https://github.com/phpDocumentor/phpDocumentor/releases/download/v3.4.3/phpDocumentor.phar && \
    mv phpDocumentor.phar /usr/local/bin/phpdoc && \
    chmod +x /usr/local/bin/phpdoc && \
    sbt 'runMain codacy.codesniffer.docsgen.GeneratorMain'
RUN sbt stage

FROM php:8.2-alpine

WORKDIR /app

# Set environment variables
ENV COMPOSER_HOME /app/.composer
ENV COMPOSER_ALLOW_SUPERUSER 1 
ENV PATH ${COMPOSER_HOME}/vendor/bin:${PATH}

# Install necessary packages
RUN apk --no-cache add php82 openjdk11-jre-headless

# Configure PHP settings
RUN sed 's/.*short_open_tag.*=.*/short_open_tag=On/' /etc/php82/php.ini -i

# Install Composer and packages
RUN curl -sS https://getcomposer.org/installer | php
COPY composer.* ${COMPOSER_HOME}
RUN php composer.phar global install

# Cleanup and miscellaneous  
RUN rm -rf /tmp/* && \
    adduser -u 2004 -D docker

# Copy codacy-codesniffer and docs
COPY --chown=docker:docker --from=builder /app/target/universal/stage scala
COPY --chown=docker:docker --from=builder /docs /docs

WORKDIR /src

USER docker
ENTRYPOINT ["/app/scala/bin/codacy-codesniffer"]
