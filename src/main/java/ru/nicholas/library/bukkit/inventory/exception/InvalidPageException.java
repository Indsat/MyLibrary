package ru.nicholas.library.bukkit.inventory.exception;

/**
 * @author Nicholas Alexandrov 23.07.2023
 */
public class InvalidPageException  extends RuntimeException {

    public InvalidPageException(int page) {
        super("Неверное значение страницы: " + page);
    }
}
