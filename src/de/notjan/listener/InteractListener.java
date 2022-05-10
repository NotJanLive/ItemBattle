package de.notjan.listener;

import de.notjan.main.ItemBattle;
import de.notjan.run.RandomItem;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class InteractListener implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        ItemStack random = ItemBattle.getInstance().getConfig().getItemStack("PlayerItem." + e.getPlayer().getDisplayName());
        if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            if(ItemBattle.started) {
            if(e.getItem() != null) {
                if (e.getItem().getType().equals(Material.BARRIER)) {
                    if (e.getItem().getItemMeta().getDisplayName().equals("§cSkip Item")) {
                        if (e.getPlayer().getInventory().firstEmpty() == -1) {
                            e.getPlayer().getWorld().dropItemNaturally(e.getPlayer().getLocation(), random);
                            e.getPlayer().sendMessage(ItemBattle.prefix + "§cDein Item wurde aufgrund eines vollen Inventars gedroppt!");
                            e.getPlayer().getInventory().getItemInMainHand().setAmount(e.getPlayer().getInventory().getItemInMainHand().getAmount() - 1);
                            ItemBattle.getInstance().getConfig().set(e.getPlayer().getDisplayName() + ".Skips", ItemBattle.getInstance().getConfig().getInt(e.getPlayer().getDisplayName() + ".Skips") - 1);
                            ItemBattle.getInstance().saveConfig();
                            e.setCancelled(true);
                        } else {
                            e.getPlayer().getInventory().addItem(random);
                            e.getPlayer().getInventory().getItemInMainHand().setAmount(e.getPlayer().getInventory().getItemInMainHand().getAmount() - 1);
                            e.getPlayer().sendMessage(ItemBattle.prefix + "§aDu hast das Item übersprungen!");
                            ItemBattle.getInstance().getConfig().set(e.getPlayer().getDisplayName() + ".Skips", ItemBattle.getInstance().getConfig().getInt(e.getPlayer().getDisplayName() + ".Skips") - 1);
                            ItemBattle.getInstance().saveConfig();
                            e.setCancelled(true);
                        }
                    }
                }
            }
            } else {
                e.setCancelled(true);
            }
        }
    }
}
