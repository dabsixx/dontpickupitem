package me.dabsi_xx.Events;

import me.dabsi_xx.Util.ConfigFile;
import me.dabsi_xx.Util.ItemFile;
import me.dabsi_xx.Util.MessageFile;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class OnItemDrop implements Listener {
    private static ArrayList<String> itemList;
    private static ItemStack currentItem;

    @EventHandler
    public void onItemDrop (PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        currentItem = event.getItemDrop().getItemStack();
        itemList = (ArrayList<String>) ItemFile.get().getStringList("items");

        if (!ConfigFile.get().getBoolean("BlockDrop")) return;
        if (player.hasPermission("dpi.drop")) return;
        if (!currentItem.hasItemMeta()) return;

        if (itemList.contains(currentItem.getItemMeta().getDisplayName())) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', (MessageFile.get().get("Prefix") + " " + MessageFile.get().getString("CantDrop").replaceAll("%item%", event.getItemDrop().getItemStack().getItemMeta().getDisplayName()))));
            event.setCancelled(true);
        }
    }
}
