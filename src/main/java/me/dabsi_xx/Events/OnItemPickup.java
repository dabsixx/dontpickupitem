package me.dabsi_xx.Events;

import me.dabsi_xx.Dontpickupitems;
import me.dabsi_xx.Util.ConfigFile;
import me.dabsi_xx.Util.ItemFile;
import me.dabsi_xx.Util.MessageFile;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class OnItemPickup implements Listener {
    private static ArrayList<String> itemList;
    private static ItemStack currentItem;

    @EventHandler
    public void onItemPickup(EntityPickupItemEvent event) {
        itemList = (ArrayList<String>) ItemFile.get().getStringList("items");
        currentItem = event.getItem().getItemStack();
        Player player = ((Player) event.getEntity()).getPlayer();

        if (!ConfigFile.get().getBoolean("BlockPickup")) return;
        if (player.hasPermission("dpi.pickup")) return;
        if (!currentItem.hasItemMeta()) return;

        if (itemList.contains(currentItem.getItemMeta().getDisplayName())) {
            if (!Dontpickupitems.getCooldownManager().isPlayerInCooldown(player)) player.sendMessage(ChatColor.translateAlternateColorCodes('&', (MessageFile.get().get("Prefix") + " " + MessageFile.get().getString("CantPickup").replaceAll("%item%", event.getItem().getItemStack().getItemMeta().getDisplayName()))));
                Dontpickupitems.getCooldownManager().addPlayerToMap(player, 3);
            event.setCancelled(true);
        }
    }
}
