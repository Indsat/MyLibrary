package ru.nicholas.java.database.sqlite;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Класс для подключения к базе данных SQLite
 *
 * @author Nicholas Alexandrov 13.06.2023
 */
public class SQLiteConnector {

    private final Connection connection;

    private final Plugin plugin;

    {

        this.plugin = Bukkit.getPluginManager().getPlugin("UltimateAnarchyRegions");
    }

    public SQLiteConnector() {

        this.connection = connect();
    }

    /**
     *
     * Подключение к базе данных
     *
     * @return подключение
     */

    private Connection connect() {

        Connection connection = null;

        try {

            String url = "jdbc:sqlite:" + plugin.getDataFolder() + File.separator + "data.db";

            connection = DriverManager.getConnection(url);

        } catch (SQLException e) {

            System.out.println("An error occurred while connecting to SQLite database: " + e.getSQLState());
        }

        return connection;
    }

    public void close() {

        if (isConnected()) {

            try {

                connection.close();

            } catch (SQLException e) {

                System.out.println("An error occurred while disconnect to SQLite database: " + e.getSQLState());
            }
        }
    }

    public boolean isConnected() {

        return connection != null;
    }

    public Connection getConnection() {
        return connection;
    }
}
