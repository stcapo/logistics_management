package com.logistics.repository;

import com.logistics.entity.Vehicle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface VehicleRepository {
    List<Vehicle> findAll();
    Vehicle findById(@Param("id") Integer id);
    int insert(Vehicle vehicle);
    int update(Vehicle vehicle);
    int deleteById(@Param("id") Integer id);
}
