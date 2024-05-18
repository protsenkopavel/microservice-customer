package net.protsenko.microservicecustomer.util;

import lombok.Getter;

@Getter
public enum HealthStatus {

    UP("UP"),
    DOWN("DOWN");

    private final String status;

    HealthStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }

}
