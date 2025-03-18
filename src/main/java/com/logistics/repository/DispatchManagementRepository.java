package com.logistics.repository;

import com.logistics.entity.DispatchManagement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface DispatchManagementRepository {
    List<DispatchManagement> findAll();
    List<DispatchManagement> findAllWithDetails();
    DispatchManagement findById(@Param("id") Integer id);
    DispatchManagement findByOrderId(@Param("orderId") Integer orderId);
    int insert(DispatchManagement dispatchManagement);
    int update(DispatchManagement dispatchManagement);
    int deleteById(@Param("id") Integer id);
}
