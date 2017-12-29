package com.epam.preprod.patsera.shop.repository.parser.impl;

import com.epam.preprod.patsera.shop.bean.User;
import com.epam.preprod.patsera.shop.repository.parser.ResultSetParser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserParser implements ResultSetParser<User> {
    @Override
    public User getDBObject(ResultSet resultSet) throws SQLException {
        User user = null;
        if (resultSet.next()) {
            user = getUser(resultSet);
        }
        return user;
    }

    @Override
    public List<User> getDBObjectsList(ResultSet resultSet) throws SQLException {
        List<User> userList = new ArrayList<>();
        while (resultSet.next()) {
            userList.add(getUser(resultSet));
        }
        return userList;
    }

    private User getUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(String.valueOf(resultSet.getInt(1)));
        user.setFirstName(resultSet.getString(2));
        user.setLastName(resultSet.getString(3));
        user.setEmail(resultSet.getString(4));
        user.setPassword(resultSet.getString(5));
        user.setGetNews(resultSet.getBoolean(6));

        return user;
    }
}
