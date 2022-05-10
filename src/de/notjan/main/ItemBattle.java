package de.notjan.main;

import de.notjan.commands.*;
import de.notjan.files.ItemList;
import de.notjan.listener.*;
import de.notjan.run.PlayerPortalEvent;
import de.notjan.run.WorldChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;


public class ItemBattle extends JavaPlugin {
    public static ItemBattle main;
    public ItemBattle(){main = this;}
    public static String prefix = "§7[§6ItemBattle§7] ";
    public static boolean started;
    public static boolean ended;
    public static Scoreboard scoreboard;

    @Override
    public void onEnable() {
        ended = false;
        getConfig().options().copyDefaults(true);
        saveConfig();
        ItemList.setList();
        registerCommands();
        registerEvents();
        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Bukkit.getConsoleSender().sendMessage(prefix + "§aDas Plugin wurde geladen!");
        for(Player players : Bukkit.getOnlinePlayers()) {
            if(players.isOp()) {
                players.sendMessage("§aReload complete.");
            }
        }
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(prefix + "§cDas Plugin wurde gestoppt!");
        ItemBattle.getInstance().getConfig().options().copyDefaults(true);
    }

    public void registerCommands(){
        getCommand("start").setExecutor(new StartCommand());
        getCommand("forcereload").setExecutor(new ReloadCommand());
        getCommand("results").setExecutor(new ResultCommand());
        getCommand("reset").setExecutor(new ResetCommand());
        getCommand("top").setExecutor(new TopCommand());
        getCommand("debugskip").setExecutor(new DebugSkipCommand());
        getCommand("test").setExecutor(new TestCommand());
    }
    public void registerEvents(){
        PluginManager pl = Bukkit.getPluginManager();
        pl.registerEvents(new ChatListener(), this);
        pl.registerEvents(new ProtectionListener(), this);
        pl.registerEvents(new JoinEvent(), this);
        pl.registerEvents(new CommandCanceller(), this);
        pl.registerEvents(new InteractListener(), this);
        pl.registerEvents(new QuitEvent(), this);
        pl.registerEvents(new WorldChangeEvent(), this);
        pl.registerEvents(new PlayerPortalEvent(), this);
    }


    public static ItemBattle getInstance() {return main;}
}
