#!/bin/zsh
set -e

minikube start
eval $(minikube docker-env)

kubectl delete --all pods,deployments,services -n pract8 || true

docker build -t config-service:latest ./config-service
docker build -t discovery-service:latest ./discovery-service
docker build -t user-service:latest ./user-service
docker build -t product-service:latest ./product-service
docker build -t order-service:latest ./order-service
docker build -t api-gateway:latest ./api-gateway

docker image prune -f
docker images
