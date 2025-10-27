package dev.meywy.purpleAPI.menu;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class MenuItem {

    private final ItemStack item;
    private final Consumer<InventoryClickEvent> clickHandler;

    public MenuItem(ItemStack item, Consumer<InventoryClickEvent> clickHandler) {
        this.item = item;
        this.clickHandler = clickHandler;
    }

    public ItemStack getItem() {
        return item.clone();
    }

    public void onClickEvent(InventoryClickEvent event) {
        clickHandler.accept(event);
    }
}
