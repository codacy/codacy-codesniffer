FROM php:8.2-alpine

WORKDIR /opt/docker/app

# Set environment variables
ENV COMPOSER_HOME /opt/docker/app/.composer
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
COPY --chown=docker:docker target/docker/stage/opt /opt
COPY --chown=docker:docker docs /docs

WORKDIR /src

USER docker
ENTRYPOINT ["/opt/docker/bin/codacy-codesniffer"]