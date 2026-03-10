import { useMemo } from "react";
import ReactECharts from "echarts-for-react";
import { applyEChartsTheme, getEChartsThemeTokens } from "./echartsTheme";
import { formatTradeValue } from "../utils/formatTradeValue";

function BilateralTrendChart({
  selectedCountry,
  trendData,
  loading,
  error,
  theme,
}) {
  const hasData = trendData.length > 0;

  const chartTokens = useMemo(() => getEChartsThemeTokens(), [theme]);

  const chartOption = useMemo(() => {
    const baseOption = {
      tooltip: {
        trigger: "axis",
        formatter(params) {
          const point = params[0];
          return `${point.axisValue}<br/>Trade value: <strong>${formatTradeValue(point.value)}</strong>`;
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
        data: trendData.map((item) => item.year),
        axisLabel: {
          margin: 12,
          hideOverlap: true,
        },
      },
      yAxis: {
        type: "value",
      },
      series: [
        {
          data: trendData.map((item) => item.tradeValue),
          type: "line",
          smooth: true,
          symbolSize: 10,
          areaStyle: {},
          label: {
            show: true,
            formatter(value) {
              return formatTradeValue(value.value);
            },
          },
        },
      ],
    };

    return applyEChartsTheme(baseOption, chartTokens);
  }, [selectedCountry, hasData, trendData, chartTokens]);

  return (
    <section className="panel" aria-busy={loading ? "true" : "false"}>
      <div className="panel-heading">
        <div>
          <h2>Bilateral Trade Trend</h2>
          <p className="panel-subtitle">Trade value over time</p>
        </div>
        <span className="panel-chip">Trend</span>
      </div>

      {loading ? (
        <p className="helper-text">Loading bilateral trade trend...</p>
      ) : error ? (
        <p className="helper-text">{error}</p>
      ) : hasData ? (
        <div className="chart-container">
          <ReactECharts option={chartOption} style={{ height: "100%" }} />
        </div>
      ) : (
        <p className="helper-text">
          No bilateral trend data available for the selected filters.
        </p>
      )}
    </section>
  );
}

export default BilateralTrendChart;
