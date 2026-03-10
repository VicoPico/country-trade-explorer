import { formatTradeValue } from "../utils/formatTradeValue";

function DashboardKpis({
  selectedCountry,
  selectedYear,
  selectedFlow,
  partners,
  products,
  trendData,
}) {
  const totalPartners = partners.length;
  const totalProducts = products.length;

  const topPartner =
    partners[0]?.partnerName ?? partners[0]?.partner ?? "No data";
  const topPartnerValue = partners[0]?.tradeValue || 0;

  const topProduct =
    products[0]?.productName ?? products[0]?.product ?? "No data";
  const topProductValue = products[0]?.tradeValue || 0;

  const latestTrendValue = trendData.at(-1)?.tradeValue || 0;
  const firstTrendValue = trendData[0]?.tradeValue || 0;

  const trendDirection =
    latestTrendValue > firstTrendValue
      ? "Growing"
      : latestTrendValue < firstTrendValue
        ? "Declining"
        : "Stable";

  return (
    <>
      <section className="kpi-grid kpi-grid--top">
        <article className="kpi-card kpi-card--blue">
          <p className="kpi-label">Selected Country</p>
          <h3 className="kpi-value">
            {selectedCountry ? selectedCountry.name : "Loading"}
          </h3>
          <p className="kpi-helper">{selectedCountry?.code || "—"}</p>
        </article>

        <article className="kpi-card kpi-card--slate">
          <p className="kpi-label">Trade Slice</p>
          <h3 className="kpi-value">{selectedFlow || "—"}</h3>
          <p className="kpi-helper">{selectedYear || "—"}</p>
        </article>

        <article className="kpi-card kpi-card--green">
          <p className="kpi-label">Top Partner</p>
          <h3 className="kpi-value">{topPartner}</h3>
          <p className="kpi-helper">{formatTradeValue(topPartnerValue)}</p>
        </article>
      </section>

      <section className="kpi-grid kpi-grid--bottom">
        <article className="kpi-card kpi-card--purple">
          <p className="kpi-label">Top Product</p>
          <h3 className="kpi-value">{topProduct}</h3>
          <p className="kpi-helper">{formatTradeValue(topProductValue)}</p>
        </article>

        <article className="kpi-card kpi-card--amber">
          <p className="kpi-label">Partners Visible</p>
          <h3 className="kpi-value">{totalPartners}</h3>
          <p className="kpi-helper">Current ranking</p>
        </article>

        <article className="kpi-card kpi-card--cyan">
          <p className="kpi-label">Products Visible</p>
          <h3 className="kpi-value">{totalProducts}</h3>
          <p className="kpi-helper">Current ranking</p>
        </article>

        <article className="kpi-card kpi-card--rose">
          <p className="kpi-label">Trend Direction</p>
          <h3 className="kpi-value">{trendDirection}</h3>
          <p className="kpi-helper">{formatTradeValue(latestTrendValue)}</p>
        </article>
      </section>
    </>
  );
}

export default DashboardKpis;
