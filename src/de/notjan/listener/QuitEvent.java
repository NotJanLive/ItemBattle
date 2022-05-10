package de.notjan.listener;

import de.notjan.main.ItemBattle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {
    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        if(ItemBattle.started){
            Location loc = e.getPlayer().getLocation();
            double x = loc.getX();
            double y = loc.getY();
            double z = loc.getZ();
            if (e.getPlayer().getWorld().getName().equals("world")) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kill @e[type=minecraft:armor_stand, x=" + x + ", y=" + y + ", z=" + z + ", distance=..3]");
            } else if (e.getPlayer().getWorld().getName().equals("world_nether")) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:the_nether run kill @e[type=minecraft:armor_stand,x=" + x + ", y=" + y + ", z=" + z + ", distance=..3]");
            } else if (e.getPlayer().getWorld().getName().equals("world_the_end")) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:the_end run kill @e[type=minecraft:armor_stand,x=" + x + ", y=" + y + ", z=" + z + ", distance=..3]");
            }
        }
    }
}
