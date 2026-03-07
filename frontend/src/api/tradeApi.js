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

export function fetchTopPartners(reporter) {
  return fetchJson(`/api/trade/partners?reporter=${encodeURIComponent(reporter)}`)
}

export function fetchBilateralTrend(reporter) {
  return fetchJson(`/api/trade/bilateral?reporter=${encodeURIComponent(reporter)}`)
}

export function fetchTopProducts(reporter) {
  return fetchJson(`/api/trade/products?reporter=${encodeURIComponent(reporter)}`)
}
