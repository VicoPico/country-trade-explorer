function TradeFilters({
  years,
  flows,
  year,
  flow,
  loading,
  onYearChange,
  onFlowChange,
}) {
  return (
    <section className="panel">
      <h2>Trade Filters</h2>

      {loading ? (
        <p className="helper-text">Loading filter options...</p>
      ) : (
        <div className="form-row">
          <div className="field-group">
            <label htmlFor="year-select">Year:</label>
            <select
              id="year-select"
              className="select-input"
              value={year}
              onChange={(event) => onYearChange(Number(event.target.value))}
            >
              {years.map((availableYear) => (
                <option key={availableYear} value={availableYear}>
                  {availableYear}
                </option>
              ))}
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
              {flows.map((availableFlow) => (
                <option key={availableFlow} value={availableFlow}>
                  {availableFlow}
                </option>
              ))}
            </select>
          </div>
        </div>
      )}
    </section>
  )
}

export default TradeFilters
