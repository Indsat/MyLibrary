package ru.nicholas.library.core.providers;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

/**
 * @author Nicholas Alexandrov 28.07.2023
 */
public class ProviderService {

    private static VaultProvider vaultProvider;

    public static void load() {

        if (Bukkit.getPluginManager().isPluginEnabled("Vault")) {
            Plugin plugin = Bukkit.getPluginManager().getPlugin("Vault");
            String version = plugin.getDescription().getVersion();
            if (version.compareTo("1.7") >= 0) {
                Bukkit.getLogger().info("Vault found. use Vault API");
                vaultProvider = new VaultProvider();
                vaultProvider.setupEconomy();
                vaultProvider.setupPermission();
                vaultProvider.setupChat();
            } else {
                Bukkit.getLogger().warning("Vault version < 1.7 not supported. Ignore this dependence.");
            }
        }
    }

    public static VaultProvider getVaultProvider() {
        if (vaultProvider != null)
            return vaultProvider;
        else throw new NullPointerException("Can't invoke Vault dependency");
    }
}
