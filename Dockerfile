FROM alpine:3.13

WORKDIR /opt/docker/app

RUN apk --no-cache add curl git php7 php7-openssl php7-phar \
    php7-json php7-curl php7-iconv php7-zlib php7-simplexml php7-tokenizer \
    php7-xmlwriter php7-mbstring php7-xml php7-dom php7-xmlreader openjdk8-jre
RUN ln -s /etc/php7/php.ini /etc/php7/conf.d/php.ini && sed 's/.*short_open_tag.*=.*/short_open_tag=On/' /etc/php7/php.ini -i

RUN apk --no-cache add composer

ENV COMPOSER_HOME /opt/docker/app/.composer

ENV PATH ${COMPOSER_HOME}/vendor/bin:$PATH
ADD composer.* ${COMPOSER_HOME}/

RUN composer global install
RUN ln -s ${COMPOSER_HOME}/vendor/bin/phpcs /usr/bin/phpcs

RUN composer global require dealerdirect/phpcodesniffer-composer-installer

RUN apk del curl git && rm -rf /tmp/* && rm -rf /var/cache/apk/*
