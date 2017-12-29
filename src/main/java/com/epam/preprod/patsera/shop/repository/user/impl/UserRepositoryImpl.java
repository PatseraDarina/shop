package com.epam.preprod.patsera.shop.repository.user.impl;

import com.epam.preprod.patsera.shop.bean.User;
import com.epam.preprod.patsera.shop.repository.AbstractDao;
import com.epam.preprod.patsera.shop.repository.parser.ResultSetParser;
import com.epam.preprod.patsera.shop.repository.user.UserRepository;
import com.epam.preprod.patsera.shop.repository.util.QueryHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserRepositoryImpl extends AbstractDao<User, String> implements UserRepository {

    public UserRepositoryImpl(ResultSetParser<User> parser) {
        super(parser);
    }

    @Override
    protected PreparedStatement prepareCreateQuery(Connection connection, User entity) throws SQLException {
        String query = QueryHolder.User.CREATE;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, entity.getFirstName());
        preparedStatement.setString(2, entity.getLastName());
        preparedStatement.setString(3, entity.getEmail());
        preparedStatement.setString(4, entity.getPassword());
        preparedStatement.setBoolean(5, entity.isGetNews());
        return preparedStatement;
    }

    private PreparedStatement prepareReadAllQuery(Connection connection) throws SQLException {
        String query = QueryHolder.User.READ_ALL;
        return connection.prepareStatement(query);
    }

    @Override
    protected PreparedStatement prepareReadQuery(Connection connection, String id) throws SQLException {
        String query = QueryHolder.User.READ;
        return connection.prepareStatement(query);
    }

    @Override
    protected PreparedStatement prepareUpdateQuery(Connection connection, User entity) {
        return null;
    }

    @Override
    protected PreparedStatement prepareDeleteQuery(Connection connection, String key) {
        return null;
    }

    @Override
    public List<User> readAll(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = prepareReadAllQuery(connection);
        ResultSet resultSet = preparedStatement.executeQuery();
        return parser.getDBObjectsList(resultSet);
    }

    @Override
    public User getByEmail(Connection connection, String email) throws SQLException {
        PreparedStatement preparedStatement = prepareReadByEmailQuery(connection);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        return parser.getDBObject(resultSet);
    }

    private PreparedStatement prepareReadByEmailQuery(Connection connection) throws SQLException {
        String query = QueryHolder.User.READ_BY_EMAIL;
        return connection.prepareStatement(query);
    }

    @Override
    public User login(Connection connection, String email, String password) throws SQLException {
        PreparedStatement preparedStatement = prepareLoginQuery(connection);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        return parser.getDBObject(resultSet);
    }

    private PreparedStatement prepareLoginQuery(Connection connection) throws SQLException {
        String query = QueryHolder.User.READ_BY_EMAIL_PASSWORD;
        return connection.prepareStatement(query);
    }

}
