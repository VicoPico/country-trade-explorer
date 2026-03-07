function HealthStatus({ health }) {
  return (
    <section style={{ marginBottom: '2rem' }}>
      <h2>Backend Health</h2>
      {health ? (
        <pre>{JSON.stringify(health, null, 2)}</pre>
      ) : (
        <p>Loading backend status...</p>
      )}
    </section>
  )
}

export default HealthStatus
