import { useEffect, useMemo, useState } from 'react'
import ReactECharts from 'echarts-for-react'
import './App.css'

const apiBaseUrl = import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080'

function App() {
  const [health, setHealth] = useState(null)
  const [countries, setCountries] = useState([])
  const [selectedCountryCode, setSelectedCountryCode] = useState('')
  const [partners, setPartners] = useState([])

  useEffect(() => {
    fetch(`${apiBaseUrl}/api/health`)
      .then((res) => res.json())
      .then((data) => setHealth(data))
      .catch(() =>
        setHealth({
          status: 'DOWN',
          service: 'backend unreachable',
        }),
      )
  }, [])

  useEffect(() => {
    fetch(`${apiBaseUrl}/api/countries`)
      .then((res) => res.json())
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

    fetch(`${apiBaseUrl}/api/trade/partners?reporter=${selectedCountryCode}`)
      .then((res) => res.json())
      .then((data) => setPartners(data))
      .catch(() => setPartners([]))
  }, [selectedCountryCode])

  const selectedCountry = useMemo(
    () => countries.find((country) => country.code === selectedCountryCode),
    [countries, selectedCountryCode],
  )

  const trendChartOption = {
    title: {
      text: selectedCountry
        ? `Mock Trade Trend - ${selectedCountry.name}`
        : 'Mock Trade Trend',
    },
    tooltip: {
      trigger: 'axis',
    },
    xAxis: {
      type: 'category',
      data: ['2021', '2022', '2023', '2024'],
    },
    yAxis: {
      type: 'value',
    },
    series: [
      {
        data: [120, 200, 150, 280],
        type: 'line',
        smooth: true,
      },
    ],
  }

  const partnersChartOption = {
    title: {
      text: selectedCountry
        ? `Top Trading Partners - ${selectedCountry.name}`
        : 'Top Trading Partners',
    },
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'shadow',
      },
    },
    xAxis: {
      type: 'category',
      data: partners.map((partner) => partner.partnerName),
      axisLabel: {
        rotate: 25,
      },
    },
    yAxis: {
      type: 'value',
      name: 'Trade value',
    },
    series: [
      {
        data: partners.map((partner) => partner.tradeValue),
        type: 'bar',
      },
    ],
  }

  return (
    <div style={{ padding: '2rem', fontFamily: 'Arial, sans-serif', maxWidth: '1100px', margin: '0 auto' }}>
      <h1>Country Trade Explorer</h1>

      <section style={{ marginBottom: '2rem' }}>
        <h2>Backend Health</h2>
        {health ? (
          <pre>{JSON.stringify(health, null, 2)}</pre>
        ) : (
          <p>Loading backend status...</p>
        )}
      </section>

      <section style={{ marginBottom: '2rem' }}>
        <h2>Country Selector</h2>

        {countries.length > 0 ? (
          <>
            <label htmlFor="country-select">Select a country: </label>
            <select
              id="country-select"
              value={selectedCountryCode}
              onChange={(event) => setSelectedCountryCode(event.target.value)}
              style={{ marginLeft: '0.5rem', padding: '0.4rem' }}
            >
              {countries.map((country) => (
                <option key={country.code} value={country.code}>
                  {country.name} ({country.code})
                </option>
              ))}
            </select>

            <p style={{ marginTop: '1rem' }}>
              Selected country:{' '}
              <strong>
                {selectedCountry ? `${selectedCountry.name} (${selectedCountry.code})` : 'None'}
              </strong>
            </p>
          </>
        ) : (
          <p>Loading countries...</p>
        )}
      </section>

      <section style={{ marginBottom: '2rem' }}>
        <h2>Mock Top Trading Partners</h2>
        <ReactECharts option={partnersChartOption} style={{ height: '400px' }} />
      </section>

      <section>
        <h2>Mock Trade Trend</h2>
        <ReactECharts option={trendChartOption} style={{ height: '400px' }} />
      </section>
    </div>
  )
}

export default App
