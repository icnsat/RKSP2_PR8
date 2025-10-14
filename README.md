# Порты
- config-service: 8888
- discovery-service: 8761
- user-service: 9001
- product-service: 9002 
- order-service: 9003
- api-gateway: 8080

# Запуск
```zsh
./scripts/build-jar.sh
./scripts/build-containers.sh
./scripts/apply-manifests.sh # Запускать вручную по одному
./scripts/port-forward.sh
```
