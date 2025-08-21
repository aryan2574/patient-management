package com.pm.patientservice.aspects;

import io.micrometer.core.instrument.MeterRegistry;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PatientServiceMetrics {

    private final MeterRegistry meterRegistry;

    public PatientServiceMetrics(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Around("execution(* com.pm.patientservice.service.PatientService.getPatients(int, int, String, String, String))")
    public Object monitorGetPatients(ProceedingJoinPoint joinPoint) throws Throwable {
        meterRegistry.counter("custom.redis.cache.miss", "cache", "patients").increment();

        Object result = joinPoint.proceed();

        // Assume result has isCacheHit() method
        if (result instanceof com.pm.patientservice.model.PatientResponse) {
            com.pm.patientservice.model.PatientResponse response = (com.pm.patientservice.model.PatientResponse) result;
            if (!response.isCacheHit()) {
                meterRegistry.counter("custom.redis.cache.miss", "cache", "patients").increment();
            } else {
                meterRegistry.counter("custom.redis.cache.hit", "cache", "patients").increment();
            }
        }
        return result;
    }
}
