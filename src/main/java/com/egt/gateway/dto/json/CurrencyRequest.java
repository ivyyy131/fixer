package com.egt.gateway.dto.json;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CurrencyRequest {

    @NotBlank
    private String requestId;

    @NotNull
    private Long timestamp;

    @NotBlank
    private String client;

    @NotBlank
    private String currency;

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
}

