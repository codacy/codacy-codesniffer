FROM php:8.2-alpine

WORKDIR /opt/docker/app

ENV COMPOSER_HOME /opt/docker/app/.composer
ENV PATH ${COMPOSER_HOME}/vendor/bin:$PATH
ADD composer.* ${COMPOSER_HOME}/

RUN apk --no-cache add curl git php82 php82-openssl php82-phar php82-simplexml \
    php82-json php82-curl php82-iconv php82-zlib php82-simplexml php82-tokenizer \
    php82-xmlwriter php82-mbstring php82-xml php82-dom php82-xmlreader openjdk8-jre \
    openssh-client && \
    ln -s /etc/php82/php.ini /etc/php82/conf.d/php.ini && sed 's/.*short_open_tag.*=.*/short_open_tag=On/' /etc/php82/php.ini -i && \
    curl -sS https://getcomposer.org/installer | php82 && \
    mkdir -p /opt/docker/app/.composer && \
    ln -s /usr/bin/php82 /usr/bin/php && \
    php composer.phar global install && \
    ln -s ${COMPOSER_HOME}/vendor/bin/phpcs /usr/bin/phpcs && \
    php composer.phar global require dealerdirect/phpcodesniffer-composer-installer && \
    apk del curl git && rm -rf /tmp/* && rm -rf /var/cache/apk/*
