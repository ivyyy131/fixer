package com.egt.gateway.repository;

import com.egt.gateway.model.RequestLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestLogRepository extends JpaRepository<RequestLog, Long> {
    boolean findByRequestId(String requestId);
}



