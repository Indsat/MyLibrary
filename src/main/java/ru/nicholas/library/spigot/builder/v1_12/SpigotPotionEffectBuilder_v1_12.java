package ru.nicholas.spigot.builder.v1_12;

import com.cryptomorin.xseries.XPotion;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import ru.nicholas.core.builder.PotionEffectBuilder;
import ru.nicholas.spigot.utils.universal.SpigotPotionEffectUtil;

import java.util.Optional;

/**
 * @author Nicholas Alexandrov 17.06.2023
 */
public class SpigotPotionEffectBuilder_v1_12 implements PotionEffectBuilder {

    private boolean ambient;

    private int amplifier;

    private int duration;

    private boolean particles;

    private PotionEffectType type;

    private Color color;

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

        this.duration = seconds * 20;

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

    @Override
    public PotionEffectBuilder setColor(Color color) {

        this.color = color;

        return this;
    }

    @Override
    public PotionEffect build() {

        return SpigotPotionEffectUtil.newPotionEffect(type, duration, amplifier, ambient, particles, color);
    }
}
