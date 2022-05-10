package de.notjan.commands;

import de.notjan.apis.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player) {
            commandSender.sendMessage(Utils.translate("&#fb0000m&#fb0f00m&#fb1e00o&#fc2e00c&#fc3d00r&#fc4c01a&#fc5b01f&#fc6a01t&#fd7a01.&#fd8901d&#fd9801e").replace("&", ""));
        } else {
            return true;
        }
        return true;
    }
}
