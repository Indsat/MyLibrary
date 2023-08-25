package ru.nicholas.library.java.database;

import ru.nicholas.library.java.database.query.query.Query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author Nicholas Alexandrov 18.08.2023
 */
public abstract class SQLConnector implements Connector {

    @Override
    public CompletableFuture<PreparedStatement> createStatement(String query, int keys, Object... objects) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                PreparedStatement ps = getConnection().prepareStatement(query, keys);
                if (objects != null) {
                    for (int i = 0; i < objects.length; i++) {
                        ps.setObject(i + 1, objects[i]);
                    }
                }
                if (objects == null || objects.length == 0) {
                    ps.clearParameters();
                }
                return ps;
            } catch (SQLException e) {
                throw new RuntimeException("Failed to create statement", e);
            }
        });
    }

    @Override
    public int execute(Query query, Object... objects) {
        return execute(query.toString(), objects);
    }

    @Override
    public int execute(String query, Object... objects) {
        Objects.requireNonNull(query, "SQL query must not be null");
        Objects.requireNonNull(objects, "Parameters query must not be null");
        Callable<Integer> callable = () -> {
            try (PreparedStatement ps = createStatement(query, PreparedStatement.RETURN_GENERATED_KEYS, objects).get()) {
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    return (rs.next() ? rs.getInt(1) : -1);
                }
            }
        };

        return handle(callable);
    }

    @Override
    public <T> T executeQuery(Query query, ResponseHandler<ResultSet, T> handler, Object... objects) {
        return executeQuery(query.toString(), handler, objects);
    }

    @Override
    public <T> T executeQuery(String query, ResponseHandler<ResultSet, T> handler, Object... objects) {
        Objects.requireNonNull(query, "SQL query must not be null");
        Objects.requireNonNull(objects, "Parameters query must not be null");
        CompletableFuture<T> future = createStatement(query, PreparedStatement.NO_GENERATED_KEYS, objects)
                .thenApplyAsync(ps -> {
                    try (ResultSet rs = ps.executeQuery()) {
                        return handler.handle(rs);
                    } catch (SQLException e) {
                        throw new RuntimeException("Failed to execute query", e);
                    }
                });
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to execute async query", e);
        }
    }

    @Override
    public <T> T handle(Callable<T> callable) {
        CompletableFuture <T> async = CompletableFuture.supplyAsync(() -> {
            try {
                return callable.call();
            } catch (Exception e) {
                throw new RuntimeException("Failed to execute query", e);
            }
        });
        try {
            return async.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to execute async query", e);
        }
    }
}
