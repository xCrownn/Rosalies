package me.xcrownn.rosalies.Events;

import de.leonhard.storage.Yaml;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;

import java.util.Objects;

public class ShirtAnvilDisable implements Listener {

    @EventHandler
    public void invClickEvent(InventoryClickEvent event) {
        if (event.getInventory().getType() == InventoryType.ANVIL) {
            Yaml messages = new Yaml("Messages", "plugins/Rosalies");
            if (Objects.equals(Objects.requireNonNull(event.getCurrentItem()).getType(), Material.LEATHER_CHESTPLATE) || event.getCurrentItem().getType().equals(Material.ROSE_BUSH)) {
                if (Objects.requireNonNull(Objects.requireNonNull(event.getCurrentItem()).getItemMeta()).hasLore()) {
                    Player player = (Player) event.getWhoClicked();
                    event.setCancelled(true);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("Anvil_Edit_Attempt")));
                }
            }

        }
    }
}
