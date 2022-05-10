package de.notjan.listener;

import de.notjan.main.ItemBattle;
import de.notjan.run.RandomItem;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.bukkit.util.EulerAngle;

import static de.notjan.main.ItemBattle.scoreboard;

public class JoinEvent implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (ItemBattle.getInstance().getConfig().get("PlayerBossBar." + e.getPlayer().getDisplayName()) != null) {
                if (ItemBattle.started) {
                    BossBar bar = (BossBar) ItemBattle.getInstance().getConfig().get("PlayerBossBar." + e.getPlayer().getDisplayName());
                    bar.addPlayer(e.getPlayer());
                    bar.show();
                    RandomItem.checkItem(e.getPlayer());
                    ArmorStand as = (ArmorStand) e.getPlayer().getWorld().spawnEntity(e.getPlayer().getLocation(), EntityType.ARMOR_STAND);
                    as.setInvulnerable(true);
                    as.setInvisible(true);
                    as.setGravity(false);
                    as.setMarker(true);
                    as.setLeftArmPose(new EulerAngle(180, 0, 325));
                    as.setRightArmPose(new EulerAngle(180, 0, 35));
                    e.getPlayer().setPassenger(as);
                    ItemStack random = ItemBattle.getInstance().getConfig().getItemStack("PlayerItem." + e.getPlayer().getDisplayName());
                    as.setHelmet(random);
                    String itemName = WordUtils.capitalizeFully(random.getType().name().toLowerCase().replace("_", " "));
                    scoreboard.getTeam(e.getPlayer().getDisplayName()).unregister();
                    Team team = scoreboard.registerNewTeam(e.getPlayer().getDisplayName());
                    team.setColor(ChatColor.GOLD);
                    team.setSuffix(" §7[§6" + itemName + "§7]");
                    team.addPlayer(e.getPlayer());
                    e.getPlayer().setScoreboard(scoreboard);
            }
        } else {
            BossBar bar = Bukkit.createBossBar(e.getPlayer().getDisplayName(), BarColor.YELLOW, BarStyle.SOLID);
            bar.addPlayer(e.getPlayer());
            bar.hide();
            ItemBattle.getInstance().getConfig().getConfigurationSection("PlayerBossBar").createSection(e.getPlayer().getDisplayName());
            ItemBattle.getInstance().getConfig().set("PlayerBossBar." + e.getPlayer().getDisplayName(), bar);
            ItemBattle.getInstance().saveConfig();
        }
    }

}