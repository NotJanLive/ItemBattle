package de.notjan.commands;

import de.notjan.main.ItemBattle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandCanceller implements Listener {
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e){
        if(e.getMessage().equals("/rl") || e.getMessage().equals("/reload") || e.getMessage().equals("/bukkit:reload")) {
            e.getPlayer().sendMessage(ItemBattle.prefix + "§cDurch das Reloaden funktioniert das Plugin nicht mehr!");
            e.getPlayer().sendMessage(ItemBattle.prefix + "§cDeine Aktion wurde abgebrochen!");
            e.setCancelled(true);
        }
    }
}
