package ru.nicholas.library;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import ru.nicholas.library.core.providers.ProviderService;
import ru.nicholas.library.spigot.listener.PlayerPickupExperienceListener;

/**
 * @author Nicholas Alexandrov 06.08.2023
 */
public class MyLibrary extends JavaPlugin {

    private static MyLibrary library;

    public void onEnable() {
        library = this;
        ProviderService.load();
        Bukkit.getPluginManager().registerEvents(new PlayerPickupExperienceListener(), this);
    }

    public static MyLibrary getLibrary() {
        return library;
    }
}
