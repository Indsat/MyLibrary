package ru.nicholas.bukkit.inventory.service;

import org.bukkit.entity.Player;
import ru.nicholas.bukkit.inventory.BaseInventory;
import ru.nicholas.bukkit.inventory.alternative.PagedInventory;

import java.util.HashMap;
import java.util.Map;

public class InventoryService {

    private static final Map<Player, BaseInventory> cache = new HashMap<>();

    private static final Map<Player, PagedInventory> pagedCache = new HashMap<>();

    public static void cache(Player player, PagedInventory pagedInventory) {

        pagedCache.put(player, pagedInventory);
    }

    public static Map<Player, PagedInventory> getPagedCache() {
        return pagedCache;
    }

    public static void cache(Player player, BaseInventory baseInventory) {
        cache.put(player, baseInventory);
    }

    public static BaseInventory get(Player player) {
        return cache.get(player);
    }

    public static PagedInventory getPaged(Player player) {
        return pagedCache.get(player);
    }

    public static void remove(Player player) {

        if (cache.containsKey(player)) cache.remove(player);
        else if (pagedCache.containsKey(player)) pagedCache.remove(player);
    }

    public static Map<Player, BaseInventory> getCache() {
        return cache;
    }
}
