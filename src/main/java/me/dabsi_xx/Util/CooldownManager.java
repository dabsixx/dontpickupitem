package me.dabsi_xx.Util;

import me.dabsi_xx.Dontpickupitems;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManager {

    private Map<UUID, Integer> playerCooldownMap = new HashMap<>();

    public CooldownManager(Dontpickupitems plugin) {
        new BukkitRunnable() {

            @Override
            public void run() {
                for (UUID uuid : playerCooldownMap.keySet()) {
                    if (playerCooldownMap.get(uuid) == 1) {
                        playerCooldownMap.remove(uuid);
                        continue;
                    }

                    playerCooldownMap.put(uuid,playerCooldownMap.get(uuid)-1);
                }
            }
        }.runTaskTimer(plugin, 0, 20);
    }

    public void addPlayerToMap(Player player, Integer time) {
        playerCooldownMap.put(player.getUniqueId(), time);
    }

    public boolean isPlayerInCooldown(Player player) {
        return playerCooldownMap.containsKey(player.getUniqueId());
    }
}
