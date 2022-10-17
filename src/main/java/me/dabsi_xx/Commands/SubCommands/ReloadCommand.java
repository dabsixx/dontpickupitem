package me.dabsi_xx.Commands.SubCommands;

import me.dabsi_xx.Commands.SubCommand;
import me.dabsi_xx.Util.ConfigFile;
import me.dabsi_xx.Util.ItemFile;
import me.dabsi_xx.Util.MessageFile;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ReloadCommand extends SubCommand {

    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return "Reloads the configuration files";
    }

    @Override
    public String syntax() {
        return ChatColor.translateAlternateColorCodes('&', (MessageFile.get().getString("Prefix") + " " + "/dpi reload [messages|config|items]"));
    }

    @Override
    public void perform(Player player, String[] args) {
        if (!player.hasPermission("dpi.reload")) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', (MessageFile.get().getString("Prefix") + " " + MessageFile.get().getString("NoPermission"))));
            return;
        }

        if (args.length > 1) {
            switch (args[1]) {
                case "messages":
                    MessageFile.reload();
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', (MessageFile.get().getString("Prefix") + " " + MessageFile.get().getString("ReloadSuccess"))).replaceAll("%file%", "messages.yml"));
                    return;
                case "config":
                    ConfigFile.reload();
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', (MessageFile.get().getString("Prefix") + " " + MessageFile.get().getString("ReloadSuccess"))).replaceAll("%file%", "config.yml"));
                    return;
                case "items":
                    ItemFile.reload();
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', (MessageFile.get().getString("Prefix") + " Unique " + MessageFile.get().getString("ReloadSuccess"))).replaceAll("%file%", "items.yml"));
                    return;
                default:
                    MessageFile.reload();
                    ConfigFile.reload();
                    ItemFile.reload();
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', (MessageFile.get().getString("Prefix") + " " + MessageFile.get().getString("ReloadSuccessAll"))));
            }
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', syntax()));
        }
    }
}
