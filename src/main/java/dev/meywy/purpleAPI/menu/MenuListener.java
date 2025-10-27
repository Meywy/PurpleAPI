package dev.meywy.purpleAPI.menu;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.lang.annotation.Annotation;

public class MenuListener implements EventHandler {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        if (!(event.getWhoClicked() instanceof Player player)) return;
        if(event.getClickedInventory() == null) return;

        Menu menu = MenuManager.getInstance().getOpenMenu(player);
        if(menu == null || event.getClickedInventory().getHolder() == menu) return;

        event.setCancelled(true);

        int slot = event.getSlot();

        if (!(menu instanceof SimpleMenu simpleMenu)) return;

        MenuItem item = simpleMenu.getItemAt(slot);

        if (item == null) return;
        item.onClickEvent(event);

    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (!(event.getPlayer() instanceof Player player)) return;

        Menu menu = MenuManager.getInstance().getOpenMenu(player);

        if (menu != null && event.getInventory().getHolder() == menu) {
            MenuManager.getInstance().closeMenu(player);
        }
    }

    @Override
    public EventPriority priority() {
        return null;
    }

    @Override
    public boolean ignoreCancelled() {
        return false;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
