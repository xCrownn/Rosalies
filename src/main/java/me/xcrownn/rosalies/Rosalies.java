package me.xcrownn.rosalies;

import de.leonhard.storage.Yaml;
import me.xcrownn.rosalies.Commands.RPgiveCommand;
import me.xcrownn.rosalies.Events.RosePlaceEvent;
import me.xcrownn.rosalies.Events.ShirtAnvilDisable;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class Rosalies extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("rpgive").setExecutor(new RPgiveCommand());
        getCommand("rpgive").setTabCompleter(new RPgiveCommand());
        getServer().getPluginManager().registerEvents(new RosePlaceEvent(), this);
        getServer().getPluginManager().registerEvents(new ShirtAnvilDisable(), this);

        Yaml messages = new Yaml("Messages", "plugins/Rosalies");
        messages.getOrSetDefault("No_Perms", "&cYou do not have permission to use this command!");
        messages.getOrSetDefault("Not_Enough_Arguments", "&cNot enough arguments. &7Usage: /rpgive <rose/shirt> <player>");
        messages.getOrSetDefault("Anvil_Edit_Attempt", "&cYou may not edit this item!");
        messages.getOrSetDefault("Player_Not_Online", "&cThat player is not online!");
        messages.getOrSetDefault("Place_Rose_Error", "&cYou may not place a rose!");
        messages.getOrSetDefault("No_Player_Entered", "&cYou must enter a players name!");
        messages.getOrSetDefault("Given_Shirt_Item", "&aYou have been given a shirt.");
        messages.getOrSetDefault("Given_Rose_Item", "&aYou have been given a rose.");
        //No_Player_Entered Given_Shirt_Item

        Yaml config = new Yaml("Config", "plugins/Rosalies");
        config.getOrSetDefault("Rose.Rose_Name", "&c&lRosalie's Rose");
        config.getOrSetDefault("Shirt.Shirt_Name", "&c&lA Red Shirt.");


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}