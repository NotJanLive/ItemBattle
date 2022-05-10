package de.notjan.run;

import de.notjan.apis.Utils;
import de.notjan.main.ItemBattle;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class Results {
    public static String winner;
    public static void announceResult(Player player){
        HashMap<String, Integer> players = new HashMap<String, Integer>();
        for(String playerName: ItemBattle.getInstance().getConfig().getConfigurationSection("PlayerScore").getKeys(false)){
            players.put(playerName, ItemBattle.getInstance().getConfig().getInt("PlayerScore." + playerName ));
        }
        String nextTop = "";
        Integer nextTopKills = 0;
        String senderName = player.getName();
        int playerRank = 0;

        player.sendMessage("§7===========[§6Top§7]===========");

        if(players.containsKey(senderName)){
            playerRank++;
            int playerKills = players.get(senderName);
            for(String playerName: players.keySet()){
                if(playerName.equalsIgnoreCase(senderName)) continue;
                if(players.get(playerName) > playerKills) playerRank++;
            }
        }

        for(int i = 1; i < 11; i++){
            for(String playerName: players.keySet()){
                if(players.get(playerName) > nextTopKills){
                    nextTop = playerName;
                    nextTopKills = players.get(playerName);
                }
            }
            if(i == 1) {
                winner = nextTop;
            }
            player.sendMessage("§6" + "#" + i  + "§a " + nextTop + "§7: " + "§6" + nextTopKills);
            players.remove(nextTop);
            nextTop = "";
            nextTopKills = 0;
        }
        player.sendMessage("§7Deine Platzierung: §6" + playerRank + ".");
        player.sendMessage("§7===========[§6Top§7]===========");
        for(Player player1 : Bukkit.getOnlinePlayers()) {
            player1.sendTitle("§6" + winner, "§7hat das Battle gewonnen!", 20, 100 ,20);
            player1.playSound(player1.getLocation(), Sound.ENTITY_ENDER_DRAGON_DEATH, 1, 1);
        }
    }
}
