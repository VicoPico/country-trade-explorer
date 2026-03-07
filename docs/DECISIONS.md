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
