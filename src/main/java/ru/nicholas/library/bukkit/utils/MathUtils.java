package ru.nicholas.library.bukkit.utils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Nicholas Alexandrov 18.06.2023
 */
public class MathUtils {

    public static double randomDouble(double min, double max) {

        return ThreadLocalRandom.current().nextDouble(min, max + 1);
    }
}
