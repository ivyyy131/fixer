package com.egt.gateway.repository;
import com.egt.gateway.model.CurrencyData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CurrencyDataRepository extends JpaRepository<CurrencyData, Long> {
    Optional<CurrencyData> findTopByCurrencyOrderByTimestampDesc(String currency);
    List<CurrencyData> findAllByCurrencyAndTimestampAfter(String currency, LocalDateTime timestamp);
}

