package ru.nicholas.library.spigot.builder.v1_15;

import ru.nicholas.library.core.builder.ItemBuilder;
import ru.nicholas.library.spigot.builder.v1_12.SpigotItemBuilder_v1_12;

/**
 * Класс для создания предметов для версии 1.15
 *
 * @author Nicholas Alexandrov 26.05.2023
 */
public class SpigotItemBuilder_v1_15 extends SpigotItemBuilder_v1_12 {

    @Override
    public ItemBuilder setCustomModelData(int data) {

        if (data != -1) {

            super.itemMeta.setCustomModelData(data);
        }

        return this;
    }
}
