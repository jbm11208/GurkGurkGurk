package com.jbm11208.gurkgurkgurk.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;


// Handles the /gurk command.
public class GurkCommand implements CommandExecutor, TabCompleter {

  private static final List<String> SUBCOMMANDS =
          List.of("pickle", "waffle");

  private static final String USAGE = "Usage: /%s [pickle|waffle]";

  /**
   * Executes the /gurk command.
   *
   * @param sender  the command sender
   * @param command the command
   * @param label   the alias used
   * @param args    the arguments
   * @return true if handled
   */
  @Override
  public boolean onCommand(final @NotNull CommandSender sender, final @NotNull Command command,
                           final @NotNull String label, final String[] args) {
    if (!(sender instanceof Player player)) {
      sender.sendMessage("Only players can use this command.");
      return true;
    }

    if (args.length < 1) {
      player.sendMessage(String.format(USAGE, label));
      return true;
    }

    final String sub = args[0].toLowerCase(Locale.ROOT);

    switch (sub) {
      case "pickle":
        {
        final ItemStack item = new ItemStack(Material.SEA_PICKLE, 1);
        player.getInventory().addItem(item);
        player.sendMessage("You received 1 sea pickle.");
        return true;
        }
      case "waffle":
        {
        final ItemStack item = new ItemStack(Material.SEA_PICKLE, 1);
        final ItemMeta meta = item.getItemMeta();
        if (meta != null) {
          meta.setDisplayName("waffle");
          final boolean applied = item.setItemMeta(meta);
          if (!applied) {
            player.sendMessage("Could not apply item name to the sea pickle.");
          }
        }
        player.getInventory().addItem(item);
        player.sendMessage("You received a \"waffle\".");
        return true;
        }
      default:
        {
        player.sendMessage(String.format(USAGE, label));
        return true;
        }
    }
  }

  /**
   * Provides tab completion for the first argument (subcommand).
   *
   * @param sender the command sender
   * @param command the command
   * @param alias the alias used
   * @param args the current args
   * @return a list of matching subcommands
   */
  @Override
  public List<String> onTabComplete(final @NotNull CommandSender sender,
                                    final @NotNull Command command,
                                    final @NotNull String alias, final String[] args) {
    if (args.length == 1) {
      final String prefix = args[0].toLowerCase(Locale.ROOT);
      final List<String> matches = new ArrayList<>();
      for (final String s : SUBCOMMANDS) {
        if (s.startsWith(prefix)) {
          matches.add(s);
        }
      }
      return matches;
    }
    return Collections.emptyList();
  }
}