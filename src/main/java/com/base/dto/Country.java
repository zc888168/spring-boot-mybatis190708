package com.base.dto;

import lombok.Data;

@Data
public class Country{
    private Integer id;

    private Integer page = 1;

    private Integer rows = 10;
    /**
     * 名称
     */
    private String countryname;

    /**
     * 代码
     */
    private String countrycode;


}