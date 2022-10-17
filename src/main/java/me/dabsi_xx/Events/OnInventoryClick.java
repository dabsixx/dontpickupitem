package me.dabsi_xx.Events;

import me.dabsi_xx.Util.ConfigFile;
import me.dabsi_xx.Util.ItemFile;
import me.dabsi_xx.Util.MessageFile;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class OnInventoryClick implements Listener {
    private static ArrayList<String> itemList;
    private static ItemStack currentItem;

    @EventHandler
    public void onItemMove(InventoryClickEvent event) {
        Player player = ((Player) event.getWhoClicked());
        currentItem = event.getCurrentItem();
        itemList = (ArrayList<String>) ItemFile.get().getStringList("items");

        if (!ConfigFile.get().getBoolean("BlockMovement")) return;
        if (player.hasPermission("dpi.move")) return;
        if (!currentItem.hasItemMeta()) return;
        if (event.getInventory() instanceof AnvilInventory) return;

        if (itemList.contains(currentItem.getItemMeta().getDisplayName())) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', (MessageFile.get().get("Prefix") + " " + MessageFile.get().getString("CantMove").replaceAll("%item%", event.getCurrentItem().getItemMeta().getDisplayName()))));
            event.setCancelled(true);
        }
    }
}
