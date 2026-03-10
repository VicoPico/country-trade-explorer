import { useMemo } from "react";
import ReactECharts from "echarts-for-react";
import { applyEChartsTheme, getEChartsThemeTokens } from "./echartsTheme";
import { formatTradeValue } from "../utils/formatTradeValue";

function ProductGroupsChart({
  selectedCountry,
  products,
  loading,
  error,
  theme,
}) {
  const hasData = products.length > 0;

  const productLabels = useMemo(
    () =>
      products.map((product) =>
        String(product?.productName ?? product?.product ?? "Unknown").trim(),
      ),
    [products],
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
        data: productLabels,
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
          data: products.map((product) => product.tradeValue),
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
  }, [selectedCountry, hasData, products, chartTokens, productLabels]);

  return (
    <section className="panel" aria-busy={loading ? "true" : "false"}>
      <div className="panel-heading">
        <div>
          <h2>Top Product Groups</h2>
          <p className="panel-subtitle">Top categories in this slice</p>
        </div>
        <span className="panel-chip">Products</span>
      </div>

      {loading ? (
        <p className="helper-text">Loading top product groups...</p>
      ) : error ? (
        <p className="helper-text">{error}</p>
      ) : hasData ? (
        <div className="chart-container">
          <ReactECharts option={chartOption} style={{ height: "100%" }} />
        </div>
      ) : (
        <p className="helper-text">
          No product-group data available for the selected filters.
        </p>
      )}
    </section>
  );
}

export default ProductGroupsChart;
