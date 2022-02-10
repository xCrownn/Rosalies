package me.xcrownn.rosalies.Commands;

import de.leonhard.storage.Yaml;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RPgiveCommand implements CommandExecutor, TabCompleter {

    public final List<String> LIST = Arrays.asList("rose", "shirt");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("rpgive")) {
            Yaml messages = new Yaml("Messages", "plugins/Rosalies");
            Yaml config = new Yaml("Config", "plugins/Rosalies");
            if (sender.hasPermission("RosaliesPlugin.GiveRose") || sender.isOp()) {
                if (args.length == 2) {
                    if (args.length == 1) {
                        sender.sendMessage(messages.getString("No_Player_Entered"));
                    } else {
                        Player target = Bukkit.getOfflinePlayer(args[1]).getPlayer();
                        if (target.isOnline()) {

                            if (args[0].equalsIgnoreCase("rose")) {
                                ItemStack rose = new ItemStack(Material.ROSE_BUSH);
                                ItemMeta roseMeta = rose.getItemMeta();
                                ArrayList<String> roseList = new ArrayList<>();
                                roseList.add("&7Unplaceable");
                                roseList.add(" ");
                                roseList.add("&4&oFebruary is CHD Awareness Month");
                                roseList.add("&4&oThis item was purchased by &c&o" + target.getDisplayName() + "&4&o.");
                                roseList.add("&4&oAll the proceeds goes towards");
                                roseList.add("&4&oThe American Heart Association. <3 ");
                                roseList.replaceAll(s -> ChatColor.translateAlternateColorCodes('&', s));
                                roseMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getString("Rose.Rose_Name")));
                                roseMeta.setLore(roseList);
                                roseMeta.addEnchant(Enchantment.DURABILITY, 10, true);
                                roseMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                                rose.setItemMeta(roseMeta);
                                target.getInventory().addItem(rose);
                                target.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("Given_Rose_Item")));
                            }
                            if (args[0].equalsIgnoreCase("shirt")) {
                                ItemStack shirt = new ItemStack(Material.LEATHER_CHESTPLATE);
                                LeatherArmorMeta shirtMeta = (LeatherArmorMeta) shirt.getItemMeta();

                                ArrayList<String> shirtList = new ArrayList<>();
                                shirtList.add("&7Unbreakable");
                                shirtList.add(" ");
                                shirtList.add("&4&oWearing red helps raise awareness");
                                shirtList.add("&4&oof cardiovascular disease.");
                                shirtList.add("&4&oThis shirt was purchased by &c&o" + target.getDisplayName() + "&4&o.");
                                shirtList.replaceAll(s -> ChatColor.translateAlternateColorCodes('&', s));

                                shirtMeta.setColor(Color.RED);
                                shirtMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getString("Shirt.Shirt_Name")));
                                shirtMeta.setLore(shirtList);
                                shirtMeta.addEnchant(Enchantment.DURABILITY, 1, true);
                                shirtMeta.setUnbreakable(true);
                                shirtMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                                shirt.setItemMeta(shirtMeta);
                                target.getInventory().addItem(shirt);
                                target.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("Given_Shirt_Item")));

                            }
                        } else sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "Player_Not_Online"));
                    }

                } else sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("Not_Enough_Arguments")));
            } else sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("No_Perms")));
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return (args.length <= 1) ? StringUtil.copyPartialMatches(args[0], LIST, new ArrayList<>()) : null;
    }
}
