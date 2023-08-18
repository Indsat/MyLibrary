package ru.nicholas.spigot.builder.v1_16;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import ru.nicholas.core.builder.ItemBuilder;
import ru.nicholas.spigot.builder.v1_15.SpigotItemBuilder_v1_15;

/**
 * Класс для создания предметов для версии 1.16
 *
 * @author Nicholas Alexandrov 26.05.2023
 */
public class SpigotItemBuilder_v1_16 extends SpigotItemBuilder_v1_15 {

    @Override
    public ItemBuilder setDyeColor(DyeColor dyeColor) {

        super.itemStack.setType(Material.valueOf(dyeColor.name() + "_DYE"));

        return this;
    }

    @Deprecated
    @Override
    public ItemBuilder setDyeColor(byte dyeColor) {
        return this;
    }
}
