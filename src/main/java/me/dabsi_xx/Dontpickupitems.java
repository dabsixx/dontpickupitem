package me.dabsi_xx;

import me.dabsi_xx.Commands.CommandManager;
import me.dabsi_xx.Events.OnInventoryClick;
import me.dabsi_xx.Events.OnItemDrop;
import me.dabsi_xx.Events.OnItemPickup;
import me.dabsi_xx.Events.OnItemRename;
import me.dabsi_xx.Util.ConfigFile;
import me.dabsi_xx.Util.ItemFile;
import me.dabsi_xx.Util.CooldownManager;
import me.dabsi_xx.Util.MessageFile;
import org.bukkit.plugin.java.JavaPlugin;

public final class Dontpickupitems extends JavaPlugin {
    public static CooldownManager cooldownManager;

    @Override
    public void onEnable() {
        ItemFile.setup(this);
        ItemFile.get().options().copyDefaults(true);
        ItemFile.save();

        MessageFile.setup(this);
        MessageFile.get().options().copyDefaults(true);
        MessageFile.save();

        ConfigFile.setup(this);
        ConfigFile.get().options().copyDefaults(true);
        ConfigFile.save();

        cooldownManager = new CooldownManager(this);

        getCommand("dpi").setExecutor(new CommandManager());
        getCommand("dpi").setTabCompleter(new CommandManager());

        getServer().getPluginManager().registerEvents(new OnItemPickup(), this);
        getServer().getPluginManager().registerEvents(new OnInventoryClick(), this);
        getServer().getPluginManager().registerEvents(new OnItemDrop(), this);
        getServer().getPluginManager().registerEvents(new OnItemRename(), this);

    }

    public static CooldownManager getCooldownManager() {
        return cooldownManager;
    }
}
