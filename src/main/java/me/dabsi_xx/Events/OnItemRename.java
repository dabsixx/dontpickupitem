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

public class OnItemRename implements Listener {
    private static ArrayList<String> itemList;
    private static ItemStack currentItem;

    @EventHandler
    public void OnAnvilClick (InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        itemList = (ArrayList<String>) ItemFile.get().getStringList("items");
        currentItem = event.getCurrentItem();

        if (ConfigFile.get().getBoolean("BlockRename") == false) { return; }
        if (player.hasPermission("dpi.rename")) { return; }
        if (!(event.getInventory() instanceof AnvilInventory)) { return; }

        if (itemList.contains(currentItem.getItemMeta().getDisplayName())) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', (MessageFile.get().get("Prefix") + " " + MessageFile.get().getString("CantRename").replaceAll("%item%", currentItem.getItemMeta().getDisplayName()))));
            event.setCancelled(true);
        }
    }
}
