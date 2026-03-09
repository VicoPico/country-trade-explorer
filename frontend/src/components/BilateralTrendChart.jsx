import ReactECharts from 'echarts-for-react'

function formatTradeValue(value) {
  return new Intl.NumberFormat('en-US', {
    notation: 'compact',
    maximumFractionDigits: 1,
  }).format(value)
}

function BilateralTrendChart({ selectedCountry, trendData, loading }) {
  const hasData = trendData.length > 0

  const chartOption = {
    title: {
      text: selectedCountry
        ? `Bilateral Trade Trend — ${selectedCountry.name}`
        : 'Bilateral Trade Trend',
      subtext: hasData ? 'Historical view across available years' : 'Awaiting trend data',
      left: 'center',
    },
    tooltip: {
      trigger: 'axis',
      formatter(params) {
        const point = params[0]
        return `${point.axisValue}<br/>Trade value: <strong>${formatTradeValue(point.value)}</strong>`
      },
    },
    grid: {
      left: 40,
      right: 20,
      top: 80,
      bottom: 40,
      containLabel: true,
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
        symbolSize: 10,
        areaStyle: {},
        label: {
          show: true,
          formatter(value) {
            return formatTradeValue(value.value)
          },
        },
      },
    ],
  }

  return (
    <section className="panel">
      <div className="panel-heading">
        <div>
          <h2>Bilateral Trade Trend</h2>
          <p className="panel-subtitle">How the selected trade flow changes over time</p>
        </div>
        <span className="panel-chip">Trend</span>
      </div>

      {loading ? (
        <p className="helper-text">Loading bilateral trade trend...</p>
      ) : hasData ? (
        <div className="chart-container">
          <ReactECharts option={chartOption} style={{ height: '400px' }} />
        </div>
      ) : (
        <p className="helper-text">No bilateral trend data available for the selected filters.</p>
      )}
    </section>
  )
}

export default BilateralTrendChart
