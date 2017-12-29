package com.epam.preprod.patsera.shop.repository;

import com.epam.preprod.patsera.shop.repository.parser.ResultSetParser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractDao<T extends BaseEntity, PK> implements GenericDao<T, PK> {

    protected ResultSetParser<T> parser;

    protected abstract PreparedStatement prepareCreateQuery(Connection connection, T entity) throws SQLException;
    protected abstract PreparedStatement prepareReadQuery(Connection connection, PK id) throws SQLException;
    protected abstract PreparedStatement prepareUpdateQuery(Connection connection, T entity);
    protected abstract PreparedStatement prepareDeleteQuery(Connection connection, PK key);

    public AbstractDao(ResultSetParser<T> parser) {
        this.parser = parser;
    }

    @Override
    public void create(Connection connection, T objectToCreate) throws SQLException {
        PreparedStatement preparedStatement = prepareCreateQuery(connection, objectToCreate);
        preparedStatement.executeUpdate();
    }

    @Override
    public T read(Connection connection, PK id) throws SQLException {
        PreparedStatement preparedStatement = prepareReadQuery(connection, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        return parser.getDBObject(resultSet);
    }

    @Override
    public void update(Connection connection, T objectToUpdate) throws SQLException {
        PreparedStatement preparedStatement = prepareUpdateQuery(connection, objectToUpdate);
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(Connection connection, PK id) throws SQLException {
        PreparedStatement preparedStatement = prepareDeleteQuery(connection, id);
        preparedStatement.executeUpdate();

    }
}
