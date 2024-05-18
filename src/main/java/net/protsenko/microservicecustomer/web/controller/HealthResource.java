package net.protsenko.microservicecustomer.web.controller;

import lombok.extern.slf4j.Slf4j;
import net.protsenko.microservicecustomer.util.Health;
import net.protsenko.microservicecustomer.util.HealthStatus;
import net.protsenko.microserviceorder.util.Health;
import net.protsenko.microserviceorder.util.HealthStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class HealthResource {

    @GetMapping(
            value = "/health",
            produces = "application/json")
    public ResponseEntity<Health> getHealth() {
        log.debug("REST request to get the Health Status");
        final var health = new Health();
        health.setStatus(HealthStatus.UP);
        return ResponseEntity.ok().body(health);
    }

}
