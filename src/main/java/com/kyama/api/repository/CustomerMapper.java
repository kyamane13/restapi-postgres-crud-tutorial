package com.kyama.api.repository;

import com.kyama.api.dto.Customer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerMapper {
    int insert(Customer customer);
}
