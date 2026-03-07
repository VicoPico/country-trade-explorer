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

## 2026-03-07

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

- Switch `/api/trade/bilateral` to database-backed yearly aggregates after `/api/trade/partners` so persistence adoption stays incremental.
- Seed historical trade observations for 2021-2023 in a dedicated migration to support trend charts without introducing external ingestion yet.
- Keep `/api/trade/products` mock-backed temporarily while partner and bilateral queries are transitioned to repository-backed reads first.

- Switch `/api/trade/products` to database-backed product-group aggregates after partners and bilateral trends so all three core views share the same persistence-backed architecture.
- Limit top-partners and top-products responses to 5 rows at the service layer to preserve a stable MVP API shape and match the current UI scope.
- Keep product aggregation at the reporter/year/flow level for now, without partner filtering, to stay aligned with the current MVP UI scope.

- Add `year` and `flow` request parameters to trade endpoints before introducing more complex filtering so the API stays aligned with likely real-data query dimensions.
- Wire frontend year and flow selectors directly to backend-backed trade queries, even though current seed data is stronger for exports than imports.
- Allow empty results for unsupported filter combinations in the current seed dataset rather than adding UI-only fallback behavior.

- Seed a small set of IMPORT trade observations after introducing the flow filter so both flow directions return meaningful data in the MVP UI.
- Keep import seed coverage intentionally narrow and focused on the main demo countries (SWE, USA, CHN) rather than attempting broad reference completeness at this stage.
