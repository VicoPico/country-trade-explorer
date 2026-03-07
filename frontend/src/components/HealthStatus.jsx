function HealthStatus({ health }) {
  return (
    <section className="panel">
      <h2>Backend Health</h2>
      {health ? (
        <pre className="status-pre">{JSON.stringify(health, null, 2)}</pre>
      ) : (
        <p className="helper-text">Loading backend status...</p>
      )}
    </section>
  )
}

export default HealthStatus
