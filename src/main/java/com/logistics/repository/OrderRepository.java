package com.logistics.repository;

import com.logistics.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface OrderRepository {
    List<Order> findAll();
    List<Order> findAllWithDetails();
    Order findById(@Param("id") Integer id);
    int insert(Order order);
    int update(Order order);
    int updateStatus(@Param("id") Integer id, @Param("orderStatus") String orderStatus);
    int deleteById(@Param("id") Integer id);
}
