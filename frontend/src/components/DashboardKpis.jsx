function formatTradeValue(value) {
  if (value == null || Number.isNaN(value)) {
    return '—'
  }

  return new Intl.NumberFormat('en-US', {
    notation: 'compact',
    maximumFractionDigits: 1,
  }).format(value)
}

function DashboardKpis({
  selectedCountry,
  selectedYear,
  selectedFlow,
  partners,
  products,
  trendData,
}) {
  const topPartner = partners.length > 0 ? partners[0].partnerName : '—'
  const topProduct = products.length > 0 ? products[0].productName : '—'

  const partnerTotal = partners.reduce((sum, item) => sum + item.tradeValue, 0)
  const productTotal = products.reduce((sum, item) => sum + item.tradeValue, 0)
  const latestTrendValue =
    trendData.length > 0 ? trendData[trendData.length - 1].tradeValue : null

  const cards = [
    {
      label: 'Selected Country',
      value: selectedCountry ? selectedCountry.name : 'Loading...',
      helper: selectedCountry ? selectedCountry.code : '—',
    },
    {
      label: 'Selected Filters',
      value: `${selectedFlow || '—'} • ${selectedYear || '—'}`,
      helper: 'Current dashboard slice',
    },
    {
      label: 'Top Partner',
      value: topPartner,
      helper: `Top 5 total: ${formatTradeValue(partnerTotal)}`,
    },
    {
      label: 'Top Product Group',
      value: topProduct,
      helper: `Top 5 total: ${formatTradeValue(productTotal)}`,
    },
    {
      label: 'Latest Trend Value',
      value: formatTradeValue(latestTrendValue),
      helper: 'Most recent point in trend',
    },
  ]

  return (
    <section className="kpi-grid">
      {cards.map((card) => (
        <article key={card.label} className="kpi-card">
          <p className="kpi-label">{card.label}</p>
          <h3 className="kpi-value">{card.value}</h3>
          <p className="kpi-helper">{card.helper}</p>
        </article>
      ))}
    </section>
  )
}

export default DashboardKpis
