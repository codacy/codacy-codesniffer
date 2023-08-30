FROM php:8.1-alpine3.17

WORKDIR /opt/docker/app

ENV COMPOSER_HOME /opt/docker/app/.composer
ENV PATH ${COMPOSER_HOME}/vendor/bin:$PATH
ENV COMPOSER_ALLOW_SUPERUSER=1
ADD composer.* ${COMPOSER_HOME}/

RUN apk --no-cache --update add \
    curl \
    git \
    openjdk8-jre \
    php81-common \
    php81-curl \
    php81-dom \
    php81-iconv \
    php81-mbstring \
    php81-openssl \
    php81-phar \
    php81-xml \
    openssh-client && \
    ln -s /etc/php81/php.ini /etc/php81/conf.d/php.ini && sed 's/.*short_open_tag.*=.*/short_open_tag=On/' /etc/php81/php.ini -i && \
    curl -sS https://getcomposer.org/installer | php81 && \
    mkdir -p /opt/docker/app/.composer && \
    php composer.phar global install && \
    ln -s ${COMPOSER_HOME}/vendor/bin/phpcs /usr/bin/phpcs && \
    php8.1 composer.phar global require dealerdirect/phpcodesniffer-composer-installer && \
    apk del curl git && rm -rf /tmp/* && rm -rf /var/cache/apk/*