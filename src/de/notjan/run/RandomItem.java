package de.notjan.run;

import de.notjan.commands.DebugSkipCommand;
import de.notjan.files.ItemList;
import de.notjan.main.ItemBattle;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Team;
import org.bukkit.util.EulerAngle;

import java.util.Random;

import static de.notjan.main.ItemBattle.scoreboard;

public class RandomItem {
    public static void randomize(Player player){
        ItemStack random = new ItemStack(ItemList.mats.get(new Random().nextInt(ItemList.mats.size())));
        String name = random.getType().name();
        Location loc = player.getLocation();
        double x = loc.getX();
        double y = loc.getY();
        double z = loc.getZ();
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kill @e[type=minecraft:armor_stand, x=" + x + ", y=" + y + ", z=" + z + ", distance=..2]");
        BossBar bar = (BossBar) ItemBattle.getInstance().getConfig().get("PlayerBossBar." + player.getDisplayName());
        String itemName = WordUtils.capitalizeFully(random.getType().name().toLowerCase().replace("_", " "));
        if (name.contains("STEM") || name.contains("REDSTONE_WIRE") || name.contains("PLANT") || name.contains("SPAWN_EGG") || name.contains("SOUL_FIRE") || name.contains("DEBUG_STICK") || name.contains("CANDLE_CAKE") || name.contains("WALL_BANNER") || name.contains("WALL_SIGN") || name.contains("INFESTED") || name.contains("POTTED") || name.contains("WATER_CAULDRON") || name.contains("LAVA_CAULDRON") || name.contains("POWDER_SNOW_CAULDRON") || name.contains("WALL_TORCH") || name.contains("TALL_SEAGRASS")) {
            randomize(player);
        } else {
            if(scoreboard.getTeam(player.getDisplayName()) == null) {
                Team team = scoreboard.registerNewTeam(player.getDisplayName());
                team.setColor(ChatColor.GOLD);
                team.setSuffix(" §7[§6" + itemName + "§7]");
                team.addPlayer(player);
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.setScoreboard(scoreboard);
                }
            } else {
                Team team = scoreboard.getTeam(player.getDisplayName());
                team.setColor(ChatColor.GOLD);
                team.setSuffix(" §7[§6" + itemName + "§7]");
                team.addPlayer(player);
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.setScoreboard(scoreboard);
                }
            }
        ItemBattle.getInstance().getConfig().set("PlayerItem." + player.getDisplayName(), random);
        ItemBattle.getInstance().saveConfig();
        player.sendMessage( ItemBattle.prefix + "§7Neues Item: §6" + itemName);
        bar.setTitle("§7Nächstes Item: §6" + itemName);
        bar.show();
            ArmorStand as = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
            as.setInvisible(true);
            as.setMarker(true);
            as.setInvulnerable(true);
            as.setGravity(false);
            as.setLeftArmPose(new EulerAngle(180, 0, 325));
            as.setRightArmPose(new EulerAngle(180, 0, 35));

            player.setPassenger(as);
            as.setHelmet(random);
        checkItem(player);
        }
    }

    public static void checkItem(Player player){
        ItemStack random = ItemBattle.getInstance().getConfig().getItemStack("PlayerItem." + player.getDisplayName());
        String itemName = WordUtils.capitalizeFully(random.getType().name().toLowerCase().replace("_", " "));
        new BukkitRunnable() {
            @Override
            public void run() {
                if(DebugSkipCommand.debug) {
                    DebugSkipCommand.debug = false;
                    randomize(player);
                    cancel();
                }
                if (player.getInventory().contains(random)) {
                    if (ItemBattle.started) {

                        player.sendMessage(ItemBattle.prefix + "§7Du hast §6" + itemName + " §7gefunden!");
                        ItemBattle.getInstance().getConfig().set("PlayerScore." + player.getDisplayName(), ItemBattle.getInstance().getConfig().getInt("PlayerScore." + player.getDisplayName()) + 1);
                        ItemBattle.getInstance().saveConfig();
                        randomize(player);
                        cancel();
                    } else {
                        cancel();
                    }
                }
            }
        }.runTaskTimer(ItemBattle.getInstance(), 20, 20);
    }
}
