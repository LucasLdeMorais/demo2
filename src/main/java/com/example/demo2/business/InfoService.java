package com.example.demo2.business;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;

@Service
public class InfoService {

    @Value("${ratelimit.auth.time}")private int time;
    @Value("${ratelimit.auth.unit}")private String unit;
    @Value("${ratelimit.auth.requestAmount}")private int requestAmount;

    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();

    public Bucket resolveBucket(HttpServletRequest request) {
        String ip = clientIp(request);
        return cache.computeIfAbsent(ip, this::newBucket);
    }
    
    public String clientIp(HttpServletRequest request)  {
        String remoteAddr = "";
        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }
        return remoteAddr;
    }

    Bandwidth getLimit() {
        return Bandwidth.classic(requestAmount, Refill.intervally(1, Duration.of(time, ChronoUnit.valueOf(unit))));
    }

    public Bucket newBucket(String clientIp) {
        return Bucket4j.builder()
            .addLimit(getLimit())
            .build();
    }
}