# 02 — Catalog làm backend đích (dummy)

- [ ] `GET /api/catalog/ping`: public
- [ ] `GET /api/catalog/me`: bắt buộc token, trả `sub` + username từ JWT
- [ ] Thêm `spring-boot-starter-oauth2-resource-server`, config JWK set URI

## Gotcha
Token lấy qua `localhost:8180` có `iss` chứa localhost, còn trong Docker service gọi `keycloak:8080` → validate issuer lệch. Tự tìm cách xử lý (từ khóa: `KC_HOSTNAME`).

## Done khi
- [ ] Gọi thẳng service: không token → 401, có token → 200
