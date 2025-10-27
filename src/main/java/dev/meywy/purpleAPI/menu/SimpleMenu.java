package dev.meywy.purpleAPI.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class SimpleMenu implements Menu, InventoryHolder {

    private final String title;
    private final int rows;
    private final Map<Integer, MenuItem> items = new HashMap<>();

    public SimpleMenu(String title, int rows) {
        this.title = title;
        this.rows = rows;
    }

    public SimpleMenu setItem(int slot, MenuItem item) {
        items.put(slot, item);
        return this;
    }


    @Override
    public Inventory getInventory(Player player) {
        Inventory inv = Bukkit.createInventory(this, rows * 9, title);
        return inv;
    }

    @Override
    public @NotNull Inventory getInventory() {
        return getInventory(null);
    }


    @Override
    public void open(Player player) {
        player.openInventory(getInventory(player));
        MenuManager.getInstance().openMenu(player, this);
    }

    @Override
    public void close(Player player) {
        if (player.getOpenInventory().getTopInventory().getHolder() == this) {
            player.closeInventory();
        }


    }
    @Override
    public void update(Player player) {
        Inventory inv = player.getOpenInventory().getTopInventory();
        if (inv.getHolder() == this) {
            inv.clear();
            items.forEach((slot, item) -> inv.setItem(slot, item.getItem()));
        }
    }

    public MenuItem getItemAt(int slot) {
        return items.get(slot);
    }

}