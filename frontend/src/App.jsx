import { useEffect, useMemo, useState } from "react";
import "./App.css";
import {
  fetchBilateralTrend,
  fetchCountries,
  fetchTopPartners,
  fetchTopProducts,
  fetchTradeMetadata,
} from "./api/tradeApi";
import BilateralTrendChart from "./components/BilateralTrendChart";
import CountrySelector from "./components/CountrySelector";
import DashboardKpis from "./components/DashboardKpis";
import PartnersChart from "./components/PartnersChart";
import ProductGroupsChart from "./components/ProductGroupsChart";
import TradeFilters from "./components/TradeFilters";

const THEME_STORAGE_KEY = "country-trade-explorer-theme";
const DEFAULT_REQUEST_TIMEOUT_MS = 15000;

function App() {
  const [theme, setTheme] = useState(() => {
    const storedTheme = localStorage.getItem(THEME_STORAGE_KEY);
    return storedTheme || "light";
  });

  const [countries, setCountries] = useState([]);
  const [countriesLoading, setCountriesLoading] = useState(true);
  const [countriesError, setCountriesError] = useState(null);
  const [selectedCountryCode, setSelectedCountryCode] = useState("");

  const [availableYears, setAvailableYears] = useState([]);
  const [availableFlows, setAvailableFlows] = useState([]);
  const [metadataLoading, setMetadataLoading] = useState(true);
  const [metadataError, setMetadataError] = useState(null);

  const [selectedYear, setSelectedYear] = useState(null);
  const [selectedFlow, setSelectedFlow] = useState("");

  const [partners, setPartners] = useState([]);
  const [trendData, setTrendData] = useState([]);
  const [products, setProducts] = useState([]);

  const [partnersLoading, setPartnersLoading] = useState(false);
  const [trendLoading, setTrendLoading] = useState(false);
  const [productsLoading, setProductsLoading] = useState(false);

  const [partnersError, setPartnersError] = useState(null);
  const [trendError, setTrendError] = useState(null);
  const [productsError, setProductsError] = useState(null);

  useEffect(() => {
    document.documentElement.setAttribute("data-theme", theme);
    localStorage.setItem(THEME_STORAGE_KEY, theme);
  }, [theme]);

  useEffect(() => {
    const controller = new AbortController();
    let active = true;

    setCountriesLoading(true);
    setCountriesError(null);

    fetchCountries({
      signal: controller.signal,
      timeoutMs: DEFAULT_REQUEST_TIMEOUT_MS,
    })
      .then((data) => {
        if (!active) return;
        setCountries(data);
        if (data.length > 0) {
          setSelectedCountryCode(data[0].code);
        }
      })
      .catch((error) => {
        if (!active) return;
        if (error?.name === "AbortError") return;
        setCountries([]);
        setCountriesError(
          error?.name === "TimeoutError"
            ? "Loading countries timed out."
            : "Failed to load countries.",
        );
      })
      .finally(() => {
        if (!active) return;
        setCountriesLoading(false);
      });

    return () => {
      active = false;
      controller.abort();
    };
  }, []);

  useEffect(() => {
    const controller = new AbortController();
    let active = true;

    setMetadataLoading(true);
    setMetadataError(null);

    fetchTradeMetadata({
      signal: controller.signal,
      timeoutMs: DEFAULT_REQUEST_TIMEOUT_MS,
    })
      .then((data) => {
        if (!active) return;
        setAvailableYears(data.years || []);
        setAvailableFlows(data.flows || []);

        if ((data.years || []).length > 0) {
          setSelectedYear(
            (currentYear) => currentYear ?? data.years[data.years.length - 1],
          );
        }

        if ((data.flows || []).length > 0) {
          setSelectedFlow((currentFlow) => currentFlow || data.flows[0]);
        }
      })
      .catch((error) => {
        if (!active) return;
        if (error?.name === "AbortError") return;
        setAvailableYears([]);
        setAvailableFlows([]);
        setMetadataError(
          error?.name === "TimeoutError"
            ? "Loading metadata timed out."
            : "Failed to load filter metadata.",
        );
      })
      .finally(() => {
        if (!active) return;
        setMetadataLoading(false);
      });

    return () => {
      active = false;
      controller.abort();
    };
  }, []);

  useEffect(() => {
    if (!selectedCountryCode || !selectedYear || !selectedFlow) {
      setPartnersLoading(false);
      setPartnersError(null);
      return;
    }

    const controller = new AbortController();
    let active = true;

    setPartnersLoading(true);
    setPartnersError(null);
    fetchTopPartners(selectedCountryCode, selectedYear, selectedFlow, {
      signal: controller.signal,
      timeoutMs: DEFAULT_REQUEST_TIMEOUT_MS,
    })
      .then((data) => {
        if (!active) return;
        setPartners(data);
      })
      .catch((error) => {
        if (!active) return;
        if (error?.name === "AbortError") return;
        setPartners([]);
        setPartnersError(
          error?.name === "TimeoutError"
            ? "Loading partners timed out."
            : "Failed to load partners.",
        );
      })
      .finally(() => {
        if (!active) return;
        setPartnersLoading(false);
      });

    return () => {
      active = false;
      controller.abort();
    };
  }, [selectedCountryCode, selectedYear, selectedFlow]);

  useEffect(() => {
    if (!selectedCountryCode || !selectedFlow) {
      setTrendLoading(false);
      setTrendError(null);
      return;
    }

    const controller = new AbortController();
    let active = true;

    setTrendLoading(true);
    setTrendError(null);
    fetchBilateralTrend(selectedCountryCode, selectedFlow, {
      signal: controller.signal,
      timeoutMs: DEFAULT_REQUEST_TIMEOUT_MS,
    })
      .then((data) => {
        if (!active) return;
        setTrendData(data);
      })
      .catch((error) => {
        if (!active) return;
        if (error?.name === "AbortError") return;
        setTrendData([]);
        setTrendError(
          error?.name === "TimeoutError"
            ? "Loading trend timed out."
            : "Failed to load trend.",
        );
      })
      .finally(() => {
        if (!active) return;
        setTrendLoading(false);
      });

    return () => {
      active = false;
      controller.abort();
    };
  }, [selectedCountryCode, selectedFlow]);

  useEffect(() => {
    if (!selectedCountryCode || !selectedYear || !selectedFlow) {
      setProductsLoading(false);
      setProductsError(null);
      return;
    }

    const controller = new AbortController();
    let active = true;

    setProductsLoading(true);
    setProductsError(null);
    fetchTopProducts(selectedCountryCode, selectedYear, selectedFlow, {
      signal: controller.signal,
      timeoutMs: DEFAULT_REQUEST_TIMEOUT_MS,
    })
      .then((data) => {
        if (!active) return;
        setProducts(data);
      })
      .catch((error) => {
        if (!active) return;
        if (error?.name === "AbortError") return;
        setProducts([]);
        setProductsError(
          error?.name === "TimeoutError"
            ? "Loading products timed out."
            : "Failed to load products.",
        );
      })
      .finally(() => {
        if (!active) return;
        setProductsLoading(false);
      });

    return () => {
      active = false;
      controller.abort();
    };
  }, [selectedCountryCode, selectedYear, selectedFlow]);

  const selectedCountry = useMemo(
    () => countries.find((country) => country.code === selectedCountryCode),
    [countries, selectedCountryCode],
  );

  function handleThemeToggle() {
    setTheme((currentTheme) => (currentTheme === "light" ? "dark" : "light"));
  }

  return (
    <div className="app-shell">
      <section className="top-band">
        <div className="hero-card">
          <div className="hero-toolbar">
            <p className="hero-eyebrow">Country Trade Explorer</p>
            <button
              type="button"
              className="theme-toggle"
              onClick={handleThemeToggle}
              aria-pressed={theme === "dark"}
            >
              {theme === "light" ? "Dark mode" : "Light mode"}
            </button>
          </div>

          <h1 className="app-title">
            Trade dashboard for bilateral relationships and product flows
          </h1>
          <p className="hero-text">
            Explore partners, trends, and product groups for a selected trade
            slice.
          </p>
        </div>

        <div className="kpi-stack">
          <DashboardKpis
            selectedCountry={selectedCountry}
            selectedYear={selectedYear}
            selectedFlow={selectedFlow}
            partners={partners}
            products={products}
            trendData={trendData}
          />
        </div>
      </section>

      <section className="controls-toolbar panel">
        <CountrySelector
          variant="toolbar"
          countries={countries}
          loading={countriesLoading}
          error={countriesError}
          selectedCountryCode={selectedCountryCode}
          selectedCountry={selectedCountry}
          onCountryChange={setSelectedCountryCode}
        />

        <TradeFilters
          variant="toolbar"
          years={availableYears}
          flows={availableFlows}
          year={selectedYear ?? ""}
          flow={selectedFlow}
          loading={metadataLoading}
          error={metadataError}
          onYearChange={setSelectedYear}
          onFlowChange={setSelectedFlow}
        />
      </section>

      <section className="charts-band">
        <PartnersChart
          selectedCountry={selectedCountry}
          partners={partners}
          loading={partnersLoading}
          error={partnersError}
          theme={theme}
        />

        <BilateralTrendChart
          selectedCountry={selectedCountry}
          trendData={trendData}
          loading={trendLoading}
          error={trendError}
          theme={theme}
        />

        <ProductGroupsChart
          selectedCountry={selectedCountry}
          products={products}
          loading={productsLoading}
          error={productsError}
          theme={theme}
        />
      </section>
    </div>
  );
}

export default App;
