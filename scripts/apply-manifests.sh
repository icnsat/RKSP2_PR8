#!/bin/zsh
set -e

minikube start
kubectl delete --all pods,deployments,services -n pract8

kubectl apply -f k8s/namespace.yaml

# Запускать по одному
kubectl apply -f k8s/discovery-service.yaml
kubectl apply -f k8s/config-service.yaml
kubectl apply -f k8s/postgres-user.yaml
kubectl apply -f k8s/user-service.yaml
kubectl apply -f k8s/postgres-product.yaml
kubectl apply -f k8s/product-service.yaml
kubectl apply -f k8s/postgres-order.yaml
kubectl apply -f k8s/order-service.yaml
kubectl apply -f k8s/api-gateway.yaml

kubectl get all -n pract8
