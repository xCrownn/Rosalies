package me.xcrownn.rosalies.Events;

import de.leonhard.storage.Yaml;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.Objects;

public class RosePlaceEvent implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Yaml messages = new Yaml("Messages", "plugins/Rosalies");

        if (event.getBlockPlaced().getType().equals(Material.ROSE_BUSH) && Objects.requireNonNull(event.getItemInHand().getItemMeta()).hasLore()) {
            event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("Place_Rose_Error")));
            event.setCancelled(true);
            event.setBuild(false);

        }
    }

}
