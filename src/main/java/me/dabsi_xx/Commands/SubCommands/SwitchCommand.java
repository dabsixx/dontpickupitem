package me.dabsi_xx.Commands.SubCommands;

import me.dabsi_xx.Commands.SubCommand;
import me.dabsi_xx.Util.ConfigFile;
import me.dabsi_xx.Util.ItemFile;
import me.dabsi_xx.Util.MessageFile;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class SwitchCommand extends SubCommand {

    @Override
    public String getName() {
        return "switch";
    }

    @Override
    public String getDescription() {
        return "Switches configs on/off";
    }

    @Override
    public String syntax() {
        return "/dpi switch [rename|pickup|drop|move]";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (!player.hasPermission("dpi.switch")) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', (MessageFile.get().getString("Prefix") + " " + MessageFile.get().getString("NoPermission"))));
            return;
        }

        if (args.length > 1) {
            switch (args[1]) {
                case "rename":
                    if (ConfigFile.get().getBoolean("BlockRename") == true) {
                        ConfigFile.get().set("BlockRename", false);
                        ConfigFile.save();
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFile.get().getString("Prefix") + " " + (MessageFile.get().getString("SwitchSuccess").replace("%config%", "Block Renaming").replace("%status%", String.valueOf(ConfigFile.get().getBoolean("BlockRename"))))));
                        return;
                    } else if (ConfigFile.get().getBoolean("BlockRename") == false) {
                        ConfigFile.get().set("BlockRename", true);
                        ConfigFile.save();
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFile.get().getString("Prefix") + " " + (MessageFile.get().getString("SwitchSuccess").replace("%config%", "Block Renaming").replace("%status%", String.valueOf(ConfigFile.get().getBoolean("BlockRename"))))));
                        return;
                    }
                case "pickup":
                    if (ConfigFile.get().getBoolean("BlockPickup") == true) {
                        ConfigFile.get().set("BlockPickup", false);
                        ConfigFile.save();
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFile.get().getString("Prefix") + " " + (MessageFile.get().getString("SwitchSuccess").replace("%config%", "Block Picking Up").replace("%status%", String.valueOf(ConfigFile.get().getBoolean("BlockPickup"))))));
                        return;
                    } else if (ConfigFile.get().getBoolean("BlockPickup") == false) {
                        ConfigFile.get().set("BlockPickup", true);
                        ConfigFile.save();
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFile.get().getString("Prefix") + " " + (MessageFile.get().getString("SwitchSuccess").replace("%config%", "Block Picking Up").replace("%status%", String.valueOf(ConfigFile.get().getBoolean("BlockPickup"))))));
                        return;
                    }
                case "drop":
                    if (ConfigFile.get().getBoolean("BlockDrop") == true) {
                        ConfigFile.get().set("BlockDrop", false);
                        ConfigFile.save();
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFile.get().getString("Prefix") + " " + (MessageFile.get().getString("SwitchSuccess").replace("%config%", "Block Dropping").replace("%status%", String.valueOf(ConfigFile.get().getBoolean("BlockDrop"))))));
                        return;
                    } else if (ConfigFile.get().getBoolean("BlockDrop") == false) {
                        ConfigFile.get().set("BlockDrop", true);
                        ConfigFile.save();
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFile.get().getString("Prefix") + " " + (MessageFile.get().getString("SwitchSuccess").replace("%config%", "Block Dropping").replace("%status%", String.valueOf(ConfigFile.get().getBoolean("BlockDrop"))))));
                        return;
                    }
                case "move":
                    if (ConfigFile.get().getBoolean("BlockMovement") == true) {
                        ConfigFile.get().set("BlockMovement", false);
                        ConfigFile.save();
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFile.get().getString("Prefix") + " " + (MessageFile.get().getString("SwitchSuccess").replace("%config%", "Block Moving").replace("%status%", String.valueOf(ConfigFile.get().getBoolean("BlockMovement"))))));
                        return;
                    } else if (ConfigFile.get().getBoolean("BlockMovement") == false) {
                        ConfigFile.get().set("BlockMovement", true);
                        ConfigFile.save();
                        ConfigurationSection itemList = ItemFile.get().getConfigurationSection("items");
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', MessageFile.get().getString("Prefix") + " " + (MessageFile.get().getString("SwitchSuccess").replace("%config%", "Block Moving").replace("%status%", String.valueOf(ConfigFile.get().getBoolean("BlockMovement"))))));
                        return;
                    }
                default:
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', syntax()));
            }
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', syntax()));
        }
    }
}
