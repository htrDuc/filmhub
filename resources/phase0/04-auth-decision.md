# 04 — Quyết định auth (Issue chính của P0)

Làm sau 02 + 03, khi đã chạy được cả 2 đường (gọi thẳng service / qua gateway).

- [ ] Verify token ở gateway, ở service, hay cả hai? Ghi lý do thành ADR ngắn
- [ ] Service nhận danh tính bằng cách nào: forward nguyên token (token relay) hay gateway gửi header `X-User-Id`? Nếu header thì service tin nó dựa vào đâu?
- [ ] Implement theo quyết định

## Research
`BFF pattern` · `JWT vs opaque token` · `token relay`
