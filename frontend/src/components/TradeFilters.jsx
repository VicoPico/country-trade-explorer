function TradeFilters({
  variant = "panel",
  years,
  flows,
  year,
  flow,
  loading,
  error,
  onYearChange,
  onFlowChange,
}) {
  const hasYears = years.length > 0;
  const hasFlows = flows.length > 0;

  if (variant === "toolbar") {
    return (
      <div className="toolbar-group" aria-busy={loading ? "true" : "false"}>
        {loading ? (
          <p className="helper-text">Loading filter options...</p>
        ) : error ? (
          <p className="helper-text">{error}</p>
        ) : !hasYears || !hasFlows ? (
          <p className="helper-text">No filter metadata available.</p>
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
      </div>
    );
  }

  return (
    <section className="panel" aria-busy={loading ? "true" : "false"}>
      <div className="panel-heading">
        <div>
          <h2>Trade Filters</h2>
          <p className="panel-subtitle">
            Drive the current dashboard slice from backend metadata
          </p>
        </div>
        <span className="panel-chip">Metadata</span>
      </div>

      {loading ? (
        <p className="helper-text">Loading filter options...</p>
      ) : error ? (
        <p className="helper-text">{error}</p>
      ) : !hasYears || !hasFlows ? (
        <p className="helper-text">No filter metadata available.</p>
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
  );
}

export default TradeFilters;
