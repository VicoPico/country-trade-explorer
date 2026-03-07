import ReactECharts from 'echarts-for-react'

function BilateralTrendChart({ selectedCountry, trendData, loading }) {
  const hasData = trendData.length > 0

  const chartOption = {
    title: {
      text: selectedCountry
        ? `Bilateral Trade Trend - ${selectedCountry.name}`
        : 'Bilateral Trade Trend',
    },
    tooltip: {
      trigger: 'axis',
    },
    xAxis: {
      type: 'category',
      data: trendData.map((item) => item.year),
    },
    yAxis: {
      type: 'value',
      name: 'Trade value',
    },
    series: [
      {
        data: trendData.map((item) => item.tradeValue),
        type: 'line',
        smooth: true,
      },
    ],
  }

  return (
    <section style={{ marginBottom: '2rem' }}>
      <h2>Bilateral Trade Trend</h2>

      {loading ? (
        <p>Loading bilateral trade trend...</p>
      ) : hasData ? (
        <ReactECharts option={chartOption} style={{ height: '400px' }} />
      ) : (
        <p>No bilateral trend data available for the selected filters.</p>
      )}
    </section>
  )
}

export default BilateralTrendChart
