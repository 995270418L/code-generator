package com.steve.mysql.common;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * BaseDataEntity
 */
@Slf4j
@Data
@NoArgsConstructor
public class BaseDataEntity<T> extends BaseEntity<T> {

    public final static String QUERY_ID = "ids";

    private Integer mid;

    private String data;

}
