import ReactECharts from 'echarts-for-react'

function PartnersChart({ selectedCountry, partners }) {
  const chartOption = {
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
    <section style={{ marginBottom: '2rem' }}>
      <h2>Mock Top Trading Partners</h2>
      <ReactECharts option={chartOption} style={{ height: '400px' }} />
    </section>
  )
}

export default PartnersChart
