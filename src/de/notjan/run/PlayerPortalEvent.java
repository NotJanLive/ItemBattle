package de.notjan.run;

import de.notjan.main.ItemBattle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

public class PlayerPortalEvent implements Listener {
    @EventHandler
    public void onJoin(PlayerMoveEvent e) {
        if (e.getPlayer().getLocation().getBlock().getType().equals(Material.NETHER_PORTAL) || e.getPlayer().getLocation().getBlock().getType().equals(Material.END_PORTAL)) {
            Location loc = e.getPlayer().getLocation();
            double x = loc.getX();
            double y = loc.getY();
            double z = loc.getZ();
            if (e.getPlayer().getWorld().getName().equals("world")) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kill @e[type=minecraft:armor_stand, x=" + x + ", y=" + y + ", z=" + z + ", distance=..3]");
            } else if (e.getPlayer().getWorld().getName().equals("world_nether")) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:the_nether run kill @e[type=minecraft:armor_stand,x=" + x + ", y=" + y + ", z=" + z + ", distance=..3]");
            } else if (e.getPlayer().getWorld().getName().equals("world_the_end")) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:the_end run kill @e[type=minecraft:armor_stand,x=" + x + ", y=" + y + ", z=" + z + ", distance=..3]");
            }
        } else if (e.getPlayer().getLocation().getBlock().getType().equals(Material.WATER) || e.getPlayer().getLocation().getBlock().getType().equals(Material.LAVA)) {
            Location loc = e.getPlayer().getLocation();
            double x = loc.getX();
            double y = loc.getY();
            double z = loc.getZ();
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kill @e[type=minecraft:armor_stand, x=" + x + ", y=" + y + ", z=" + z + ", distance=..3]");
        } else{
            if(e.getPlayer().getPassenger() == null){
                ItemStack random = ItemBattle.getInstance().getConfig().getItemStack("PlayerItem." + e.getPlayer().getDisplayName());
                ArmorStand as = (ArmorStand) e.getPlayer().getWorld().spawnEntity(e.getPlayer().getLocation(), EntityType.ARMOR_STAND);
                as.setInvisible(true);
                as.setMarker(true);
                as.setInvulnerable(true);
                as.setGravity(false);
                as.setLeftArmPose(new EulerAngle(180, 0, 325));
                as.setRightArmPose(new EulerAngle(180, 0, 35));

                e.getPlayer().setPassenger(as);
                as.setHelmet(random);
            }
        }
    }
}
