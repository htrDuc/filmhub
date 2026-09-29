# 01 — Keycloak

- [x] Thêm service `keycloak` vào `docker-compose.yml` (`start-dev`, map port **8180** tránh đụng 8080 của Spring)
- [x] Tạo realm `filmhub`, client `filmhub-app` (bật Direct Access Grants để test bằng curl), user `bob`
- [x] Export realm → `infra/keycloak/filmhub.realm.json`, mount + `--import-realm` để `make up` là có sẵn

## Done khi
- [x] curl token endpoint lấy được access token
- [x] Decode token, hiểu các field `iss`, `kid`, `exp`
