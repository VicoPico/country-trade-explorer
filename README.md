# Country Trade Explorer

A full-stack trade analytics project for exploring bilateral trade relationships between countries using a real UN Comtrade ingestion path.

## Overview

Country Trade Explorer allows a user to select a country and inspect:

- top trade partners
- bilateral yearly trade trends
- top traded product groups
- filterable import/export views
- metadata-driven year and flow selection

The project is designed as a portfolio-grade backend + frontend system:

- backend focuses on clean domain layering, ingestion separation, persistence, and query APIs
- frontend focuses on dashboard-style data presentation

## Tech Stack

### Backend

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Flyway
- Maven

### Frontend

- React
- Vite
- Apache ECharts

### Data source

- UN Comtrade API

## Architecture

- `backend/` → Spring Boot API, ingestion, persistence, query services
- `frontend/` → React dashboard
- `docs/` → technical decisions and project notes
- `READ_AGENT.md` → working instructions for AI-assisted iteration

## Current Backend Capabilities

### Query layer

- `/api/health`
- `/api/countries`
- `/api/trade/partners`
- `/api/trade/bilateral`
- `/api/trade/products`
- `/api/trade/metadata`

### Ingestion layer

- separate application service for imports
- UN Comtrade adapter
- import endpoint for narrow controlled slices

### Database

- Flyway migrations
- seeded countries
- seeded trade observations
- imported rows persisted into `trade_observation`

## Current Frontend Capabilities

- dashboard hero section
- KPI cards
- partner chart
- bilateral trend chart
- product group chart
- loading states
- empty states
- backend-driven filters
- dark mode toggle
- theme persistence via localStorage

## Local Development

### Start PostgreSQL

```bash
docker compose up -d
```

### Backend

```bash
cd backend

CTE_DB_HOST=localhost \
CTE_DB_PORT=5434 \
CTE_DB_NAME=trade_explorer \
CTE_DB_USER=postgres \
CTE_DB_PASSWORD=postgres \
mvn spring-boot:run
```

### Frontend

```bash
cd frontend
npm install
npm run dev
```

## Backend Tests

```bash
cd backend

CTE_DB_HOST=localhost \
CTE_DB_PORT=5434 \
CTE_DB_NAME=trade_explorer \
CTE_DB_USER=postgres \
CTE_DB_PASSWORD=postgres \
mvn clean test
```

## UN Comtrade Configuration

```bash
export UNCOMTRADE_ENABLED=true
export UNCOMTRADE_PREVIEW=true
export UNCOMTRADE_API_KEY="<primary key>"
export UNCOMTRADE_API_KEY_HEADER_NAME="Ocp-Apim-Subscription-Key"
export UNCOMTRADE_FINAL_DATA_URL="<working endpoint url>"
```

## Import Example

```bash
curl -X POST http://localhost:8080/api/dev/imports/trade \
  -H "Content-Type: application/json" \
  -d '{
    "reporter": "SWE",
    "year": 2022,
    "flow": "EXPORT"
  }'
```

## Project Status

Current state:

- fully working local stack
- database-backed core trade views
- real external source adapter connected
- portfolio-ready dashboard foundation

Next steps:

- screenshot-ready polish
- README visuals
- stronger import replacement strategy
- broader country mapping
- production-ready real data ingestion
