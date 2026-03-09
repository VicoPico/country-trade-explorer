function readCssVar(styles, name, fallback) {
  const value = styles.getPropertyValue(name).trim();
  return value || fallback;
}

export function getEChartsThemeTokens() {
  const styles = getComputedStyle(document.documentElement);

  return {
    fontFamily: styles.fontFamily || "Inter, system-ui, sans-serif",
    textMain: readCssVar(styles, "--text-main", "#0f172a"),
    textMuted: readCssVar(styles, "--text-muted", "#475569"),
    textSoft: readCssVar(styles, "--text-soft", "#64748b"),
    panelBg: readCssVar(styles, "--panel-bg", "rgba(255, 255, 255, 0.92)"),
    panelBorder: readCssVar(
      styles,
      "--panel-border",
      "rgba(148, 163, 184, 0.2)",
    ),
  };
}

function applyAxisTheme(axis, tokens, { showSplitLines = false } = {}) {
  if (!axis) return axis;

  const themedAxis = {
    ...axis,
    nameTextStyle: {
      ...(axis.nameTextStyle || {}),
      color: tokens.textMuted,
      fontFamily: tokens.fontFamily,
    },
    axisLabel: {
      ...(axis.axisLabel || {}),
      color: tokens.textMuted,
      fontFamily: tokens.fontFamily,
    },
    axisLine: {
      ...(axis.axisLine || {}),
      lineStyle: {
        ...((axis.axisLine && axis.axisLine.lineStyle) || {}),
        color: tokens.panelBorder,
      },
    },
    axisTick: {
      ...(axis.axisTick || {}),
      lineStyle: {
        ...((axis.axisTick && axis.axisTick.lineStyle) || {}),
        color: tokens.panelBorder,
      },
    },
  };

  if (showSplitLines) {
    themedAxis.splitLine = {
      ...(axis.splitLine || {}),
      show: true,
      lineStyle: {
        ...((axis.splitLine && axis.splitLine.lineStyle) || {}),
        color: tokens.panelBorder,
      },
    };
  } else if (axis.splitLine) {
    themedAxis.splitLine = {
      ...(axis.splitLine || {}),
      lineStyle: {
        ...((axis.splitLine && axis.splitLine.lineStyle) || {}),
        color: tokens.panelBorder,
      },
    };
  }

  return themedAxis;
}

export function applyEChartsTheme(option, tokens) {
  const tooltip = option.tooltip || {};
  const tooltipTextColor = tokens.textMain;

  return {
    backgroundColor: "transparent",
    textStyle: {
      ...(option.textStyle || {}),
      color: tokens.textMain,
      fontFamily: tokens.fontFamily,
    },
    ...option,
    title: option.title
      ? {
          ...option.title,
          textStyle: {
            ...(option.title.textStyle || {}),
            color: tokens.textMain,
            fontFamily: tokens.fontFamily,
            fontWeight: 700,
          },
          subtextStyle: {
            ...(option.title.subtextStyle || {}),
            color: tokens.textSoft,
            fontFamily: tokens.fontFamily,
          },
        }
      : option.title,
    tooltip: {
      ...tooltip,
      backgroundColor: tokens.panelBg,
      borderColor: tokens.panelBorder,
      textStyle: {
        ...(tooltip.textStyle || {}),
        color: tooltipTextColor,
        fontFamily: tokens.fontFamily,
      },
      extraCssText: `${tooltip.extraCssText ? `${tooltip.extraCssText}; ` : ""}color: ${tooltipTextColor};`,
    },
    xAxis: Array.isArray(option.xAxis)
      ? option.xAxis.map((axis) => applyAxisTheme(axis, tokens))
      : applyAxisTheme(option.xAxis, tokens),
    yAxis: Array.isArray(option.yAxis)
      ? option.yAxis.map((axis) =>
          applyAxisTheme(axis, tokens, { showSplitLines: true }),
        )
      : applyAxisTheme(option.yAxis, tokens, { showSplitLines: true }),
    series: Array.isArray(option.series)
      ? option.series.map((series) => ({
          ...series,
          label: series.label
            ? {
                ...series.label,
                color: series.label.color || tokens.textMain,
                fontFamily: tokens.fontFamily,
              }
            : series.label,
        }))
      : option.series,
  };
}
