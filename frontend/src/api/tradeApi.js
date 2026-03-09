const apiBaseUrl = import.meta.env.VITE_API_BASE_URL || "http://localhost:8080";

function createAbortSignal({ signal, timeoutMs }) {
  if (!signal && !timeoutMs) return undefined;

  const controller = new AbortController();

  let timeoutId;
  if (
    typeof timeoutMs === "number" &&
    Number.isFinite(timeoutMs) &&
    timeoutMs > 0
  ) {
    timeoutId = setTimeout(() => controller.abort(), timeoutMs);
  }

  function onAbort() {
    controller.abort();
  }

  if (signal) {
    if (signal.aborted) {
      controller.abort();
    } else {
      signal.addEventListener("abort", onAbort, { once: true });
    }
  }

  return {
    signal: controller.signal,
    cleanup() {
      if (timeoutId) clearTimeout(timeoutId);
      if (signal) signal.removeEventListener("abort", onAbort);
    },
  };
}

async function fetchJson(path, options = {}) {
  const composedSignal = createAbortSignal(options);

  try {
    const response = await fetch(`${apiBaseUrl}${path}`, {
      signal: composedSignal?.signal,
    });

    if (!response.ok) {
      throw new Error(
        `Request failed: ${response.status} ${response.statusText}`,
      );
    }

    return response.json();
  } finally {
    composedSignal?.cleanup();
  }
}

export function fetchHealth(options) {
  return fetchJson("/api/health", options);
}

export function fetchCountries(options) {
  return fetchJson("/api/countries", options);
}

export function fetchTradeMetadata(options) {
  return fetchJson("/api/trade/metadata", options);
}

export function fetchTopPartners(reporter, year, flow, options) {
  const params = new URLSearchParams({
    reporter,
    year: String(year),
    flow,
  });

  return fetchJson(`/api/trade/partners?${params.toString()}`, options);
}

export function fetchBilateralTrend(reporter, flow, options) {
  const params = new URLSearchParams({
    reporter,
    flow,
  });

  return fetchJson(`/api/trade/bilateral?${params.toString()}`, options);
}

export function fetchTopProducts(reporter, year, flow, options) {
  const params = new URLSearchParams({
    reporter,
    year: String(year),
    flow,
  });

  return fetchJson(`/api/trade/products?${params.toString()}`, options);
}
