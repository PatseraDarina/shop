package com.epam.preprod.patsera.shop.repository.transaction;

import java.sql.Connection;
import java.sql.SQLException;

public interface Operation<T> {
    T execute(Connection connection) throws SQLException;
}
