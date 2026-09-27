-- Mỗi service một user + một DB riêng. Chỉ chạy khi volume còn rỗng.
CREATE ROLE catalog LOGIN PASSWORD 'catalog';
CREATE DATABASE catalog OWNER catalog;
REVOKE CONNECT ON DATABASE catalog FROM PUBLIC;
