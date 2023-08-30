FROM alpine:3.13

WORKDIR /opt/docker/app

ENV COMPOSER_HOME /opt/docker/app/.composer
ENV PATH ${COMPOSER_HOME}/vendor/bin:$PATH
ADD composer.* ${COMPOSER_HOME}/

RUN apk --no-cache add curl git php8.1 php8.1-openssl php8.1-phar php8.1-simplexml \
    php8.1-json php8.1-curl php8.1-iconv php8.1-zlib php8.1-simplexml php8.1-tokenizer \
    php8.1-xmlwriter php8.1-mbstring php8.1-xml php8.1-dom php8.1-xmlreader openjdk8-jre \
    openssh-client && \
    ln -s /etc/php8.1/php.ini /etc/php8.1/conf.d/php.ini && sed 's/.*short_open_tag.*=.*/short_open_tag=On/' /etc/php8.1/php.ini -i && \
    curl -sS https://getcomposer.org/installer | php8.1 && \
    mkdir -p /opt/docker/app/.composer && \
    ln -s /usr/bin/php8.1 /usr/bin/php && \
    php composer.phar global install && \
    ln -s ${COMPOSER_HOME}/vendor/bin/phpcs /usr/bin/phpcs && \
    php8.1 composer.phar global require dealerdirect/phpcodesniffer-composer-installer && \
    apk del curl git && rm -rf /tmp/* && rm -rf /var/cache/apk/*
