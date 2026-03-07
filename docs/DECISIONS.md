# Decisions

## 2026-03-05

- Use Spring Boot for backend.
- Use React for frontend.
- Use Apache ECharts for charting.
- Use PostgreSQL for persistence.
- Use UN Comtrade as the main data source.
- Work in small, verifiable git steps.

## 2026-03-06

- Add a simple backend health endpoint at `GET /api/health` for initial backend/frontend integration testing.
- Use `echarts-for-react` as the React wrapper for Apache ECharts.
- Allow local frontend origins in backend CORS configuration for development.
- Use a mock trade trend chart before integrating real data sources.

- Add `/api/trade/products` as a separate endpoint for product-group rankings rather than overloading existing trade endpoints.
- Keep the frontend charts backend-driven, even for mock data, so the UI structure matches the future real-data architecture.

- Replace generic `Map<String, Object>` API responses with typed DTO classes to improve backend type safety and refactorability.
- Use Java records for API response DTOs because the current response shapes are simple, immutable, and read-oriented.

- Extract mock backend data from controllers into service classes to keep controllers focused on HTTP concerns.
- Keep current mock data in services as an intermediate step before replacing it with repository- or source-backed integration logic.

- Add backend unit tests at the service layer to verify mock data behavior without requiring full HTTP tests.
- Add a lightweight controller smoke test with MockMvc to verify the main API endpoints respond successfully and preserve expected JSON structure.

- Use Flyway for versioned PostgreSQL schema migrations so database structure evolves through tracked code changes.
- Introduce an initial relational schema with a `country` reference table and a `trade_observation` table as the first persistence foundation.
- Seed a small set of countries through a migration to support local development before repository-backed loading is implemented.

- Use PostgreSQL in Docker as the default local database setup instead of relying on a host-installed PostgreSQL instance.
- Introduce Spring Data JPA for the `country` table first, keeping the initial persistence step small and low-risk.
- Configure Hibernate with `ddl-auto=validate` so Flyway remains the source of truth for schema management.

- Use PostgreSQL in Docker as the only local database setup for this project.
- Use host port `5434` for local Docker PostgreSQL to avoid collisions with other database services.
- Use project-specific `CTE_DB_*` environment variables instead of generic `DB_*` names to avoid conflicts with other apps on the same machine.
- Introduce Spring Data JPA for `country` first, then for `trade_observation`, keeping persistence adoption incremental.
- Keep Flyway as the source of truth for schema management and use Hibernate with `ddl-auto=validate`.
- Seed additional reference countries in a separate migration before inserting trade observations so foreign-key-dependent trade seeds apply in a valid order.
- Switch only `/api/trade/partners` to database-backed reads first, while keeping bilateral trend and product-group endpoints mock-backed temporarily to reduce scope.
