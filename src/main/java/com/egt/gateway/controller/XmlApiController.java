package com.egt.gateway.controller;

import com.egt.gateway.dto.xml.CommandRequest;
import com.egt.gateway.model.CurrencyData;
import com.egt.gateway.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/xml_api")
public class XmlApiController {

    @Autowired
    private CurrencyService currencyService;

    @PostMapping("/command")
    public ResponseEntity<?> handleCommand(@RequestBody CommandRequest request) {
        if (request.isHistoryCommand()) {
            currencyService.logRequest(request.getId(), "EXT_SERVICE_2", request.getConsumer());
            return ResponseEntity.ok(currencyService.getCurrencyHistory(request.getCurrency(), request.getPeriod()));
        } else {
            currencyService.logRequest(request.getId(), "EXT_SERVICE_2", request.getConsumer());
            CurrencyData currencyData = currencyService.getCurrentRate(request.getCurrency());
            return ResponseEntity.ok(currencyData);
        }
    }
}


