# 05 — Correlation ID

- [ ] Gateway sinh `traceparent` (W3C) hoặc `X-Request-ID` nếu request chưa có
- [ ] Catalog: filter đọc header → đưa vào MDC → thêm vào log pattern

## Done khi
- [ ] Gửi 1 request, thấy cùng một ID trong log Kong và log catalog

## Research
`W3C Trace Context`
