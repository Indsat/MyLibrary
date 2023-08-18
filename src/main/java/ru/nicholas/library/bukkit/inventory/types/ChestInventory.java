package ru.nicholas.bukkit.inventory.types;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import ru.nicholas.bukkit.inventory.BaseInventory;
import ru.nicholas.bukkit.inventory.buttons.Button;
import ru.nicholas.bukkit.inventory.info.InventoryInfo;
import ru.nicholas.bukkit.inventory.items.DefaultItem;
import ru.nicholas.bukkit.inventory.service.InventoryService;
import ru.nicholas.bukkit.utils.items.ItemUtil;
import ru.nicholas.core.VersionAdapter;

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

    public ChestInventory(String title, int rows) {
        this.inventory = VersionAdapter.InventoryUtils().createInventory(null, rows * 9, title);
        this.info = new InventoryInfo((String) VersionAdapter.TextUtil().colorize(title), rows * 9);
        this.buttons = new HashMap<>();
        this.frameSlots = new ArrayList<>();
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
        frame();
        generateInventory(player);
        setupItems();
    }

    public abstract void generateInventory(Player player);

    public void clear() {
        buttons.clear();
        inventory.clear();
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

    public void frame() {

        this.frameSlots.forEach(slot -> setItem(slot, ItemUtil.createDefaultItem(DefaultItem.FRAME_ITEM)));
    }

    @Override
    public InventoryInfo getInfo() {
        return info;
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }
}
