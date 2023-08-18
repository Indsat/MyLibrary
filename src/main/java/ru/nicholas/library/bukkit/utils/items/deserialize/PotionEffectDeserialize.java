package ru.nicholas.bukkit.utils.items.deserialize;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;

/**
 * @author Nicholas Alexandrov 18.07.2023
 */
public class PotionEffectDeserialize implements StringDeserialize<PotionEffect> {

    @Override
    public PotionEffect deserialize(String str) {

        String[] data = str.split(":");

        return new PotionEffect(Objects.requireNonNull(PotionEffectType.getByName(data[1])), Integer.parseInt(data[2]), Integer.parseInt(data[3]));
    }
}
