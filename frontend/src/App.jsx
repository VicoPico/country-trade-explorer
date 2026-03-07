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

    fetchTopPartners(selectedCountryCode, selectedYear, selectedFlow)
      .then((data) => setPartners(data))
      .catch(() => setPartners([]))
  }, [selectedCountryCode, selectedYear, selectedFlow])

  useEffect(() => {
    if (!selectedCountryCode) return

    fetchBilateralTrend(selectedCountryCode, selectedFlow)
      .then((data) => setTrendData(data))
      .catch(() => setTrendData([]))
  }, [selectedCountryCode, selectedFlow])

  useEffect(() => {
    if (!selectedCountryCode) return

    fetchTopProducts(selectedCountryCode, selectedYear, selectedFlow)
      .then((data) => setProducts(data))
      .catch(() => setProducts([]))
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
      />

      <BilateralTrendChart
        selectedCountry={selectedCountry}
        trendData={trendData}
      />

      <ProductGroupsChart
        selectedCountry={selectedCountry}
        products={products}
      />
    </div>
  )
}

export default App
