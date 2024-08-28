package com.egt.gateway.service;

import com.egt.gateway.model.CurrencyData;
import com.egt.gateway.repository.CurrencyDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;

@Service
public class FixerService {

    @Autowired
    private CurrencyDataRepository currencyDataRepository;

    @Value("${fixer.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @Scheduled(fixedRateString = "${fixer.update.rate}")
    public void updateCurrencyData() {
        Map<String, Object> response = restTemplate.getForObject(apiUrl, Map.class);
        if (response != null && response.get("rates") != null) {
            Map<String, BigDecimal> rates = (Map<String, BigDecimal>) response.get("rates");
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            rates.forEach((currency, rate) -> {
                CurrencyData data = new CurrencyData();
                data.setCurrency(currency);
                data.setRate(rate);
                data.setTimestamp(timestamp);
                currencyDataRepository.save(data);
            });
        }
    }
}
