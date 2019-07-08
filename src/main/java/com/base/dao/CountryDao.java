package com.base.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface CountryDao {

    @Select({"select count(1) from country"})
     int count();
}
