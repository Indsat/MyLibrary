package ru.nicholas.bukkit.utils.items.deserialize;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import ru.nicholas.bukkit.utils.VersionsUtil;

/**
 * Класс для десериализации текста в предметы
 *
 * @author Nicholas Alexandrov 03.06.2023
 */
public class ItemStackDeserialize implements StringDeserialize<ItemStack> {

    @Override
    public ItemStack deserialize(String str) {
        String[] array = str.split(" ");
        ItemStack itemStack = new ItemStack(Material.valueOf(array[0].split(":")[0]), Integer.parseInt(array[1]));
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;

        if (itemMeta instanceof Damageable) {
            ((Damageable) itemMeta).setDamage(Integer.parseInt(array[0].split(":")[1]));
        }

        if (VersionsUtil.getServerVersion().isNewerThan(VersionsUtil.ServerVersion.v1_14)) {
            if (array[0].split(":").length > 2) {
                itemMeta.setCustomModelData(Integer.parseInt(array[0].split(":")[2]));
            }
        }

        if (itemMeta instanceof PotionMeta) {
            PotionMeta potionMeta = (PotionMeta) itemMeta;

            for (int i = 1; i < array.length; i++) {
                String[] potionData = array[i].split(":");
                if (potionData.length == 3) {
                    PotionEffectType potionEffectType = PotionEffectType.getByName(potionData[0]);
                    int duration = Integer.parseInt(potionData[1]);
                    int amplifier = Integer.parseInt(potionData[2]);
                    assert potionEffectType != null;
                    potionMeta.addCustomEffect(new PotionEffect(potionEffectType, duration, amplifier), true);
                }
            }
        }

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
