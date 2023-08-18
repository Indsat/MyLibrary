package ru.nicholas.spigot.builder.v1_8;

import com.cryptomorin.xseries.XPotion;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import ru.nicholas.core.builder.PotionEffectBuilder;

import java.util.Optional;

/**
 * Класс-билдер для создания эффектов зелий для 1.8
 *
 * @author Nicholas Alexandrov 01.06.2023
 */
public class SpigotPotionEffectBuilder_v1_8 implements PotionEffectBuilder {

    private boolean ambient;

    private int amplifier;

    private int duration;

    private boolean particles;

    private PotionEffectType type;

    @Override
    public PotionEffectBuilder setAmbient(boolean bool) {

        this.ambient = bool;

        return this;
    }

    @Override
    public PotionEffectBuilder setAmplifier(int amplifier) {

        this.amplifier = amplifier;

        return this;
    }

    @Override
    public PotionEffectBuilder setDuration(int seconds) {

        this.duration = seconds;

        return this;
    }

    @Deprecated
    @Override
    public PotionEffectBuilder setIcon(boolean icon) {

        return this;
    }

    @Override
    public PotionEffectBuilder setParticles(boolean particles) {

        this.particles = particles;

        return this;
    }

    @Override
    public PotionEffectBuilder setType(PotionEffectType type) {

        this.type = type;

        return this;
    }

    @Override
    public PotionEffectBuilder setType(String type) {

        Optional<XPotion> optional = XPotion.matchXPotion(type);

        optional.ifPresent(xPotion -> this.type = xPotion.getPotionEffectType());

        return this;
    }

    @Deprecated
    @Override
    public PotionEffectBuilder setColor(Color color) {

        return this;
    }

    @Override
    public PotionEffect build() {

        return new PotionEffect(type, duration, amplifier, ambient, particles);
    }
}
