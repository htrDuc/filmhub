# 03 — API Gateway (tự viết)

Theo Notion "04 — Tech stack": P0–P10 dùng `services/api-gateway` tự viết, Kong chỉ từ P11. Không dùng Spring Cloud Gateway.

- [x] Tạo module `services/api-gateway` (Spring Boot MVC), khai vào `<modules>` của `services/pom.xml`
- [x] Chạy port **8000** (catalog giữ 8080)
- [x] Forward `/api/catalog/**` → `http://localhost:8080` bằng `RestClient`: giữ nguyên method, path, query, body, header (đặc biệt `Authorization`), trả nguyên status + body về client
- [x] URL của catalog đọc từ config/env var, không hardcode

## Done khi
- [x] `curl localhost:8000/api/catalog/ping` → 200 "pong"
- [x] `/api/catalog/me` qua gateway: không token → 401, có token → 200
- [x] Tắt catalog rồi gọi qua gateway → gateway trả lỗi gì? (ghi lại, P4 sẽ quay lại chuyện này)
  - Kết quả: **500**. `RestClient` ném `ResourceAccessException` (connection refused), không ai bắt → Spring trả 500
  - Đúng ra: **502 Bad Gateway** (upstream chết/từ chối kết nối), **504 Gateway Timeout** (upstream quá chậm)
