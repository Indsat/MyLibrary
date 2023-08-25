package ru.nicholas.library.spigot.utils.universal;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import ru.nicholas.library.bukkit.utils.VersionsUtil;

import java.lang.reflect.Field;

/**
 * @author Nicholas Alexandrov 17.06.2023
 */
public class SpigotInventoryUtil {

    private final Field inventory;

    {

        try {

            Class<?> craftInventory = Class.forName("org.bukkit.craftbukkit." + VersionsUtil.SERVER_VERSION + ".inventory.CraftInventory");

            this.inventory = craftInventory.getDeclaredField("inventory");

        } catch (ClassNotFoundException | NoSuchFieldException e) {

            throw new RuntimeException(e);
        }

    }

    public Inventory createInventory(InventoryHolder holder, int rows, String title) {

        return Bukkit.createInventory(holder, rows, title);
    }

    public boolean equals(Inventory one, Inventory two) {

        try {

            return inventory.get(one) == inventory.get(two);

        } catch (IllegalAccessException ignore) {

            return false;
        }
    }
}
