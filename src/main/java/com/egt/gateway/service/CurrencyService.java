package com.egt.gateway.service;

import com.egt.gateway.model.CurrencyData;
import com.egt.gateway.model.RequestLog;
import com.egt.gateway.repository.CurrencyDataRepository;
import com.egt.gateway.repository.RequestLogRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyDataRepository currencyDataRepository;

    @Autowired
    private RequestLogRepository requestLogRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public CurrencyData getCurrentRate(String currency) {
        return currencyDataRepository.findTopByCurrencyOrderByTimestampDesc(currency)
                .orElseThrow(() -> new RuntimeException("No data found"));
    }

    public List<CurrencyData> getCurrencyHistory(String currency, int period) {
        LocalDateTime fromTime = LocalDateTime.now().minusHours(period);
        return currencyDataRepository.findAllByCurrencyAndTimestampAfter(currency, fromTime);
    }

    public void logRequest(String requestId, String serviceName, String clientId) {
        if (requestLogRepository.findByRequestId(requestId)) {
            throw new RuntimeException("Duplicate request");
        }
        RequestLog log = new RequestLog();
        log.setRequestId(requestId);
        log.setServiceName(serviceName);
        log.setClientId(clientId);
        log.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));

        requestLogRepository.save(log);

        rabbitTemplate.convertAndSend("request-logs-exchange", "", log);
    }
}


