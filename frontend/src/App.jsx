import { useEffect, useMemo, useState } from 'react'
import './App.css'
import {
  fetchBilateralTrend,
  fetchCountries,
  fetchHealth,
  fetchTopPartners,
  fetchTopProducts,
  fetchTradeMetadata,
} from './api/tradeApi'
import BilateralTrendChart from './components/BilateralTrendChart'
import CountrySelector from './components/CountrySelector'
import DashboardKpis from './components/DashboardKpis'
import HealthStatus from './components/HealthStatus'
import PartnersChart from './components/PartnersChart'
import ProductGroupsChart from './components/ProductGroupsChart'
import TradeFilters from './components/TradeFilters'

const THEME_STORAGE_KEY = 'country-trade-explorer-theme'

function App() {
  const [theme, setTheme] = useState(() => {
    const storedTheme = localStorage.getItem(THEME_STORAGE_KEY)
    return storedTheme || 'light'
  })

  const [health, setHealth] = useState(null)
  const [countries, setCountries] = useState([])
  const [selectedCountryCode, setSelectedCountryCode] = useState('')

  const [availableYears, setAvailableYears] = useState([])
  const [availableFlows, setAvailableFlows] = useState([])
  const [metadataLoading, setMetadataLoading] = useState(true)

  const [selectedYear, setSelectedYear] = useState(null)
  const [selectedFlow, setSelectedFlow] = useState('')

  const [partners, setPartners] = useState([])
  const [trendData, setTrendData] = useState([])
  const [products, setProducts] = useState([])

  const [partnersLoading, setPartnersLoading] = useState(false)
  const [trendLoading, setTrendLoading] = useState(false)
  const [productsLoading, setProductsLoading] = useState(false)

  useEffect(() => {
    document.documentElement.setAttribute('data-theme', theme)
    localStorage.setItem(THEME_STORAGE_KEY, theme)
  }, [theme])

  useEffect(() => {
    fetchHealth()
      .then((data) => setHealth(data))
      .catch(() =>
        setHealth({
          status: 'DOWN',
          service: 'backend unreachable',
        }),
      )
  }, [])

  useEffect(() => {
    fetchCountries()
      .then((data) => {
        setCountries(data)
        if (data.length > 0) {
          setSelectedCountryCode(data[0].code)
        }
      })
      .catch(() => setCountries([]))
  }, [])

  useEffect(() => {
    setMetadataLoading(true)

    fetchTradeMetadata()
      .then((data) => {
        setAvailableYears(data.years || [])
        setAvailableFlows(data.flows || [])

        if ((data.years || []).length > 0) {
          setSelectedYear((currentYear) => currentYear ?? data.years[data.years.length - 1])
        }

        if ((data.flows || []).length > 0) {
          setSelectedFlow((currentFlow) => currentFlow || data.flows[0])
        }
      })
      .catch(() => {
        setAvailableYears([])
        setAvailableFlows([])
      })
      .finally(() => setMetadataLoading(false))
  }, [])

  useEffect(() => {
    if (!selectedCountryCode || !selectedYear || !selectedFlow) return

    setPartnersLoading(true)
    fetchTopPartners(selectedCountryCode, selectedYear, selectedFlow)
      .then((data) => setPartners(data))
      .catch(() => setPartners([]))
      .finally(() => setPartnersLoading(false))
  }, [selectedCountryCode, selectedYear, selectedFlow])

  useEffect(() => {
    if (!selectedCountryCode || !selectedFlow) return

    setTrendLoading(true)
    fetchBilateralTrend(selectedCountryCode, selectedFlow)
      .then((data) => setTrendData(data))
      .catch(() => setTrendData([]))
      .finally(() => setTrendLoading(false))
  }, [selectedCountryCode, selectedFlow])

  useEffect(() => {
    if (!selectedCountryCode || !selectedYear || !selectedFlow) return

    setProductsLoading(true)
    fetchTopProducts(selectedCountryCode, selectedYear, selectedFlow)
      .then((data) => setProducts(data))
      .catch(() => setProducts([]))
      .finally(() => setProductsLoading(false))
  }, [selectedCountryCode, selectedYear, selectedFlow])

  const selectedCountry = useMemo(
    () => countries.find((country) => country.code === selectedCountryCode),
    [countries, selectedCountryCode],
  )

  function handleThemeToggle() {
    setTheme((currentTheme) => (currentTheme === 'light' ? 'dark' : 'light'))
  }

  return (
    <div className="app-shell">
      <section className="dashboard-hero">
        <div className="hero-card">
          <div className="hero-toolbar">
            <p className="hero-eyebrow">Country Trade Explorer</p>
            <button type="button" className="theme-toggle" onClick={handleThemeToggle}>
              {theme === 'light' ? 'Dark mode' : 'Light mode'}
            </button>
          </div>

          <h1 className="app-title">Trade dashboard for bilateral relationships and product flows</h1>
          <p className="hero-text">
            Explore partners, trade trends, and product groups with a clean portfolio-style dashboard
            built on Spring Boot, React, PostgreSQL, and UN Comtrade-oriented ingestion.
          </p>
        </div>

        <div className="hero-side-card">
          <span className="hero-badge">UN Comtrade-ready</span>
          <div>
            <h2 className="hero-side-title">What this dashboard highlights</h2>
            <p className="hero-side-text">
              A selected country, a filterable trade slice, summary KPIs, and chart panels that are
              ready for real imported data.
            </p>
          </div>
        </div>
      </section>

      <DashboardKpis
        selectedCountry={selectedCountry}
        selectedYear={selectedYear}
        selectedFlow={selectedFlow}
        partners={partners}
        products={products}
        trendData={trendData}
      />

      <section className="controls-grid">
        <HealthStatus health={health} />

        <TradeFilters
          years={availableYears}
          flows={availableFlows}
          year={selectedYear ?? ''}
          flow={selectedFlow}
          loading={metadataLoading}
          onYearChange={setSelectedYear}
          onFlowChange={setSelectedFlow}
        />
      </section>

      <section className="controls-grid">
        <CountrySelector
          countries={countries}
          selectedCountryCode={selectedCountryCode}
          selectedCountry={selectedCountry}
          onCountryChange={setSelectedCountryCode}
        />

        <section className="panel">
          <div className="panel-heading">
            <div>
              <h2>Dashboard Notes</h2>
              <p className="panel-subtitle">How to read the current view</p>
            </div>
            <span className="panel-chip">Guide</span>
          </div>

          <p className="helper-text">
            Use the country selector and the trade filters to switch the current dashboard slice.
          </p>
          <br />
          <p className="helper-text">
            The cards summarize the active view, while the charts below show partners, historical
            trend, and product composition.
          </p>
        </section>
      </section>

      <section className="chart-grid">
        <PartnersChart
          selectedCountry={selectedCountry}
          partners={partners}
          loading={partnersLoading}
        />

        <BilateralTrendChart
          selectedCountry={selectedCountry}
          trendData={trendData}
          loading={trendLoading}
        />

        <ProductGroupsChart
          selectedCountry={selectedCountry}
          products={products}
          loading={productsLoading}
        />
      </section>
    </div>
  )
}

export default App
