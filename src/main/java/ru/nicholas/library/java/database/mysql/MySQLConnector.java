package ru.nicholas.java.database.mysql;

import ru.nicholas.java.database.Connector;
import ru.nicholas.moontime.clans.file.ConfigFile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Nicholas Alexandrov 26.07.2023
 */
public class MySQLConnector implements Connector {

    private final Connection connection;

    public MySQLConnector() {

        this.connection = connect();
    }

    /**
     *
     * Подключение к базе данных
     *
     * @return подключение
     */

    @Override
    public Connection connect() {

        Connection connection = null;

        try {

            String url = "jdbc:mysql//" + ConfigFile.MySQL_URL;

            connection = DriverManager.getConnection(url, ConfigFile.MySQL_USER, ConfigFile.MySQL_PASSWORD);

        } catch (SQLException e) {

            System.out.println("An error occurred while connecting to SQLite database: " + e.getSQLState());
        }

        return connection;
    }

    @Override
    public void close() {

        if (isConnected()) {

            try {

                connection.close();

            } catch (SQLException e) {

                System.out.println("An error occurred while disconnect to SQLite database: " + e.getSQLState());
            }
        }
    }

    @Override
    public boolean isConnected() {

        return connection != null;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}
