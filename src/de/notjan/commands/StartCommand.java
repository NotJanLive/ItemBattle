package de.notjan.commands;

import de.notjan.apis.ItemBuilder;
import de.notjan.main.ItemBattle;
import de.notjan.run.RandomItem;
import de.notjan.timer.Timer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class StartCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(ItemBattle.prefix + "§cDieser Command ist nur für Spieler verfügbar!");
            return true;
        } else {
            if(ItemBattle.started) {
                sender.sendMessage(ItemBattle.prefix + "§cDas Battle läuft bereits!");
                return true;
            } else {
                Bukkit.broadcastMessage(ItemBattle.prefix + "§aDas Battle hat begonnen! §6Viel Glück!");
                for (Player players : Bukkit.getOnlinePlayers()) {
                    ItemStack skipItem = new ItemBuilder(Material.BARRIER, 5).setName("§cSkip Item").addLoreLine("§8» §cÜberspringe §7das aktuelle Item §7").toItemStack();
                    players.getInventory().setItem(8, skipItem);
                    players.playSound(players.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);
                    Timer.start();
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule sendCommandFeedback false");
                    ItemBattle.getInstance().getConfig().getConfigurationSection("PlayerScore").createSection(players.getDisplayName());
                    ItemBattle.getInstance().getConfig().getConfigurationSection("PlayerItem").createSection(players.getDisplayName());
                    ItemBattle.getInstance().getConfig().createSection(players.getDisplayName());
                    ItemBattle.getInstance().getConfig().getConfigurationSection(players.getDisplayName()).createSection("Skips");
                    ItemBattle.getInstance().getConfig().set("PlayerScore." + players.getDisplayName(), '0');
                    ItemBattle.getInstance().getConfig().set(players.getDisplayName() + ".Skips", 5);
                    ItemBattle.getInstance().saveConfig();
                    RandomItem.randomize(players);
                }
            }
        }
        return true;
    }
}
