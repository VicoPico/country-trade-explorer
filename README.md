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

## Current implemented step

- backend health endpoint at `/api/health`
- frontend connectivity test to backend API
- CORS enabled for local frontend development
- `/api/countries` reads country data from PostgreSQL via Spring Data JPA
- frontend country dropdown wired to backend country data
- `/api/trade/partners` reads the top 5 partner totals from seeded PostgreSQL trade observations
- frontend top trading partners chart tied to selected country
- `/api/trade/bilateral` reads yearly trade aggregates from seeded PostgreSQL trade observations
- frontend bilateral trade trend chart fetched from backend
- `/api/trade/products` reads the top 5 product-group aggregates from seeded PostgreSQL trade observations
- frontend top product groups chart tied to selected country
- frontend API calls extracted into a dedicated client module
- frontend UI split into smaller chart and selector components
- backend API responses migrated from generic maps to typed DTO classes
- backend mock data extracted from controllers into service classes
- controllers now focus on HTTP handling and delegate business/data logic to services
- backend service-layer unit tests added
- backend controller smoke tests added
- Flyway added for versioned PostgreSQL schema migrations
- initial schema migration creates `country` and `trade_observation` tables
- base country seed migration added for local development
- additional country seed migration added to support partner foreign keys
- trade observation seed migration added for local development
- trade observation historical seed migration added for trend queries
- Spring Data JPA introduced for `country` and `trade_observation`
- local database setup uses Docker PostgreSQL on host port `5434`
- project-specific `CTE_DB_*` environment variables avoid conflicts with other apps
