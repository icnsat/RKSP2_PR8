#!/bin/zsh
set -e

gradle :user-service:bootJar
gradle :product-service:bootJar
gradle :order-service:bootJar
gradle :discovery-service:bootJar
gradle :config-service:bootJar
gradle :api-gateway:bootJar





