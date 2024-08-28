package com.egt.gateway.controller;

import com.egt.gateway.dto.json.CurrencyHistoryRequest;
import com.egt.gateway.dto.json.CurrencyRequest;
import com.egt.gateway.model.CurrencyData;
import com.egt.gateway.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/json_api")
public class JsonApiController {

    @Autowired
    private CurrencyService currencyService;

    @PostMapping("/current")
    public ResponseEntity<?> getCurrentCurrency(@RequestBody CurrencyRequest request) {
        currencyService.logRequest(request.getRequestId(), "EXT_SERVICE_1", request.getClient());
        CurrencyData currencyData = currencyService.getCurrentRate(request.getCurrency());
        return ResponseEntity.ok(currencyData);
    }

    @PostMapping("/history")
    public ResponseEntity<?> getCurrencyHistory(@RequestBody CurrencyHistoryRequest request) {
        currencyService.logRequest(request.getRequestId(), "EXT_SERVICE_1", request.getClient());
        return ResponseEntity.ok(currencyService.getCurrencyHistory(request.getCurrency(), request.getPeriod()));
    }
}
