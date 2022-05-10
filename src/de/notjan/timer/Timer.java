package de.notjan.timer;

import de.notjan.main.ItemBattle;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Timer {
    public static String time(long sec){
        return String.format("%02d:%02d:%02d", sec / 3600, (sec % 3600) / 60, sec % 60);
    }

    public static void start(){
        ItemBattle.started = true;
        new BukkitRunnable(){
            long time = ItemBattle.getInstance().getConfig().getInt("Settings.Time");
            @Override
            public void run() {
                for(Player players : Bukkit.getOnlinePlayers()) {
                    String message = "§7» §6§l" + time(time) + "§7§l - §6§l" + ItemBattle.getInstance().getConfig().getInt("PlayerScore." + players.getDisplayName()) + " §7«";
                    players.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
                }
                if(time == 300) {
                    Bukkit.broadcastMessage(ItemBattle.prefix + "§cDas ItemBattle endet in §e5 Minuten§c!");
                }
                if(time == 60){
                    Bukkit.broadcastMessage(ItemBattle.prefix + "§cDas ItemBattle endet in §eeiner Minute§c!");
                }
                if(time == 0){
                    Bukkit.broadcastMessage(ItemBattle.prefix + "§cDas ItemBattle ist vorbei!");
                    for(Player players : Bukkit.getOnlinePlayers()) {
                        players.playSound(players.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 1 ,1);
                        players.setGameMode(GameMode.SPECTATOR);
                    }
                    ItemBattle.started = false;
                    Bukkit.broadcastMessage(ItemBattle.prefix + "§aNutze §7/results §afür das Ergebnis des Battles!");
                    ItemBattle.ended = true;
                    cancel();
                }
                time--;
            }
        }.runTaskTimer(ItemBattle.getInstance(), 20, 20);
    }
}
