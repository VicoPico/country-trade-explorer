function CountrySelector({
  countries,
  selectedCountryCode,
  selectedCountry,
  onCountryChange,
}) {
  return (
    <section className="panel">
      <h2>Country Selector</h2>

      {countries.length > 0 ? (
        <>
          <div className="field-group">
            <label htmlFor="country-select">Select a country:</label>
            <select
              id="country-select"
              className="select-input"
              value={selectedCountryCode}
              onChange={(event) => onCountryChange(event.target.value)}
            >
              {countries.map((country) => (
                <option key={country.code} value={country.code}>
                  {country.name} ({country.code})
                </option>
              ))}
            </select>
          </div>

          <p className="selected-country">
            Selected country:{' '}
            <strong>
              {selectedCountry
                ? `${selectedCountry.name} (${selectedCountry.code})`
                : 'None'}
            </strong>
          </p>
        </>
      ) : (
        <p className="helper-text">Loading countries...</p>
      )}
    </section>
  )
}

export default CountrySelector
