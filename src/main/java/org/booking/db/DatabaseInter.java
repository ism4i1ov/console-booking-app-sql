package org.booking.db;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface DatabaseInter<T> {

    default Connection connection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "12345";
        return DriverManager.getConnection(url, username, password);
    }

    int add(T t);

    boolean remove(String id);

    List<T> getAll();

    Optional<T> getById(String id);

    boolean update(T t);
}
