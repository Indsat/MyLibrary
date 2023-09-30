package ru.nicholas.library.bukkit.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import ru.nicholas.library.bukkit.file.FileOperations;
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
}
