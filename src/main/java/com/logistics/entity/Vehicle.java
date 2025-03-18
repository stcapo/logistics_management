package com.logistics.entity;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class Vehicle {
    private Integer id;
    private String licensePlate;
    private String model;
    private BigDecimal loadCapacity;
}
