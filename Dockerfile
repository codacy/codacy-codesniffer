FROM alpine:3.17.3

WORKDIR /opt/docker/app

ENV COMPOSER_HOME /opt/docker/app/.composer
ENV PATH ${COMPOSER_HOME}/vendor/bin:$PATH
ADD composer.* ${COMPOSER_HOME}/

RUN apk --no-cache add curl git php8 php8-openssl php8-phar php8-simplexml \
    php8-json php8-curl php8-iconv php8-zlib php8-simplexml php8-tokenizer \
    php8-xmlwriter php8-mbstring php8-xml php8-dom php8-xmlreader openjdk8-jre \
    openssh-client && \
    ln -s /etc/php8/php.ini /etc/php8/conf.d/php.ini && sed 's/.*short_open_tag.*=.*/short_open_tag=On/' /etc/php8/php.ini -i && \
    curl -sS https://getcomposer.org/installer | php8 && \
    mkdir -p /opt/docker/app/.composer && \
    ln -s /usr/bin/php8 /usr/bin/php && \
    php composer.phar global install && \
    ln -s ${COMPOSER_HOME}/vendor/bin/phpcs /usr/bin/phpcs && \
    php8 composer.phar global require dealerdirect/phpcodesniffer-composer-installer && \
    apk del curl git && rm -rf /tmp/* && rm -rf /var/cache/apk/*
