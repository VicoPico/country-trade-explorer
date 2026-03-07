import ReactECharts from 'echarts-for-react'

function ProductGroupsChart({ selectedCountry, products }) {
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
    <section>
      <h2>Mock Top Product Groups</h2>
      <ReactECharts option={chartOption} style={{ height: '400px' }} />
    </section>
  )
}

export default ProductGroupsChart
