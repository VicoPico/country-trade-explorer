package com.example.tradeexplorer.trade.importer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "uncomtrade")
public class UnComtradeProperties {

    private boolean enabled = false;
    private boolean preview = true;
    private String finalDataUrl = "";
    private String apiKey = "";
    private String apiKeyHeaderName = "Ocp-Apim-Subscription-Key";
    private int maxRecords = 500;
    private List<String> commodityCodes = new ArrayList<>(List.of(
            "84", "85", "27", "30", "04", "87", "44", "48", "26", "90", "12"
    ));

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isPreview() {
        return preview;
    }

    public void setPreview(boolean preview) {
        this.preview = preview;
    }

    public String getFinalDataUrl() {
        return finalDataUrl;
    }

    public void setFinalDataUrl(String finalDataUrl) {
        this.finalDataUrl = finalDataUrl;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiKeyHeaderName() {
        return apiKeyHeaderName;
    }

    public void setApiKeyHeaderName(String apiKeyHeaderName) {
        this.apiKeyHeaderName = apiKeyHeaderName;
    }

    public int getMaxRecords() {
        return maxRecords;
    }

    public void setMaxRecords(int maxRecords) {
        this.maxRecords = maxRecords;
    }

    public List<String> getCommodityCodes() {
        return commodityCodes;
    }

    public void setCommodityCodes(List<String> commodityCodes) {
        this.commodityCodes = commodityCodes;
    }
}
