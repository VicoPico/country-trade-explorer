const apiBaseUrl = import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080'

async function fetchJson(path) {
  const response = await fetch(`${apiBaseUrl}${path}`)

  if (!response.ok) {
    throw new Error(`Request failed: ${response.status} ${response.statusText}`)
  }

  return response.json()
}

export function fetchHealth() {
  return fetchJson('/api/health')
}

export function fetchCountries() {
  return fetchJson('/api/countries')
}

export function fetchTradeMetadata() {
  return fetchJson('/api/trade/metadata')
}

export function fetchTopPartners(reporter, year, flow) {
  const params = new URLSearchParams({
    reporter,
    year: String(year),
    flow,
  })

  return fetchJson(`/api/trade/partners?${params.toString()}`)
}

export function fetchBilateralTrend(reporter, flow) {
  const params = new URLSearchParams({
    reporter,
    flow,
  })

  return fetchJson(`/api/trade/bilateral?${params.toString()}`)
}

export function fetchTopProducts(reporter, year, flow) {
  const params = new URLSearchParams({
    reporter,
    year: String(year),
    flow,
  })

  return fetchJson(`/api/trade/products?${params.toString()}`)
}
