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
