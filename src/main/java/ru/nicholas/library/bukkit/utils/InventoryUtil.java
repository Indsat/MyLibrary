package ru.nicholas.library.bukkit.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import ru.nicholas.library.bukkit.file.FileOperations;
import ru.nicholas.library.bukkit.inventory.items.CustomItem;
import ru.nicholas.library.core.VersionAdapter;
import ru.nicholas.library.java.text.ReplaceData;

import java.util.ArrayList;
import java.util.List;

public class InventoryUtil {

    public static ItemStack createItem(FileConfiguration fileConfiguration, OfflinePlayer offlinePlayer, String path, ReplaceData... replaceData) {
        ItemStack itemStack;
        String materialName = FileOperations.getString(fileConfiguration, path + ".item.material");
        String url = fileConfiguration.getString(path + ".item.head");
        String displayName = PlaceholderAPI.setPlaceholders(offlinePlayer, FileOperations.getString(fileConfiguration, path + ".displayName", replaceData));
        List<String> lore = PlaceholderAPI.setPlaceholders(offlinePlayer, FileOperations.getList(fileConfiguration, path + ".lore", replaceData));
        int customModelData = fileConfiguration.getInt(path + ".customModelData", 0);

        if (materialName.equalsIgnoreCase("head")) {
            itemStack = VersionAdapter.getSkullBuilder()
                    .setDisplayName(displayName)
                    .setLore(lore)
                    .setTexture(url)
                    .build();

        } else {
            itemStack  = VersionAdapter.getItemBuilder()
                    .setType(materialName)
                    .setDisplayName(displayName)
                    .setLore(lore)
                    .setCustomModelData(customModelData)
                    .build();
        }

        return itemStack;
    }

    public static List<CustomItem> loadCustomItems(FileConfiguration fileConfiguration, Player player, String path, ReplaceData... replacesData) {
        ConfigurationSection configurationSection = fileConfiguration.getConfigurationSection(path);
        List<CustomItem> customItems = new ArrayList<>();

        for (String s : configurationSection.getKeys(false)) {
            String type = configurationSection.getString(s + ".type");
            if (!type.equalsIgnoreCase("special")) {
                String displayName = PlaceholderAPI.setPlaceholders(player, VersionAdapter.TextUtil().colorize(configurationSection.getString(s + ".displayName")));
                String material = configurationSection.getString(s + ".item.material");
                String url = configurationSection.getString(s + ".item.head");
                List<String> lore = PlaceholderAPI.setPlaceholders(player, VersionAdapter.TextUtil().colorize(configurationSection.getStringList( s + ".lore")));
                List<String> commands = configurationSection.getStringList(s + ".commands");
                int customModeData = configurationSection.getInt(s + ".customModelData", 0);
                List<Integer> slots = configurationSection.getIntegerList(s + ".slots");
                String action = configurationSection.getString(s + ".action", "");
                String permission = configurationSection.getString(s + ".permission", "");
                ItemStack itemStack;

                lore = VersionAdapter.TextUtil().setReplaces(lore, replacesData);

                if (material.equalsIgnoreCase("head")) {
                    itemStack = VersionAdapter.getSkullBuilder()
                            .setTexture(url)
                            .setLore(lore)
                            .setDisplayName(displayName)
                            .addFlag(ItemFlag.HIDE_ENCHANTS)
                            .addFlag(ItemFlag.HIDE_DESTROYS)
                            .addFlag(ItemFlag.HIDE_ATTRIBUTES)
                            .build();
                } else {
                    itemStack = VersionAdapter.getItemBuilder()
                            .setType(material)
                            .setDisplayName(displayName)
                            .setLore(lore)
                            .addFlag(ItemFlag.HIDE_ENCHANTS)
                            .addFlag(ItemFlag.HIDE_DESTROYS)
                            .addFlag(ItemFlag.HIDE_ATTRIBUTES)
                            .build();
                }
                CustomItem customItem = new CustomItem(s, action, commands, permission, slots, itemStack, customModeData, null);
                customItems.add(customItem);
            }
        }
        return customItems;
    }
}
