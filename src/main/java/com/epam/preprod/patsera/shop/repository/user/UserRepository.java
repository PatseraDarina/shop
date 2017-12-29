package com.epam.preprod.patsera.shop.repository.user;

import com.epam.preprod.patsera.shop.bean.User;
import com.epam.preprod.patsera.shop.repository.GenericDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface UserRepository extends GenericDao<User, String> {
    List<User> readAll(Connection connection) throws SQLException;
    User getByEmail(Connection connection, String email) throws SQLException;
    User login(Connection connection, String email, String password) throws SQLException;
}
