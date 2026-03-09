import ReactECharts from 'echarts-for-react'

function formatTradeValue(value) {
  return new Intl.NumberFormat('en-US', {
    notation: 'compact',
    maximumFractionDigits: 1,
  }).format(value)
}

function PartnersChart({ selectedCountry, partners, loading }) {
  const hasData = partners.length > 0

  const chartOption = {
    title: {
      text: selectedCountry
        ? `Top Trading Partners — ${selectedCountry.name}`
        : 'Top Trading Partners',
      subtext: hasData ? 'Ranked by current filtered trade value' : 'Awaiting partner data',
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
          <h2>Top Trading Partners</h2>
          <p className="panel-subtitle">Where the selected country concentrates its trade</p>
        </div>
        <span className="panel-chip">Partners</span>
      </div>

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
