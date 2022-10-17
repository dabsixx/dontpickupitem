package me.dabsi_xx.Commands.SubCommands;

import me.dabsi_xx.Commands.SubCommand;
import me.dabsi_xx.Util.ItemFile;
import me.dabsi_xx.Util.MessageFile;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class AddCommand extends SubCommand {
    private static List<String> itemList;
    private static ItemStack currentItem;

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescription() {
        return "Adds the name of the current item in hand to the blacklist";
    }

    @Override
    public String syntax() {
        return "/dpi add";
    }

    @Override
    public void perform(Player player, String[] args) {
        itemList = ItemFile.get().getStringList("items");
        currentItem = player.getInventory().getItemInMainHand();

        if (!player.hasPermission("dpi.add")) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', (MessageFile.get().getString("Prefix") + " " + MessageFile.get().getString("NoPermission"))));
            return;
        }

        if (itemList.contains(currentItem.getItemMeta().getDisplayName())) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', (MessageFile.get().getString("Prefix") + " " + (MessageFile.get().get("AddExisting"))).replaceAll("%item%", player.getInventory().getItemInMainHand().getItemMeta().getDisplayName())));
        } else {
            itemList.add(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName());
            ItemFile.get().set("items", itemList);
            ItemFile.save();
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', (MessageFile.get().getString("Prefix") + " " + (MessageFile.get().get("AddSuccess"))).replaceAll("%item%", player.getInventory().getItemInMainHand().getItemMeta().getDisplayName())));
        }
    }
}