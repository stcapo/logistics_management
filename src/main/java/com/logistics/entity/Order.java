package com.logistics.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Order {
    private Integer id;
    private Integer userid;
    private Integer vehicleid;
    private String orderStatus;
    private Date creationTime;
    
    // Additional non-DB fields for UI display
    private String username;
    private String licensePlate;
}
