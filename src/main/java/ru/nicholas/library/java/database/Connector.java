package ru.nicholas.library.java.database;

import ru.nicholas.library.java.database.query.query.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

/**
 * @author Nicholas Alexandrov 26.07.2023
 */
public interface Connector {

    Connection connect();

    void close();

    boolean isConnected();

    Connection getConnection();

    CompletableFuture<PreparedStatement> createStatement(String query, int keys, Object... objects);

    int execute(Query query, Object... objects);

    int execute(String query, Object... objects);

    <T> T executeQuery(Query query, ResponseHandler<ResultSet, T> handler, Object... objects);

    <T> T executeQuery(String query, ResponseHandler<ResultSet, T> handler, Object... objects);

    <T> T handle(Callable<T> callable);
}
