build:
	docker compose up -d

psql:
	docker exec -it postgres-ecommerce-container psql -U postgres -d db_ecommerce

.PHONY: postgres build psql