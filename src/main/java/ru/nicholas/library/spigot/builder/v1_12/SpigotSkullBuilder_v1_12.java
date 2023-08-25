package ru.nicholas.library.spigot.builder.v1_12;

import ru.nicholas.library.core.builder.SkullBuilder;
import ru.nicholas.library.spigot.builder.v1_8.SpigotSkullBuilder_v1_8;

/**
 *
 * Класс для создания голов для версии 1.15
 *
 * @author Nicholas Alexandrov 27.05.2023
 */
public class SpigotSkullBuilder_v1_12 extends SpigotSkullBuilder_v1_8 {

    @Override
    public SkullBuilder setUnbreakable(boolean bool) {

        super.skullMeta.setUnbreakable(bool);

        return this;
    }
}
