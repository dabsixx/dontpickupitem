package me.dabsi_xx.Util;

import me.dabsi_xx.Dontpickupitems;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigFile {
    private static File file;
    private static FileConfiguration config;

    public static void setup(Dontpickupitems plugin) {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("DontPickupItems").getDataFolder(), "config.yml");

        if (!file.exists()) {
            plugin.saveResource("config.yml", false);
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get() {
        return config;
    }

    public static void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            Bukkit.getConsoleSender().sendMessage("Couldn't save file");
        }
    }

    public static void reload() {
        config = YamlConfiguration.loadConfiguration(file);
    }
}
