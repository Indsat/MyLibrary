package ru.nicholas.bukkit.inventory.info;

public class InventoryInfo {

    private String title;

    private int rows;

    public InventoryInfo(String title, int rows) {

        this.title = title;

        this.rows = rows;
    }

    public int getRows() {
        return rows;
    }

    public String getTitle() {
        return title;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
