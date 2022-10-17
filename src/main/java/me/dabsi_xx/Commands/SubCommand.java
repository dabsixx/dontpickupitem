package me.dabsi_xx.Commands;

import org.bukkit.entity.Player;

public abstract class SubCommand {
    public abstract String getName();

    public abstract String getDescription();

    public abstract String syntax();

    public abstract void perform(Player player, String[] args);
}
