import { useEffect, useMemo, useState } from 'react'
import './App.css'
import {
  fetchBilateralTrend,
  fetchCountries,
  fetchHealth,
  fetchTopPartners,
  fetchTopProducts,
} from './api/tradeApi'
import BilateralTrendChart from './components/BilateralTrendChart'
import CountrySelector from './components/CountrySelector'
import HealthStatus from './components/HealthStatus'
import PartnersChart from './components/PartnersChart'
import ProductGroupsChart from './components/ProductGroupsChart'
import TradeFilters from './components/TradeFilters'

function App() {
  const [health, setHealth] = useState(null)
  const [countries, setCountries] = useState([])
  const [selectedCountryCode, setSelectedCountryCode] = useState('')
  const [selectedYear, setSelectedYear] = useState(2024)
  const [selectedFlow, setSelectedFlow] = useState('EXPORT')

  const [partners, setPartners] = useState([])
  const [trendData, setTrendData] = useState([])
  const [products, setProducts] = useState([])

  const [partnersLoading, setPartnersLoading] = useState(false)
  const [trendLoading, setTrendLoading] = useState(false)
  const [productsLoading, setProductsLoading] = useState(false)

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
    if (!selectedCountryCode) return

    setPartnersLoading(true)
    fetchTopPartners(selectedCountryCode, selectedYear, selectedFlow)
      .then((data) => setPartners(data))
      .catch(() => setPartners([]))
      .finally(() => setPartnersLoading(false))
  }, [selectedCountryCode, selectedYear, selectedFlow])

  useEffect(() => {
    if (!selectedCountryCode) return

    setTrendLoading(true)
    fetchBilateralTrend(selectedCountryCode, selectedFlow)
      .then((data) => setTrendData(data))
      .catch(() => setTrendData([]))
      .finally(() => setTrendLoading(false))
  }, [selectedCountryCode, selectedFlow])

  useEffect(() => {
    if (!selectedCountryCode) return

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

  return (
    <div
      style={{
        padding: '2rem',
        fontFamily: 'Arial, sans-serif',
        maxWidth: '1100px',
        margin: '0 auto',
      }}
    >
      <h1>Country Trade Explorer</h1>

      <HealthStatus health={health} />

      <CountrySelector
        countries={countries}
        selectedCountryCode={selectedCountryCode}
        selectedCountry={selectedCountry}
        onCountryChange={setSelectedCountryCode}
      />

      <TradeFilters
        year={selectedYear}
        flow={selectedFlow}
        onYearChange={setSelectedYear}
        onFlowChange={setSelectedFlow}
      />

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
    </div>
  )
}

export default App
