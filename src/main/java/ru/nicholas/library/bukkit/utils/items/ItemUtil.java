package ru.nicholas.library.bukkit.utils.items;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import ru.nicholas.library.bukkit.utils.items.deserialize.ItemStackDeserialize;
import ru.nicholas.library.bukkit.utils.items.serialize.ItemStackSerialize;
import ru.nicholas.library.bukkit.utils.items.serialize.PotionEffectSerialize;

import java.util.Map;

/**
 * @author Nicholas Alexandrov 01.06.2023
 */
public class ItemUtil {

    public static String potionEffectSerialize(PotionEffect potionEffect) {
        return new PotionEffectSerialize().serialize(potionEffect);
    }

    public static String itemStackSerialize(ItemStack itemStack) {
        return new ItemStackSerialize().serialize(itemStack);
    }

    public static ItemStack itemStackDeserialize(String string) {
        return new ItemStackDeserialize().deserialize(string);
    }

    public static boolean hasEnchants(ItemStack itemStack) {

        return getEnchants(itemStack).isEmpty();
    }

    public static Map<Enchantment, Integer> getEnchants(ItemStack itemStack) {

        return itemStack.getEnchantments();
    }
}
