function CountrySelector({
  countries,
  selectedCountryCode,
  selectedCountry,
  onCountryChange,
}) {
  return (
    <section style={{ marginBottom: '2rem' }}>
      <h2>Country Selector</h2>

      {countries.length > 0 ? (
        <>
          <label htmlFor="country-select">Select a country: </label>
          <select
            id="country-select"
            value={selectedCountryCode}
            onChange={(event) => onCountryChange(event.target.value)}
            style={{ marginLeft: '0.5rem', padding: '0.4rem' }}
          >
            {countries.map((country) => (
              <option key={country.code} value={country.code}>
                {country.name} ({country.code})
              </option>
            ))}
          </select>

          <p style={{ marginTop: '1rem' }}>
            Selected country:{' '}
            <strong>
              {selectedCountry
                ? `${selectedCountry.name} (${selectedCountry.code})`
                : 'None'}
            </strong>
          </p>
        </>
      ) : (
        <p>Loading countries...</p>
      )}
    </section>
  )
}

export default CountrySelector
