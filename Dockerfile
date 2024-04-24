FROM php:8.2-alpine

WORKDIR /opt/docker/app

ENV COMPOSER_HOME /opt/docker/app/.composer
ENV PATH ${COMPOSER_HOME}/vendor/bin:$PATH
ADD composer.* ${COMPOSER_HOME}/

# Install necessary packages
RUN apk --no-cache add git openssh-client openjdk11-jre \
  php82-openssl php82-phar php82-simplexml php82-json php82-curl \
  php82-iconv php82-zlib php82-simplexml php82-tokenizer \
  php82-xmlwriter php82-mbstring php82-xml php82-dom php82-xmlreader

# Configure PHP settings
RUN ln -s /etc/php82/php.ini /etc/php82/conf.d/php.ini && \
    sed 's/.*short_open_tag.*=.*/short_open_tag=On/' /etc/php82/php.ini -i

# Install Composer and required packages
RUN curl -sS https://getcomposer.org/installer | php && \
    mkdir -p /opt/docker/app/.composer && \
    php composer.phar global install && \
    ln -s ${COMPOSER_HOME}/vendor/bin/phpcs /usr/bin/phpcs && \
    php composer.phar global require dealerdirect/phpcodesniffer-composer-installer

# Clean up temporary files and cache
RUN rm -rf /tmp/* /var/cache/apk/*
