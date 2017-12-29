package com.epam.preprod.patsera.shop.repository.parser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ResultSetParser<T> {
    /**
     * Gets data from returned {@code ResultSet} and transform
     * them into Java object.
     *
     * @return instance of class that was used instead of
     *         template {@code T}. An instance will consist of
     *         date from database table.
     */
    T getDBObject(ResultSet resultSet) throws SQLException;

    /**
     * Gets a list of objects that consist of data
     * from database table.
     *
     * @return list of instance of class that was used
     *         instead of template {@code T}.
     */
    List<T> getDBObjectsList(ResultSet resultSet) throws SQLException;
}

