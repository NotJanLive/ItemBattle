package de.notjan.run;

import de.notjan.main.ItemBattle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

public class WorldChangeEvent implements Listener {
    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent e){
        if(ItemBattle.started){
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
