package ru.nicholas.bukkit.utils.items;

import com.cryptomorin.xseries.XEnchantment;
import com.cryptomorin.xseries.XMaterial;
import com.cryptomorin.xseries.XPotion;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import ru.nicholas.bukkit.inventory.items.DefaultItem;
import ru.nicholas.bukkit.utils.items.deserialize.ItemStackDeserialize;
import ru.nicholas.bukkit.utils.items.deserialize.PotionDeserialize;
import ru.nicholas.bukkit.utils.items.deserialize.SkullDeserialize;
import ru.nicholas.bukkit.utils.items.serialize.ItemStackSerialize;
import ru.nicholas.bukkit.utils.items.serialize.PotionEffectSerialize;
import ru.nicholas.bukkit.utils.items.serialize.PotionMetaSerialize;
import ru.nicholas.core.VersionAdapter;
import ru.nicholas.villagers.files.InventoryFile;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Nicholas Alexandrov 01.06.2023
 */
public class ItemUtil {

    public static Object getItem(String name) {
        try {
            Optional<XMaterial> optional = XMaterial.matchXMaterial(name.toUpperCase());
            if (optional.isPresent()) {
                return optional.get();
            }
        } catch (IllegalArgumentException e) {
            // не Material
        }
        try {
            Optional<XEnchantment> optional = XEnchantment.matchXEnchantment(name.toUpperCase());
            if (optional.isPresent()) {
                return optional.get();
            }
        } catch (IllegalArgumentException e) {
            // не Enchantment
        }
        try {
            Optional<XPotion> optional = XPotion.matchXPotion(name);
            if (optional.isPresent()) {
                return optional.get().getPotionEffectType();
            }
        } catch (IllegalArgumentException e) {
            // не Effect
        }
        throw new NullPointerException("Can't take type of item for quest task: " + name);
    }

    public static String potionEffectSerialize(PotionEffect potionEffect) {
        return new PotionEffectSerialize().serialize(potionEffect);
    }

    public static String itemStackSerialize(ItemStack itemStack) {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(new ItemStackSerialize().serialize(itemStack));

        if (itemStack.getType() == Material.POTION) {

            PotionMeta potionMeta = (PotionMeta) Objects.requireNonNull(itemStack.getItemMeta());

            stringBuilder.append(new PotionMetaSerialize().serialize(potionMeta));
        }

        return stringBuilder.toString();
    }

    public static ItemStack itemStackDeserialize(String string) {
        String[] data = string.split(" ");
        ItemStack itemStack = new ItemStack(Material.BARRIER);

        for (String str : data) {
            String[] split = str.split(":");
            Object item = getItem(split[0]);

            if (item instanceof Material) {
                Material material = (Material) item;

                if (material == XMaterial.PLAYER_HEAD.parseMaterial()) {
                    itemStack = new SkullDeserialize().deserialize(str);

                } else {
                    itemStack = new ItemStackDeserialize().deserialize(str);
                }

            } else if (item instanceof Enchantment) {
                Enchantment enchantment = (Enchantment) item;
                int level = Integer.parseInt(split[2]);
                itemStack.addUnsafeEnchantment(enchantment, level);

            } else if (item instanceof PotionEffectType) {
                if (itemStack.getType() == Material.POTION) {
                    List<PotionEffect> potionEffectList = new PotionDeserialize().deserialize(string);
                    PotionMeta potionMeta = (PotionMeta) itemStack.getItemMeta();
                    Objects.requireNonNull(potionMeta, "PotionMeta is null");

                    for (PotionEffect potionEffect : potionEffectList) {
                        potionMeta.addCustomEffect(potionEffect, true);
                    }
                }
            }
        }
        return itemStack;
    }

    public static boolean hasEnchants(ItemStack itemStack) {

        return getEnchants(itemStack).isEmpty();
    }

    public static Map<Enchantment, Integer> getEnchants(ItemStack itemStack) {

        return itemStack.getEnchantments();
    }
}
