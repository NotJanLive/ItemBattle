package de.notjan.commands;

import de.notjan.main.ItemBattle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DebugSkipCommand implements CommandExecutor {
    public static boolean debug = false;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            if(ItemBattle.started) {
                debug = true;
                return true;
            }
        } else {
            sender.sendMessage(ItemBattle.prefix + "§cDieser Command ist nur für Spieler verfügbar!");
            return true;
        }
        return true;
    }
}
