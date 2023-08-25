package ru.nicholas.library.spigot.builder.v1_13;

import org.bukkit.Color;
import org.bukkit.potion.PotionEffect;
import ru.nicholas.library.core.builder.PotionEffectBuilder;
import ru.nicholas.library.spigot.builder.v1_12.SpigotPotionEffectBuilder_v1_12;

/**
 * @author Nicholas Alexandrov 25.08.2023
 */
public class SpigotPotionEffectBuilder_v1_13 extends SpigotPotionEffectBuilder_v1_12 {

    private boolean icon;

    @Deprecated
    @Override
    public PotionEffectBuilder setColor(Color color) {
        return this;
    }

    @Override
    public PotionEffectBuilder setIcon(boolean icon) {
        this.icon = icon;
        return this;
    }

    public PotionEffect build() {
        return new PotionEffect(super.type, super.duration, super.amplifier, super.ambient, super.particles, icon);
    }

}
