import ReactECharts from 'echarts-for-react'

function PartnersChart({ selectedCountry, partners, loading }) {
  const hasData = partners.length > 0

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
    <section className="panel">
      <h2>Top Trading Partners</h2>

      {loading ? (
        <p className="helper-text">Loading top trading partners...</p>
      ) : hasData ? (
        <div className="chart-container">
          <ReactECharts option={chartOption} style={{ height: '400px' }} />
        </div>
      ) : (
        <p className="helper-text">No partner data available for the selected filters.</p>
      )}
    </section>
  )
}

export default PartnersChart
