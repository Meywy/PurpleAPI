package dev.meywy.purpleapi;

import org.bukkit.ChatColor;

public abstract class Utils {

    public String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
