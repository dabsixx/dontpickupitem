package me.dabsi_xx.Commands;

import me.dabsi_xx.Commands.SubCommands.AddCommand;
import me.dabsi_xx.Commands.SubCommands.ReloadCommand;
import me.dabsi_xx.Commands.SubCommands.SwitchCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandManager implements TabExecutor {

    private ArrayList<SubCommand> subCommands = new ArrayList<>();

    public CommandManager() {
        subCommands.add(new AddCommand());
        subCommands.add(new ReloadCommand());
        subCommands.add(new SwitchCommand());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length > 0) {
                for (int i = 0; i < getSubCommands().size(); i++) {
                    if (args[0].equalsIgnoreCase(getSubCommands().get(i).getName())) {
                            getSubCommands().get(i).perform(player, args);
                    }
                }

            } else {
                player.sendMessage("Usage: /dpi [reload|switch|add]");
            }
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            List<String> commandArgs = Arrays.asList("reload", "add","switch");
            return commandArgs;
        }
        if (args[0].equals("reload")) {
            List<String> commandArgs = Arrays.asList("items", "messages", "config", "all");
            return commandArgs;
        }
        if (args[0].equals("switch")) {
            List<String> commandArgs = Arrays.asList("pickup", "drop", "move", "rename");
            return commandArgs;
        }
        return null;
    }

    public ArrayList<SubCommand> getSubCommands() {
        return subCommands;
    }
}
