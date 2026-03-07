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

function App() {
  const [health, setHealth] = useState(null)
  const [countries, setCountries] = useState([])
  const [selectedCountryCode, setSelectedCountryCode] = useState('')
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

    fetchTopPartners(selectedCountryCode)
      .then((data) => setPartners(data))
      .catch(() => setPartners([]))
  }, [selectedCountryCode])

  useEffect(() => {
    if (!selectedCountryCode) return

    fetchBilateralTrend(selectedCountryCode)
      .then((data) => setTrendData(data))
      .catch(() => setTrendData([]))
  }, [selectedCountryCode])

  useEffect(() => {
    if (!selectedCountryCode) return

    fetchTopProducts(selectedCountryCode)
      .then((data) => setProducts(data))
      .catch(() => setProducts([]))
  }, [selectedCountryCode])

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
