package com.epam.preprod.patsera.shop.repository.util;

public final class QueryHolder {

    private QueryHolder() {
    }

    public static class User {
        public static final String READ_ALL = "SELECT * FROM USER";
        public static final String CREATE = "INSERT INTO USER (FIRSTNAME, LASTNAME, EMAIL, PASSWORD, ISGETNEWS)  VALUES (?, ?, ?, ?, ?)";
        public static final String READ = "SELECT * FROM USER WHERE ID = ?";
        public static final String READ_BY_EMAIL = "SELECT * FROM USER WHERE EMAIL = ?";
        public static final String READ_BY_EMAIL_PASSWORD = "SELECT * FROM USER WHERE EMAIL = ? AND PASSWORD = ?";
    }
}
