package ru.nicholas.library.bukkit.inventory.alternative;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import ru.nicholas.library.bukkit.inventory.BaseInventory;
import ru.nicholas.library.bukkit.inventory.buttons.Button;
import ru.nicholas.library.bukkit.inventory.exception.InvalidPageException;
import ru.nicholas.library.bukkit.inventory.service.InventoryService;
import ru.nicholas.library.bukkit.utils.InventoryUtil;

import java.util.ArrayList;
import java.util.List;

public abstract class PagedInventory {

    public BaseInventory inventory;

    private int page, pagesCount;

    private final List<Button> buttons;

    private final List<Integer> slots;

    private final int nextButtonSlot;

    private final int backButtonSlot;

    public PagedInventory(BaseInventory inventory, int nextButtonSlot, int backButtonSlot) {
        this.inventory = inventory;
        this.buttons = new ArrayList<>();
        this.slots = new ArrayList<>();
        this.backButtonSlot = backButtonSlot;
        this.nextButtonSlot = nextButtonSlot;
        this.page = 0;
    }

    public Button getButton(ItemStack itemStack) {
        return buttons.stream().filter(button -> button.getItemStack().isSimilar(itemStack)).findAny().orElse(null);
    }

    public Button getButton(int slot) {
        return buttons.stream().filter(button -> button.getSlot() == slot).findAny().orElse(null);
    }

    public void close(Player player) {
        InventoryService.remove(player);
        player.closeInventory();
    }

    public List<Button> getButtons() {
        return buttons;
    }

    public List<Integer> getSlots() {
        return slots;
    }

    public abstract void generateInventory(Player player);

    public void openInventory(Player player) {
        inventory.frame(player);
        inventory.generateInventory(player);
        inventory.setupItems();
        generateInventory(player);
        player.openInventory(inventory.getInventory());
        buildPage(player);
        InventoryService.cache(player, this);
    }

    private void backward(Player player) {
        if (page - 1 < 0) {
            throw new InvalidPageException(page);
        }

        this.page--;
        clear();
        openInventory(player);
    }

    private void forward(Player player) {
        if (page >= pagesCount) {
            throw new InvalidPageException(page);
        }

        this.page++;
        clear();
        openInventory(player);
    }

    public void addFrameSlots(List<Integer> slots) {
        slots.forEach(slot -> inventory.addFrameSlot(slot));
    }

    public void addSlots(List<Integer> slots) {
        this.slots.addAll(slots);
    }

    public void addButton(Button button) {
        this.buttons.add(button);
    }

    private void buildPage(Player player) {
        buttons.clear();
        inventory.frame(player);
        inventory.generateInventory(player);
        generateInventory(player);
        inventory.setupItems();
        int itemsPerPage = slots.size();
        this.pagesCount = (int) Math.ceil((double) buttons.size() / itemsPerPage);

        if (page + 1 < pagesCount) {
            createNextButton(player);
        }

        if (page - 1 >= 0) {
            createPreviousButton(player);
        }

        int startIndex = page * itemsPerPage;
        int endIndex = Math.min(startIndex + itemsPerPage, buttons.size());
        for (int i = startIndex; i < endIndex; i++) {
            Button button = buttons.get(i);
            int slotIndex = i - startIndex;
            int slot = slots.get(slotIndex);
            inventory.setItem(slot, button);
        }
    }

    private void createNextButton(Player player) {
        inventory.setItem(nextButtonSlot, new Button(InventoryUtil.createItem(inventory.getFileConfiguration(), player, "inventories.paged-inventory.next-button"), (player1, type) -> forward(player)));
    }

    private void createPreviousButton(Player player) {
        inventory.setItem(backButtonSlot, new Button(InventoryUtil.createItem(inventory.getFileConfiguration(), player, "inventories.paged-inventory.previous-button"), (player1, type) -> backward(player)));
    }

    public BaseInventory getInventory() {
        return inventory;
    }

    public void clear() {
        buttons.clear();
        inventory.clear();
        inventory.getInventory().clear();
    }

    public void update(Player player) {
        buttons.clear();
        inventory.clear();
        buildPage(player);
    }
}

