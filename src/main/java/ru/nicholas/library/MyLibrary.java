package ru.nicholas.library;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import ru.nicholas.library.bukkit.inventory.listeners.InventoryListener;
import ru.nicholas.library.bukkit.inventory.updater.InventoryUpdater;
import ru.nicholas.library.core.providers.ProviderService;

/**
 * @author Nicholas Alexandrov 06.08.2023
 */
public class MyLibrary extends JavaPlugin {

    private static MyLibrary library;

    private InventoryUpdater inventoryUpdater;

    public void onEnable() {
        library = this;
        ProviderService.load();
        Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);
        this.inventoryUpdater = new InventoryUpdater();
        inventoryUpdater.runTaskTimerAsynchronously(this, 1L, 10L);
    }

    public void onDisable() {
        inventoryUpdater.cancel();
    }

    public static MyLibrary getLibrary() {
        return library;
    }
}
