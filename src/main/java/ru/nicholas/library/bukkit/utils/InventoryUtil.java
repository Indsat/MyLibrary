package ru.nicholas.bukkit.utils;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import ru.nicholas.bukkit.inventory.items.CustomItem;
import ru.nicholas.core.VersionAdapter;
import ru.nicholas.villagers.files.InventoryFile;

import java.util.ArrayList;
import java.util.List;

public class InventoryUtil {

    public static List<CustomItem> loadCustomItems(String path) {

        ConfigurationSection configurationSection = InventoryFile.getConfig().getConfigurationSection(path);

        List<CustomItem> customItems = new ArrayList<>();

        for (String s : configurationSection.getKeys(false)) {

            String type = configurationSection.getString(s + ".type");

            if (!type.equalsIgnoreCase("special")) {

                String displayName = InventoryFile.getString(path + "." + s + ".displayName");

                String material = configurationSection.getString(path + "." + s + ".item.material");

                String url = configurationSection.getString(path + "." + s + ".item.head");

                List<String> lore = InventoryFile.getList(path + "." + s + ".lore");

                List<String> commands = configurationSection.getStringList(path + "." + s + ".commands");

                int customModeData = configurationSection.getInt(path + "." + s + ".customModelData", 0);

                List<Integer> slots = configurationSection.getIntegerList(path + "." + s + ".slots");

                String action = configurationSection.getString(path + "." + s + ".action", "");

                String permission = configurationSection.getString(path + "." + s + ".permission", "");

                ItemStack itemStack;

                if (material.equalsIgnoreCase("head")) {

                    itemStack = VersionAdapter.getSkullBuilder()
                            .setTexture(url)
                            .setLore(lore)
                            .setDisplayName(displayName)
                            .build();

                } else {

                    itemStack = VersionAdapter.getItemBuilder()
                            .setType(material)
                            .setDisplayName(displayName)
                            .setLore(lore)
                            .build();
                }

                CustomItem customItem = new CustomItem(s, action, commands, permission, slots, itemStack, customModeData, null);

                customItems.add(customItem);
            }
        }

        return customItems;
    }
}
