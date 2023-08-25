package ru.nicholas.library.bukkit.inventory.click;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public interface Click {

    void click(Player player, InventoryClickEvent event);
}
