# Country Trade Explorer

A web app for exploring bilateral trade relationships between countries using UN Comtrade as the main data source.

## Goal

The user selects one country and can view:

- top trading partners
- bilateral import/export trends
- top traded product groups over time

## Tech stack

- Backend: Spring Boot
- Frontend: React + Vite
- Charts: Apache ECharts
- Database: PostgreSQL (Docker-only for local development)
- Database migrations: Flyway
- Main source: UN Comtrade

## Monorepo structure

- `backend/` Spring Boot API
- `frontend/` React app
- `docs/` project notes and decisions
- `READ_AGENT.md` persistent project instructions/context for AI-assisted work

## Planned MVP

- country selector
- top trading partners view
- bilateral trade trends
- top traded product groups
- normalized trade data model
- cached local storage of source data

## Local development

### Infra

```bash
docker compose up -d
```

### Backend

```bash
cd backend
CTE_DB_HOST=localhost CTE_DB_PORT=5434 CTE_DB_NAME=trade_explorer CTE_DB_USER=postgres CTE_DB_PASSWORD=postgres mvn spring-boot:run
```

### Frontend

```bash
cd frontend
npm run dev
```

## Testing

### Backend tests

```bash
cd backend
CTE_DB_HOST=localhost CTE_DB_PORT=5434 CTE_DB_NAME=trade_explorer CTE_DB_USER=postgres CTE_DB_PASSWORD=postgres mvn clean test
```

## Dev import endpoint

```bash
curl -X POST http://localhost:8080/api/dev/imports/trade \
  -H "Content-Type: application/json" \
  -d '{
    "reporter": "SWE",
    "year": 2024,
    "flow": "EXPORT"
  }'
```

## Current implemented step

- backend health endpoint at `/api/health`
- frontend connectivity test to backend API
- CORS enabled for local frontend development
- `/api/countries` reads country data from PostgreSQL via Spring Data JPA
- `/api/trade/partners` reads the top 5 partner totals from seeded PostgreSQL trade observations
- `/api/trade/bilateral` reads yearly trade aggregates from seeded PostgreSQL trade observations
- `/api/trade/products` reads the top 5 product-group aggregates from seeded PostgreSQL trade observations
- backend trade metadata endpoint provides available years and flows
- frontend year and flow filter options are loaded from backend metadata
- import seed data added so both EXPORT and IMPORT flows return meaningful chart data
- frontend charts show basic loading and empty-state messaging
- frontend shared CSS structure replaces most inline styling
- ingestion/application layer introduced separately from the API query layer
- trade import source abstraction added for external-source adapters
- dev-only mock import endpoint added to persist source records into `trade_observation`
- Spring Data JPA introduced for `country` and `trade_observation`
- Flyway manages PostgreSQL schema and seed migrations
- local database setup uses Docker PostgreSQL on host port `5434`
- project-specific `CTE_DB_*` environment variables avoid conflicts with other apps
