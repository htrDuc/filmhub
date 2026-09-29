# 03 — Gateway

- [ ] Chọn Kong hay Nginx (gợi ý: **Kong DB-less**, có sẵn plugin JWT + correlation-id; Nginx OSS không verify JWT được)
- [ ] Thêm Kong vào compose, config bằng file `kong.yml`
- [ ] Route `/api/catalog/*` → catalog

## Done khi
- [ ] Gọi `/api/catalog/ping` qua gateway chạy được
