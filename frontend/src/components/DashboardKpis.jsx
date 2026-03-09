function formatTradeValue(value) {
  if (value == null || Number.isNaN(value)) {
    return '—'
  }

  return new Intl.NumberFormat('en-US', {
    notation: 'compact',
    maximumFractionDigits: 1,
  }).format(value)
}

function formatPercent(value) {
  if (value == null || Number.isNaN(value)) {
    return '—'
  }

  return `${value > 0 ? '+' : ''}${value.toFixed(1)}%`
}

function DashboardKpis({
  selectedCountry,
  selectedYear,
  selectedFlow,
  partners,
  products,
  trendData,
}) {
  const topPartner = partners.length > 0 ? partners[0] : null
  const topProduct = products.length > 0 ? products[0] : null

  const partnerTotal = partners.reduce((sum, item) => sum + item.tradeValue, 0)
  const productTotal = products.reduce((sum, item) => sum + item.tradeValue, 0)

  const latestTrendValue =
    trendData.length > 0 ? trendData[trendData.length - 1].tradeValue : null
  const previousTrendValue =
    trendData.length > 1 ? trendData[trendData.length - 2].tradeValue : null

  const trendChange =
    latestTrendValue != null &&
    previousTrendValue != null &&
    previousTrendValue !== 0
      ? ((latestTrendValue - previousTrendValue) / previousTrendValue) * 100
      : null

  const cards = [
    {
      label: 'Selected Country',
      value: selectedCountry ? selectedCountry.name : 'Loading...',
      helper: selectedCountry ? selectedCountry.code : '—',
      tone: 'blue',
    },
    {
      label: 'Trade Slice',
      value: `${selectedFlow || '—'} • ${selectedYear || '—'}`,
      helper: 'Active dashboard view',
      tone: 'slate',
    },
    {
      label: 'Top Partner',
      value: topPartner ? topPartner.partnerName : '—',
      helper: topPartner ? formatTradeValue(topPartner.tradeValue) : 'No data',
      tone: 'green',
    },
    {
      label: 'Top Product Group',
      value: topProduct ? topProduct.productName : '—',
      helper: topProduct ? formatTradeValue(topProduct.tradeValue) : 'No data',
      tone: 'purple',
    },
    {
      label: 'Trend Momentum',
      value: formatPercent(trendChange),
      helper:
        latestTrendValue != null
          ? `Latest: ${formatTradeValue(latestTrendValue)}`
          : 'No trend data',
      tone: 'amber',
    },
    {
      label: 'Partners Total',
      value: formatTradeValue(partnerTotal),
      helper: 'Top partners currently shown',
      tone: 'cyan',
    },
    {
      label: 'Products Total',
      value: formatTradeValue(productTotal),
      helper: 'Top products currently shown',
      tone: 'rose',
    },
  ]

  return (
    <section className="kpi-grid">
      {cards.map((card) => (
        <article key={card.label} className={`kpi-card kpi-card--${card.tone}`}>
          <p className="kpi-label">{card.label}</p>
          <h3 className="kpi-value">{card.value}</h3>
          <p className="kpi-helper">{card.helper}</p>
        </article>
      ))}
    </section>
  )
}

export default DashboardKpis
