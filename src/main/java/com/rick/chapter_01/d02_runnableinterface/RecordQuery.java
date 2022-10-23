package com.rick.chapter_01.d02_runnableinterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: Rick
 * @Date: 2022/9/27 00:16
 */
public class RecordQuery {
    private final Connection connection;

    public RecordQuery(Connection connection) {
        this.connection = connection;
    }

    public <T> T query(RowHandler<T> handler, String sql, Object... params) throws SQLException {

        // 可在括号里声明对象
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            int index = 1;
            for (Object param : params) {
                stmt.setObject(index++, param);
            }
            ResultSet resultSet = stmt.executeQuery();
            return handler.handle(resultSet); // 调用RowHandler

        }
    }
}
