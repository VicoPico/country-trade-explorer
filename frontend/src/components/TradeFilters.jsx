function TradeFilters({ year, flow, onYearChange, onFlowChange }) {
  return (
    <section style={{ marginBottom: '2rem' }}>
      <h2>Trade Filters</h2>

      <div style={{ display: 'flex', gap: '1rem', alignItems: 'center', flexWrap: 'wrap' }}>
        <div>
          <label htmlFor="year-select">Year: </label>
          <select
            id="year-select"
            value={year}
            onChange={(event) => onYearChange(Number(event.target.value))}
            style={{ marginLeft: '0.5rem', padding: '0.4rem' }}
          >
            <option value={2021}>2021</option>
            <option value={2022}>2022</option>
            <option value={2023}>2023</option>
            <option value={2024}>2024</option>
          </select>
        </div>

        <div>
          <label htmlFor="flow-select">Flow: </label>
          <select
            id="flow-select"
            value={flow}
            onChange={(event) => onFlowChange(event.target.value)}
            style={{ marginLeft: '0.5rem', padding: '0.4rem' }}
          >
            <option value="EXPORT">Export</option>
            <option value="IMPORT">Import</option>
          </select>
        </div>
      </div>
    </section>
  )
}

export default TradeFilters
