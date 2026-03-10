export function formatTradeValue(
  value,
  {
    emptyLabel = "No data",
    maximumFractionDigits = 1,
    zeroIsEmpty = true,
  } = {},
) {
  const numericValue = typeof value === "number" ? value : Number(value);

  if (!Number.isFinite(numericValue)) return emptyLabel;
  if (numericValue < 0) return emptyLabel;
  if (zeroIsEmpty && numericValue === 0) return emptyLabel;

  return new Intl.NumberFormat("en-US", {
    notation: "compact",
    maximumFractionDigits,
  }).format(numericValue);
}
