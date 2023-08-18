package ru.nicholas.bukkit.inventory.items;

import org.bukkit.inventory.ItemStack;
import ru.nicholas.bukkit.inventory.click.Click;

import java.util.List;

public final class CustomItem {

   private ItemStack itemStack;

   private Click actionOnClick;

   private final String name;

   private final String action;

   private final List<String> commands;

   private final String permission;

   private final List<Integer> slots;

   private final int customModeData;

   public CustomItem(
           String name,
           String action,
           List<String> commands,
           String permission,
           List<Integer> slots,
           ItemStack itemStack,
           int customModeData,
           Click actionOnClick) {
      this.name = name;
      this.action = action;
      this.commands = commands;
      this.permission = permission;
      this.slots = slots;
      this.itemStack = itemStack;
      this.actionOnClick = actionOnClick;
      this.customModeData = customModeData;
   }

   public ItemStack getItemStack() {
      return itemStack;
   }

   public String getName() {
      return name;
   }

   public Click getActionOnClick() {
      return actionOnClick;
   }

   public int getCustomModeData() {
      return customModeData;
   }

   public List<Integer> getSlots() {
      return slots;
   }

   public List<String> getCommands() {
      return commands;
   }

   public String getAction() {
      return action;
   }

   public String getPermission() {
      return permission;
   }

   public void setActionOnClick(Click actionOnClick) {
      this.actionOnClick = actionOnClick;
   }

   public void setItemStack(ItemStack itemStack) {
      this.itemStack = itemStack;
   }
}
