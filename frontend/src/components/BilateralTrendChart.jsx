import ReactECharts from 'echarts-for-react'

function BilateralTrendChart({ selectedCountry, trendData }) {
  const chartOption = {
    title: {
      text: selectedCountry
        ? `Mock Bilateral Trade Trend - ${selectedCountry.name}`
        : 'Mock Bilateral Trade Trend',
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
      <h2>Mock Bilateral Trade Trend</h2>
      <ReactECharts option={chartOption} style={{ height: '400px' }} />
    </section>
  )
}

export default BilateralTrendChart
