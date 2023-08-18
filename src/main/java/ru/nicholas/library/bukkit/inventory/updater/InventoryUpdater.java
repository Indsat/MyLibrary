package ru.nicholas.bukkit.inventory.updater;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import ru.nicholas.bukkit.inventory.BaseInventory;
import ru.nicholas.bukkit.inventory.alternative.PagedInventory;
import ru.nicholas.bukkit.inventory.service.InventoryService;

import java.util.Map;

public class InventoryUpdater extends BukkitRunnable {

    @Override
    public void run() {

        Map<Player, BaseInventory> map = InventoryService.getCache();

        for (Map.Entry<Player, BaseInventory> entry : map.entrySet()) {

            entry.getValue().update(entry.getKey());
        }

        Map<Player, PagedInventory> pagedMap = InventoryService.getPagedCache();

        for (Map.Entry<Player, PagedInventory> entry : pagedMap.entrySet()) {

            entry.getValue().update(entry.getKey());
        }
    }
}
