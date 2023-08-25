package ru.nicholas.library.spigot.builder.v1_16;

import org.bukkit.Color;
import ru.nicholas.library.core.builder.PotionEffectBuilder;
import ru.nicholas.library.spigot.builder.v1_13.SpigotPotionEffectBuilder_v1_13;


/**
 * Класс-билдер для создания эффектов зелий для версии 1.16
 *
 * @author Nicholas Alexandrov 01.06.2023
 */
public class SpigotPotionEffectBuilder_v1_16 extends SpigotPotionEffectBuilder_v1_13 {

    @Deprecated
    @Override
    public PotionEffectBuilder setColor(Color color) {
        return this;
    }

}
