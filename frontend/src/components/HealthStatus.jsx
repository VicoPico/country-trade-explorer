function HealthStatus({ health }) {
  return (
    <section className="panel">
      <div className="panel-heading">
        <div>
          <h2>Backend Health</h2>
          <p className="panel-subtitle">Quick system snapshot for the current API state</p>
        </div>
        <span className="panel-chip">Status</span>
      </div>

      {health ? (
        <pre className="status-pre">{JSON.stringify(health, null, 2)}</pre>
      ) : (
        <p className="helper-text">Loading backend status...</p>
      )}
    </section>
  )
}

export default HealthStatus
