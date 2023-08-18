package ru.nicholas.bukkit.utils.items.deserialize;

import com.cryptomorin.xseries.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;

import java.util.Arrays;

/**
 * @author Nicholas Alexandrov 29.07.2023
 */
public class QuestDeserialize implements StringDeserialize<Object> {

    @Override
    public Object deserialize(String str) {
        String[] data = str.split(";");

        for (String objectSplit : data) {
            String[] questData = objectSplit.split("\\*");
            String obj = questData[0];
            if (Bukkit.getWorld(obj) != null) {
                return Bukkit.getWorld(obj);
            } else if (Arrays.stream(EntityType.values()).anyMatch(e -> e.name().equals(obj))) {
                return EntityType.valueOf(obj);
            } else if (XMaterial.matchXMaterial(obj).isPresent()) {
                return new ItemStackDeserialize().deserialize(obj);
            } else if (obj.startsWith("PotionEffect:")) {
                return new PotionEffectDeserialize().deserialize(obj);
            } else {
                return obj;
            }
        }
        throw new IllegalArgumentException("Не удалось определить тип " + str);
    }
}
