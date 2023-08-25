package ru.nicholas.library.bukkit.inventory;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import ru.nicholas.library.bukkit.inventory.buttons.Button;
import ru.nicholas.library.bukkit.inventory.click.Click;
import ru.nicholas.library.bukkit.inventory.info.InventoryInfo;
import ru.nicholas.library.bukkit.inventory.service.InventoryService;

import java.util.Map;

public interface BaseInventory extends InventoryHolder {

    void addFrameSlot(int slot);

    InventoryInfo getInfo();

    default void openInventory(Player player) {
        generateInventory(player);
        setupItems();
        frame(player);
        player.openInventory(getInventory());
        InventoryService.cache(player, this);
    }

    void setItem(int slot, Button button) throws IllegalArgumentException ;

    default void setItem(int slot, ItemStack itemStack) {
        Button button = new Button(itemStack, (player, type) -> {});
        try {
            setItem(slot, button);
        } catch (IllegalArgumentException e) {
            Bukkit.getLogger().warning("Слот " + slot + "не является допустимым");
        }
    }

    void generateInventory(Player player);

    void setupItems();

    default void setItem(int slot, ItemStack itemStack, Click clickable) {

        Button button = new Button(itemStack, clickable);

        try {
            setItem(slot, button);
        } catch (IllegalArgumentException e) {
            System.out.println("Слот " + slot + "не является допустимым");
        }
    }

    Button getButton(int slot);

    Map<Integer, Button> getButtons();

    void close(Player player);

    void clear();

    void update(Player player);

    void frame(Player player);

    FileConfiguration getFileConfiguration();
}
