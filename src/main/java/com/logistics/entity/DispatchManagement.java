package com.logistics.entity;

import lombok.Data;

@Data
public class DispatchManagement {
    private Integer id;
    private Integer orderid;
    private Integer vehicleid;
    private Integer driverid;
    private String task;
    
    // Additional non-DB fields for UI display
    private String driverName;
    private String licensePlate;
    private String orderStatus;
}
