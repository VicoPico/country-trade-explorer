function TradeFilters({ year, flow, onYearChange, onFlowChange }) {
  return (
    <section className="panel">
      <h2>Trade Filters</h2>

      <div className="form-row">
        <div className="field-group">
          <label htmlFor="year-select">Year:</label>
          <select
            id="year-select"
            className="select-input"
            value={year}
            onChange={(event) => onYearChange(Number(event.target.value))}
          >
            <option value={2021}>2021</option>
            <option value={2022}>2022</option>
            <option value={2023}>2023</option>
            <option value={2024}>2024</option>
          </select>
        </div>

        <div className="field-group">
          <label htmlFor="flow-select">Flow:</label>
          <select
            id="flow-select"
            className="select-input"
            value={flow}
            onChange={(event) => onFlowChange(event.target.value)}
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
