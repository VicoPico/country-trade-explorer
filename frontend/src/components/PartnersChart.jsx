import { useMemo } from "react";
import ReactECharts from "echarts-for-react";
import { applyEChartsTheme, getEChartsThemeTokens } from "./echartsTheme";
import { formatTradeValue } from "../utils/formatTradeValue";

function PartnersChart({ selectedCountry, partners, loading, error, theme }) {
  const hasData = partners.length > 0;

  const partnerLabels = useMemo(
    () =>
      partners.map((partner) =>
        String(partner?.partnerName ?? partner?.partner ?? "Unknown").trim(),
      ),
    [partners],
  );

  const chartTokens = useMemo(() => getEChartsThemeTokens(), [theme]);

  const chartOption = useMemo(() => {
    const baseOption = {
      tooltip: {
        trigger: "axis",
        axisPointer: {
          type: "shadow",
        },
        formatter(params) {
          const point = params[0];
          return `${point.name}<br/>Trade value: <strong>${formatTradeValue(point.value)}</strong>`;
        },
      },
      grid: {
        left: 40,
        right: 20,
        top: 24,
        bottom: 70,
        containLabel: true,
      },
      xAxis: {
        type: "category",
        data: partnerLabels,
        axisLabel: {
          rotate: 25,
          margin: 12,
          hideOverlap: true,
        },
      },
      yAxis: {
        type: "value",
      },
      series: [
        {
          data: partners.map((partner) => partner.tradeValue),
          type: "bar",
          barMaxWidth: 48,
          label: {
            show: true,
            position: "top",
            formatter(value) {
              return formatTradeValue(value.value);
            },
          },
        },
      ],
    };

    return applyEChartsTheme(baseOption, chartTokens);
  }, [selectedCountry, hasData, partners, chartTokens, partnerLabels]);

  return (
    <section className="panel" aria-busy={loading ? "true" : "false"}>
      <div className="panel-heading">
        <div>
          <h2>Top Trading Partners</h2>
          <p className="panel-subtitle">Concentration by partner</p>
        </div>
        <span className="panel-chip">Partners</span>
      </div>

      {loading ? (
        <p className="helper-text">Loading top trading partners...</p>
      ) : error ? (
        <p className="helper-text">{error}</p>
      ) : hasData ? (
        <div className="chart-container">
          <ReactECharts option={chartOption} style={{ height: "100%" }} />
        </div>
      ) : (
        <p className="helper-text">
          No partner data available for the selected filters.
        </p>
      )}
    </section>
  );
}

export default PartnersChart;
