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
- Database: PostgreSQL
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
mvn spring-boot:run
```

### Frontend

```bash
cd frontend
npm run dev
```

## Current implemented step

- backend health endpoint at `/api/health`
- frontend connectivity test to backend API
- CORS enabled for local frontend development
- mock backend countries endpoint at `/api/countries`
- frontend country dropdown wired to backend country data
- mock backend top trading partners endpoint at `/api/trade/partners`
- frontend top trading partners chart tied to selected country
- mock backend bilateral trade trend endpoint at `/api/trade/bilateral`
- frontend bilateral trade trend chart fetched from backend
- mock backend top product groups endpoint at `/api/trade/products`
- frontend top product groups chart tied to selected country
