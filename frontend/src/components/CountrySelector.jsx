function CountrySelector({
  variant = "panel",
  countries,
  loading,
  error,
  selectedCountryCode,
  selectedCountry,
  onCountryChange,
}) {
  const hasCountries = countries.length > 0;
  const selectDisabled = loading || !hasCountries;

  if (variant === "toolbar") {
    return (
      <div className="toolbar-group" aria-busy={loading ? "true" : "false"}>
        {loading ? (
          <p className="helper-text">Loading countries...</p>
        ) : error ? (
          <p className="helper-text">{error}</p>
        ) : hasCountries ? (
          <div className="field-group">
            <label htmlFor="country-select">Country:</label>
            <select
              id="country-select"
              className="select-input"
              value={selectedCountryCode}
              onChange={(event) => onCountryChange(event.target.value)}
              disabled={selectDisabled}
            >
              {countries.map((country) => (
                <option key={country.code} value={country.code}>
                  {country.name} ({country.code})
                </option>
              ))}
            </select>
          </div>
        ) : (
          <p className="helper-text">No countries available.</p>
        )}
        {selectedCountry ? (
          <span className="toolbar-meta">
            Reporter: <strong>{selectedCountry.name}</strong>
          </span>
        ) : null}
      </div>
    );
  }

  return (
    <section className="panel" aria-busy={loading ? "true" : "false"}>
      <div className="panel-heading">
        <div>
          <h2>Country Selector</h2>
          <p className="panel-subtitle">
            Choose the reporter country for the dashboard
          </p>
        </div>
        <span className="panel-chip">Reporter</span>
      </div>

      {loading ? (
        <p className="helper-text">Loading countries...</p>
      ) : error ? (
        <p className="helper-text">{error}</p>
      ) : hasCountries ? (
        <>
          <div className="field-group">
            <label htmlFor="country-select">Select a country:</label>
            <select
              id="country-select"
              className="select-input"
              value={selectedCountryCode}
              onChange={(event) => onCountryChange(event.target.value)}
              disabled={selectDisabled}
            >
              {countries.map((country) => (
                <option key={country.code} value={country.code}>
                  {country.name} ({country.code})
                </option>
              ))}
            </select>
          </div>

          <p className="selected-country">
            Selected country:{" "}
            <strong>
              {selectedCountry
                ? `${selectedCountry.name} (${selectedCountry.code})`
                : "None"}
            </strong>
          </p>
        </>
      ) : (
        <p className="helper-text">No countries available.</p>
      )}
    </section>
  );
}

export default CountrySelector;
