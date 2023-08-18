package ru.nicholas.bukkit.inventory.items;

public enum DefaultItem {

    FRAME_ITEM("inventories.inventory.frame-item"),
    EMPTY_ITEM("inventories.inventory.empty-item"),
    NEXT_PAGE_ITEM("inventories.paged-inventory.next-button"),
    PREVIOUS_PAGE_ITEM("inventories.paged-inventory.previous-button"),
    PERMS_ITEM("inventories.inventory.perms-item"),
    DEAL_ITEM("inventories.inventory.deal-item");

    private final String path;

    DefaultItem (String path) {

        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
