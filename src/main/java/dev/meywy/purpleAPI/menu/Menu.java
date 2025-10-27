package dev.meywy.purpleAPI.menu;


import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public interface Menu {

    Inventory getInventory(Player player);
    void open(Player player);
    void close(Player player);
    void update(Player player);


}
