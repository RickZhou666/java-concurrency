package com.rick.chapter_01.d02_runnableinterface;

import java.sql.ResultSet;

/**
 * @Author: Rick
 * @Date: 2022/9/27 00:16
 */
public interface RowHandler<T>{

    T handle(ResultSet rs);
}
