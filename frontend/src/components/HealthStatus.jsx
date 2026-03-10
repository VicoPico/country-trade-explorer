function HealthStatus({ health, loading, error }) {
  return (
    <section className="panel" aria-busy={loading ? "true" : "false"}>
      <div className="panel-heading">
        <div>
          <h2>Backend Health</h2>
          <p className="panel-subtitle">
            Quick system snapshot for the current API state
          </p>
        </div>
        <span className="panel-chip">Status</span>
      </div>

      {loading ? (
        <p className="helper-text">Loading backend status...</p>
      ) : error ? (
        <p className="helper-text">{error}</p>
      ) : health ? (
        <pre className="status-pre">{JSON.stringify(health, null, 2)}</pre>
      ) : (
        <p className="helper-text">No health data available.</p>
      )}
    </section>
  );
}

export default HealthStatus;
