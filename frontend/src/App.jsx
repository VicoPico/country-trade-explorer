import { useEffect, useState } from 'react'
import ReactECharts from 'echarts-for-react'
import './App.css'

const apiBaseUrl = import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080'

function App() {
  const [health, setHealth] = useState(null)

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

  const chartOption = {
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
      },
    ],
  }

  return (
    <div style={{ padding: '2rem', fontFamily: 'Arial, sans-serif' }}>
      <h1>Country Trade Explorer</h1>

      <section>
        <h2>Backend Health</h2>
        {health ? (
          <pre>{JSON.stringify(health, null, 2)}</pre>
        ) : (
          <p>Loading...</p>
        )}
      </section>

      <section>
        <h2>Mock Trade Trend</h2>
        <ReactECharts option={chartOption} style={{ height: '400px' }} />
      </section>
    </div>
  )
}

export default App
