package de.notjan.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        e.setFormat("§6" + e.getPlayer().getDisplayName() + " §8» §7" + e.getMessage());
    }
}
