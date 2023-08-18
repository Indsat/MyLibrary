package ru.nicholas.bukkit.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class PlayerUtil {

    public static void giveItem(Player player, ItemStack itemStack) {

        if (player.getInventory().firstEmpty() == -1)

            Objects.requireNonNull(Bukkit.getWorld(player.getWorld().getName())).dropItem(player.getLocation(), itemStack);
        else
            player.getInventory().addItem(itemStack);

        player.updateInventory();
    }
}
