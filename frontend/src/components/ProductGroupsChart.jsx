import ReactECharts from 'echarts-for-react'

function ProductGroupsChart({ selectedCountry, products, loading }) {
  const hasData = products.length > 0

  const chartOption = {
    title: {
      text: selectedCountry
        ? `Top Product Groups - ${selectedCountry.name}`
        : 'Top Product Groups',
    },
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'shadow',
      },
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
      },
    ],
  }

  return (
    <section className="panel">
      <h2>Top Product Groups</h2>

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
