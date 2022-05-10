package de.notjan.commands;

import de.notjan.main.ItemBattle;
import de.notjan.run.Results;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ResultCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            for (Player players : Bukkit.getOnlinePlayers()) {
                if(ItemBattle.ended) {
                    Results.announceResult(players);
                } else {
                    sender.sendMessage(ItemBattle.prefix + "§cDas Battle ist noch nicht zu Ende!");
                }
            }
        } else {
            sender.sendMessage(ItemBattle.prefix + "§cDieser Command ist nur für Spieler verfügbar!");
            return true;
        }
        return true;
    }
}
