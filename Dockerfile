FROM sbtscala/scala-sbt:eclipse-temurin-alpine-25.0.3_9_1.12.13_3.8.4 as doc-generator

WORKDIR /app

RUN wget https://github.com/phpDocumentor/phpDocumentor/releases/download/v3.10.0/phpDocumentor.phar && \
    mv phpDocumentor.phar /usr/local/bin/phpdoc && \
    chmod +x /usr/local/bin/phpdoc

COPY build.sbt .
COPY docs /docs
COPY project project
COPY doc-generator doc-generator

RUN sbt 'doc-generator/runMain codacy.codesniffer.docsgen.GeneratorMain'

FROM sbtscala/scala-sbt:graalvm-ce-22.3.3-b1-java17_1.12.11_3.8.4 AS builder

WORKDIR /app

COPY build.sbt .
COPY project project
COPY src src

RUN --mount=type=cache,target=/root/.cache/coursier \
    sbt nativeImage

FROM php:8.5-alpine

WORKDIR /app

# Set environment variables
ENV COMPOSER_HOME /app/.composer
ENV COMPOSER_ALLOW_SUPERUSER 1 
ENV PATH ${COMPOSER_HOME}/vendor/bin:${PATH}

# Install necessary packages
RUN apk --no-cache add php85

# Configure PHP settings
RUN sed 's/.*short_open_tag.*=.*/short_open_tag=On/' /etc/php85/php.ini -i

# Install Composer and packages
RUN curl -sS https://getcomposer.org/installer | php
COPY composer.* ${COMPOSER_HOME}
RUN php composer.phar global install

# Cleanup and miscellaneous
RUN rm -rf /tmp/* && \
    adduser -u 2004 -D docker

# Copy codacy-codesniffer and docs
COPY --chown=docker:docker --from=builder /app/target/native-image/codacy-codesniffer bin/codacy-codesniffer
COPY --chown=docker:docker --from=doc-generator /docs /docs

WORKDIR /src

USER docker
ENTRYPOINT ["/app/bin/codacy-codesniffer"]
