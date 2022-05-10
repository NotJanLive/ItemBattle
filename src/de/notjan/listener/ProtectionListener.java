package de.notjan.listener;

import de.notjan.apis.ItemBuilder;
import de.notjan.main.ItemBattle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

public class ProtectionListener implements Listener {
    @EventHandler
    public void onBreak(BlockBreakEvent e){
        if(!ItemBattle.started){
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        if(!ItemBattle.started){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e){
        if(ItemBattle.started){
            ItemStack skipItem = new ItemBuilder(Material.BARRIER, ItemBattle.getInstance().getConfig().getInt(e.getPlayer().getDisplayName() + ".Skips")).setName("§cSkip Item").addLoreLine("§8» §cÜberspringe §7das aktuelle Item §7").toItemStack();
            e.getPlayer().getInventory().addItem(skipItem);
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
        }
    }
    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        if(ItemBattle.started) {
            ItemStack skipItem = new ItemBuilder(Material.BARRIER, ItemBattle.getInstance().getConfig().getInt(e.getEntity().getDisplayName() + ".Skips")).setName("§cSkip Item").addLoreLine("§8» §cÜberspringe §7das aktuelle Item §7").toItemStack();
            if(e.getEntity().getInventory().contains(skipItem)) {
                e.getDrops().remove(skipItem);
            }
            Location loc = e.getEntity().getLocation();
            double x = loc.getX();
            double y = loc.getY();
            double z = loc.getZ();
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kill @e[type=minecraft:armor_stand, x=" + x + ", y=" + y + ", z=" + z + ", distance=..2]");
        }
    }
    @EventHandler
    public void onPickup(PlayerPickupItemEvent e) {
        if(!ItemBattle.started) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if(!ItemBattle.started) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if(!ItemBattle.started) {
            if (e.getEntity().getType().equals(EntityType.PLAYER)) {
                e.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void onFood(FoodLevelChangeEvent e) {
        if(!ItemBattle.started) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onWeather(WeatherChangeEvent e) {
        if(!ItemBattle.started) {
            e.setCancelled(true);
        }
    }
}
