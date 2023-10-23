FROM php:8.3-rc-alpine

WORKDIR /opt/docker/app

ENV COMPOSER_HOME /opt/docker/app/.composer
ENV PATH ${COMPOSER_HOME}/vendor/bin:$PATH
ADD composer.* ${COMPOSER_HOME}/

RUN apk --no-cache add curl git php83 php83-openssl php83-phar php83-simplexml \
    php83-json php83-curl php83-iconv php83-zlib php83-simplexml php83-tokenizer \
    php83-xmlwriter php83-mbstring php83-xml php83-dom php83-xmlreader openjdk8-jre \
    openssh-client && \
    ln -s /etc/php83/php.ini /etc/php83/conf.d/php.ini && sed 's/.*short_open_tag.*=.*/short_open_tag=On/' /etc/php83/php.ini -i && \
    curl -sS https://getcomposer.org/installer | php83 && \
    mkdir -p /opt/docker/app/.composer && \
    ln -s /usr/bin/php83 /usr/bin/php && \
    php composer.phar global install && \
    ln -s ${COMPOSER_HOME}/vendor/bin/phpcs /usr/bin/phpcs && \
    php composer.phar global require dealerdirect/phpcodesniffer-composer-installer && \
    apk del curl git && rm -rf /tmp/* && rm -rf /var/cache/apk/*
