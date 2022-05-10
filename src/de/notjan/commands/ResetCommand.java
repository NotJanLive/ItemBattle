package de.notjan.commands;

import de.notjan.main.ItemBattle;
import org.bukkit.Bukkit;;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ResetCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        ItemBattle.getInstance().getConfig().options().copyDefaults(true);
        Bukkit.getServer().shutdown();
        return true;
    }
}
