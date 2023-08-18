package ru.nicholas.paper;

import ru.nicholas.core.util.SkullUtils;
import ru.nicholas.paper.utils.PaperSkullUtil;
import ru.nicholas.spigot.SpigotAdapter;

/**
 * @author Nicholas Alexandrov 27.06.2023
 */
public class PaperAdapter extends SpigotAdapter {

    private final SkullUtils skullUtils;


    {

        skullUtils = new PaperSkullUtil();

    }

    @Override
    public SkullUtils SkullUtils() {
        return skullUtils;
    }

}
