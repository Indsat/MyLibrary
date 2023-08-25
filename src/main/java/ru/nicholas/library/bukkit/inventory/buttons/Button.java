package ru.nicholas.library.bukkit.inventory.buttons;

import org.bukkit.inventory.ItemStack;
import ru.nicholas.library.bukkit.inventory.click.Click;

public class Button {

    private final ItemStack itemStack;

    private final Click click;

    private int slot;

    public Button(ItemStack itemStack, Click click) {
        this.itemStack = itemStack;
        this.click = click;
    }

    public Button(int slot, ItemStack itemStack, Click click) {
        this.slot = slot;
        this.itemStack = itemStack;
        this.click = click;

    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public Click getClick() {
        return click;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }
}
