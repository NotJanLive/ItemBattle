package de.notjan.commands;

import de.notjan.main.ItemBattle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TopCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(p.getWorld().getName().equals("world")) {
                p.sendMessage(ItemBattle.prefix + "§aDu wurdest an die §eOberfläche §ateleportiert.");
                Location loc = p.getLocation();
                Location top = p.getLocation();
                top.setY(Bukkit.getWorld("world").getHighestBlockYAt(loc) + 1);
                double x = loc.getX();
                double y = loc.getY();
                double z = loc.getZ();
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kill @e[type=minecraft:armor_stand, x=" + x + ", y=" + y + ", z=" + z + ", distance=..3]");
                p.teleport(top);
                p.playSound(p.getLocation() , Sound.ENTITY_ENDERMAN_TELEPORT , 1, 1);
            } else {
                p.sendMessage(ItemBattle.prefix + "§aDu wurdest in die §eOverworld §ateleportiert.");
                Location loc = Bukkit.getWorld("world").getSpawnLocation();
                loc.setY(Bukkit.getWorld("world").getHighestBlockYAt(loc) + 1);
                double x = loc.getX();
                double y = loc.getY();
                double z = loc.getZ();
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kill @e[type=minecraft:armor_stand, x=" + x + ", y=" + y + ", z=" + z + ", distance=..3]");
                p.teleport(new Location(Bukkit.getWorld("world"), loc.getX(), loc.getY(), loc.getZ()));
                p.playSound(p.getLocation() , Sound.ENTITY_ENDERMAN_TELEPORT , 1, 1);
            }
        }else {
            sender.sendMessage(ItemBattle.prefix + "§cDieser Command ist nur für Spieler verfügbar!");
        }
            return true;
    }
}
