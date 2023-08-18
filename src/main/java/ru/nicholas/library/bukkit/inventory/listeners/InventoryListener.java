package ru.nicholas.bukkit.inventory.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import ru.nicholas.bukkit.inventory.BaseInventory;
import ru.nicholas.bukkit.inventory.alternative.PagedInventory;
import ru.nicholas.bukkit.inventory.buttons.Button;
import ru.nicholas.bukkit.inventory.service.InventoryService;

public class InventoryListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();

        int slot = e.getSlot();

        if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) {
            return;
        }

        if (InventoryService.getPaged(player) != null) {
            PagedInventory pagedInventory = InventoryService.getPaged(player);
            e.setCancelled(true);
            BaseInventory baseInventory = pagedInventory.getInventory();
            if (baseInventory.getButtons().containsKey(slot)) {
                Button button = baseInventory.getButton(slot);
                if (button != null) button.getClick().click(player, e);
                e.setCancelled(true);
                return;
            }
            if (pagedInventory.getSlots().contains(slot)) {
                Button button = pagedInventory.getButton(slot);
                if (button != null) button.getClick().click(player, e);
                e.setCancelled(true);
            }

        } else if (InventoryService.get(player) != null) {
            BaseInventory inventory = InventoryService.get(player);
            Button button = inventory.getButton(slot);
            if (button != null) {
                button.getClick().click(player, e);
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        Player player = (Player) e.getPlayer();

        BaseInventory inventory = InventoryService.get(player);

        PagedInventory pagedInventory = InventoryService.getPaged(player);

        if (pagedInventory != null) {
            pagedInventory.close(player);
        } else if (inventory != null) {
            inventory.close(player);
        }
    }
}
