FROM openjdk:8-jre-alpine

WORKDIR /opt/docker/app

RUN apk --no-cache add curl git 
RUN apk --no-cache add php php-openssl php-phar php-json php-curl php-iconv php-zlib php-simplexml php-tokenizer php-xmlwriter php-mbstring php-xml php-dom 
RUN ln -s /etc/php7/php.ini /etc/php7/conf.d/php.ini && sed 's/.*short_open_tag.*=.*/short_open_tag=On/' /etc/php7/php.ini -i

RUN curl -sS https://getcomposer.org/installer | php 
RUN mv composer.phar /usr/bin/composer

ENV COMPOSER_HOME /opt/docker/app/.composer

ENV PATH ${COMPOSER_HOME}/vendor/bin:$PATH
ADD composer.json ${COMPOSER_HOME}/composer.json

RUN composer global install
RUN ln -s ${COMPOSER_HOME}/vendor/bin/phpcs /usr/bin/phpcs

RUN composer global require dealerdirect/phpcodesniffer-composer-installer

RUN apk del curl git && rm -rf /tmp/* && rm -rf /var/cache/apk/*


