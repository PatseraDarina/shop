package com.epam.preprod.patsera.shop.repository.transaction;

import com.epam.preprod.patsera.shop.exception.TransactionInterruptedException;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {
    private static final Logger LOGGER = Logger.getLogger(TransactionManager.class);
    private DataSource dataSource;

    public TransactionManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public  <T> T doTransaction(Operation<T> operation) throws TransactionInterruptedException {
        T result = null;

        try (Connection connection = dataSource.getConnection()) {
            try {
                connection.setAutoCommit(false);
                connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
                result = operation.execute(connection);
                connection.commit();
            } catch (Exception ex) {
                rollback(connection);
                throw new TransactionInterruptedException(ex.getMessage(), ex);
            }
        } catch (SQLException ex) {
            LOGGER.warn(ex.getMessage(), ex);
        }
        return result;
}

    private void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }
}
