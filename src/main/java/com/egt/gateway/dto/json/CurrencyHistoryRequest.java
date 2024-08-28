package com.egt.gateway.dto.json;

import javax.validation.constraints.NotNull;

public class CurrencyHistoryRequest {

    @NotNull
    private String requestId;

    @NotNull
    private Long timestamp;

    @NotNull
    private String client;

    @NotNull
    private String currency;

    @NotNull
    private Integer period;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }
}
