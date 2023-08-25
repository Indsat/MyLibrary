package ru.nicholas.library.spigot.builder.v1_12;


import ru.nicholas.library.core.builder.ItemBuilder;
import ru.nicholas.library.spigot.builder.v1_8.SpigotItemBuilder_v1_8;

/**
 * Класс для создания предметов для версии 1.12
 *
 * @author Nicholas Alexandrov 26.05.2023
 */
public class SpigotItemBuilder_v1_12 extends SpigotItemBuilder_v1_8 {

    @Override
    public ItemBuilder setUnbreakable(boolean bool) {

        super.itemMeta.setUnbreakable(bool);

        return this;
    }
}
