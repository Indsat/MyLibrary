package ru.nicholas.library.paper;

import ru.nicholas.library.core.util.SkullUtils;
import ru.nicholas.library.paper.utils.PaperSkullUtil;
import ru.nicholas.library.spigot.SpigotAdapter;

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
