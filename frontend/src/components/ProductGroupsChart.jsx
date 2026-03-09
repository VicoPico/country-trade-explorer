import ReactECharts from 'echarts-for-react'

function formatTradeValue(value) {
  return new Intl.NumberFormat('en-US', {
    notation: 'compact',
    maximumFractionDigits: 1,
  }).format(value)
}

function ProductGroupsChart({ selectedCountry, products, loading }) {
  const hasData = products.length > 0

  const chartOption = {
    title: {
      text: selectedCountry
        ? `Top Product Groups — ${selectedCountry.name}`
        : 'Top Product Groups',
      subtext: hasData ? 'Most valuable categories in the current slice' : 'Awaiting product data',
      left: 'center',
    },
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'shadow',
      },
      formatter(params) {
        const point = params[0]
        return `${point.name}<br/>Trade value: <strong>${formatTradeValue(point.value)}</strong>`
      },
    },
    grid: {
      left: 40,
      right: 20,
      top: 80,
      bottom: 70,
      containLabel: true,
    },
    xAxis: {
      type: 'category',
      data: products.map((product) => product.productName),
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
        data: products.map((product) => product.tradeValue),
        type: 'bar',
        barMaxWidth: 48,
        label: {
          show: true,
          position: 'top',
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
          <h2>Top Product Groups</h2>
          <p className="panel-subtitle">Which product categories dominate the selected slice</p>
        </div>
        <span className="panel-chip">Products</span>
      </div>

      {loading ? (
        <p className="helper-text">Loading top product groups...</p>
      ) : hasData ? (
        <div className="chart-container">
          <ReactECharts option={chartOption} style={{ height: '400px' }} />
        </div>
      ) : (
        <p className="helper-text">No product-group data available for the selected filters.</p>
      )}
    </section>
  )
}

export default ProductGroupsChart
