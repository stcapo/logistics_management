package com.logistics.repository;

import com.logistics.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface UserRepository {
    List<User> findAll();
    User findById(@Param("id") Integer id);
    User findByUsername(@Param("username") String username);
    int insert(User user);
    int update(User user);
    int deleteById(@Param("id") Integer id);
}
