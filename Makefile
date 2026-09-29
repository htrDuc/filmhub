MVN := ./mvnw

.PHONY: help build test verify clean run up down reset logs

help: ## Liệt kê các lệnh
	@grep -E '^[a-z-]+:.*## ' $(MAKEFILE_LIST) | awk -F':.*## ' '{printf "  %-8s %s\n", $$1, $$2}'

build: ## Đóng gói tất cả module, bỏ qua test
	$(MVN) -T 1C package -DskipTests

test: ## Chạy test
	$(MVN) -T 1C test

verify: ## Build + test đầy đủ (giống CI)
	$(MVN) -T 1C verify

clean: ## Xoá target/
	$(MVN) clean

run: ## Chạy một service: make run SVC=catalog
	@test -n "$(SVC)" || (echo "Thiếu SVC, ví dụ: make run SVC=catalog" && exit 1)
	$(MVN) -pl services/$(SVC) spring-boot:run

up: ## Bật hạ tầng local
	docker compose up -d --wait

down: ## Tắt hạ tầng, giữ dữ liệu
	docker compose down

reset: ## Tắt hạ tầng và xoá volume (chạy lại script init)
	docker compose down -v

logs: ## Xem log hạ tầng
	docker compose logs -f
