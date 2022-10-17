package me.dabsi_xx.Util;

import me.dabsi_xx.Dontpickupitems;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ItemFile {
    private static File file;
    private static FileConfiguration config;
    private static @NotNull List<String> itemList;
    private static String itemName;
    private static int counter;

    public static void setup(Dontpickupitems plugin) {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("DontPickupItems").getDataFolder(), "items.yml");

        if (!file.exists()) {
            plugin.saveResource("items.yml", false);
        }
        if (file.exists()) {

        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get() {
        return config;
    }

    public static List<String> getItemList() {
        itemList = (ArrayList<String>) ItemFile.get().getStringList("items");
        for (counter = 0; counter < itemList.size(); counter++) {
            itemName = itemList.get(counter);
        }
        return itemList;
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
