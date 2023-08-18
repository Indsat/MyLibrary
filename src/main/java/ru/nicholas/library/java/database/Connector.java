package ru.nicholas.java.database;

import java.sql.Connection;

/**
 * @author Nicholas Alexandrov 26.07.2023
 */
public interface Connector {

    Connection connect();

    void close();

    boolean isConnected();

    Connection getConnection();
}
