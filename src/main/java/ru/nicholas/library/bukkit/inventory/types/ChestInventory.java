package ru.nicholas.library.bukkit.inventory.types;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import ru.nicholas.library.bukkit.inventory.BaseInventory;
import ru.nicholas.library.bukkit.inventory.buttons.Button;
import ru.nicholas.library.bukkit.inventory.info.InventoryInfo;
import ru.nicholas.library.bukkit.inventory.service.InventoryService;
import ru.nicholas.library.bukkit.utils.InventoryUtil;
import ru.nicholas.library.core.VersionAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Nicholas Alexandrov 02.06.2023
 */
public abstract class ChestInventory implements BaseInventory {

    private final Inventory inventory; // Инвентарь Bukkit
    private final InventoryInfo info; // Информация об инвентаре
    private final Map<Integer, Button> buttons; // список кнопок в инвентаре
    private final List<Integer> frameSlots; // слоты являющиеся заполнителями
    private final FileConfiguration fileConfiguration;

    public ChestInventory(FileConfiguration fileConfiguration, String title, int rows) {
        this.inventory = VersionAdapter.InventoryUtils().createInventory(null, rows * 9, title);
        this.info = new InventoryInfo(VersionAdapter.TextUtil().colorize(title), rows * 9);
        this.buttons = new HashMap<>();
        this.frameSlots = new ArrayList<>();
        this.fileConfiguration = fileConfiguration;
    }

    @Override
    public void setItem(int slot, Button button) {
        buttons.put(slot, button);
        inventory.setItem(slot, button.getItemStack());
    }

    public Button getButton(int slot) {
        return buttons.get(slot);
    }

    public void close(Player player) {
        InventoryService.remove(player);
        player.closeInventory();
    }

    public void update(Player player) {
        clear();
        frame(player);
        generateInventory(player);
        setupItems();
    }

    public abstract void generateInventory(Player player);

    public void clear() {
        buttons.clear();
    }

    @Override
    public Map<Integer, Button> getButtons() {
        return buttons;
    }

    public void setupItems() {
        buttons.forEach( (slot, button) -> inventory.setItem(slot, button.getItemStack()));
    }

    @Override
    public void addFrameSlot(int slot) {

        this.frameSlots.add(slot);
    }

    public void frame(Player player) {
        this.frameSlots.forEach(slot -> setItem(slot, InventoryUtil.createItem(fileConfiguration, player, "inventories.inventory.frame-item")));
    }

    @Override
    public InventoryInfo getInfo() {
        return info;
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }

    @Override
    public FileConfiguration getFileConfiguration() {
        return fileConfiguration;
    }
}
