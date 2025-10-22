package dev.meywy.purpleAPI.menu;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public class MenuManager {

    private static MenuManager instance;
    private final HashMap<UUID, Menu> openMenus = new HashMap<>();

    public static MenuManager getInstance() {
        if (instance == null) instance = new MenuManager();
        return instance;
    }

    public void openMenu(Player player, Menu menu) {
        openMenus.put(player.getUniqueId(), menu);
    }

    public void closeMenu(Player player) {
        openMenus.remove(player.getUniqueId());
    }

    public Menu getOpenMenu(Player player) {
        return openMenus.get(player.getUniqueId());
    }


}
