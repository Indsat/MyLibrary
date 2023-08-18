package ru.nicholas.bukkit.inventory.alternative;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import ru.nicholas.bukkit.inventory.BaseInventory;
import ru.nicholas.bukkit.inventory.buttons.Button;
import ru.nicholas.bukkit.inventory.exception.InvalidPageException;
import ru.nicholas.bukkit.inventory.items.DefaultItem;
import ru.nicholas.bukkit.inventory.service.InventoryService;
import ru.nicholas.bukkit.utils.items.ItemUtil;

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
        inventory.frame();
        inventory.generateInventory(player);
        inventory.setupItems();
        inventory.frame();
        generateInventory(player);
        player.openInventory(inventory.getInventory());
        buildPage(player);
        InventoryService.cache(player, this);
    }

    private void backward(Player player) {
        if (page - 1 < 0) {
            throw new InvalidPageException(page);
        }

        inventory.getInventory().clear();
        this.page--;
        openInventory(player);
    }

    private void forward(Player player) {
        if (page >= pagesCount) {
            throw new InvalidPageException(page);
        }

        inventory.getInventory().clear();
        this.page++;
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

        this.pagesCount = (int) Math.ceil((double) buttons.size() / slots.size());

        if (page + 1 < pagesCount) {
            createNextButton(player);
        }

        if (!(page - 1 < 0) ) {
            createPreviousButton(player);
        }

        for (int i = 0; i < slots.size(); i++) {
            int index = page * slots.size() + i;

            if (buttons.size() <= index) {
                return;
            }

            int slot = slots.get(i);
            Button button = buttons.get(index);
            button.setSlot(slot);
            ItemStack itemStack = button.getItemStack();
            inventory.setItem(slot, itemStack, button.getClick());
        }
    }

    private void createNextButton(Player player) {
        inventory.setItem(nextButtonSlot, new Button(ItemUtil.createDefaultItem(DefaultItem.NEXT_PAGE_ITEM), (player1, type) -> forward(player)));
    }

    private void createPreviousButton(Player player) {
        inventory.setItem(backButtonSlot, new Button(ItemUtil.createDefaultItem(DefaultItem.PREVIOUS_PAGE_ITEM), (player1, type) -> backward(player)));
    }

    public BaseInventory getInventory() {
        return inventory;
    }

    public void clear() {
        buttons.clear();
        inventory.clear();
    }

    public void update(Player player) {
        clear();
        generateInventory(player);
        inventory.frame();
        inventory.setupItems();
        buildPage(player);
    }
}
