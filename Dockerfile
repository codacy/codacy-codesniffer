FROM alpine:3.13

WORKDIR /opt/docker/app

ENV COMPOSER_HOME /opt/docker/app/.composer
ENV PATH ${COMPOSER_HOME}/vendor/bin:$PATH
ADD composer.* ${COMPOSER_HOME}/

RUN apk --no-cache add curl git php81 php81-openssl php81-phar php81-simplexml \
    php81-json php81-curl php81-iconv php81-zlib php81-simplexml php81-tokenizer \
    php81-xmlwriter php81-mbstring php81-xml php81-dom php81-xmlreader openjdk8-jre \
    openssh-client && \
    ln -s /etc/php81/php.ini /etc/php81/conf.d/php.ini && sed 's/.*short_open_tag.*=.*/short_open_tag=On/' /etc/php81/php.ini -i && \
    curl -sS https://getcomposer.org/installer | php81 && \
    mkdir -p /opt/docker/app/.composer && \
    ln -s /usr/bin/php81 /usr/bin/php && \
    php composer.phar global install && \
    ln -s ${COMPOSER_HOME}/vendor/bin/phpcs /usr/bin/phpcs && \
    php81 composer.phar global require dealerdirect/phpcodesniffer-composer-installer && \
    apk del curl git && rm -rf /tmp/* && rm -rf /var/cache/apk/*
