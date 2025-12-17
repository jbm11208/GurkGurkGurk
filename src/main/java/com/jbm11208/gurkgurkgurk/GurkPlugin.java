package com.jbm11208.gurkgurkgurk;

import com.jbm11208.gurkgurkgurk.commands.GurkCommand;
import io.papermc.lib.PaperLib;
import java.util.Objects;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main plugin entrypoint for GurkPlugin.
 * Registers commands and initializes configuration on enable.
 */
public class GurkPlugin extends JavaPlugin {

  /**
   * Called by the server when the plugin is enabled.
   * Registers the /gurk command executor and tab completer.
   */
  @Override
  public void onEnable() {
    // Suggest using Paper
    PaperLib.suggestPaper(this);

    // Keep default config
    saveDefaultConfig();

    // Register /gurk command executor and tab completer
    final GurkCommand gurkCommand = new GurkCommand();
    final PluginCommand gurk = Objects.requireNonNull(
        getCommand("gurk"),
        "Command 'gurk' not found in plugin.yml"
    );
    gurk.setExecutor(gurkCommand);
    gurk.setTabCompleter(gurkCommand);
  }
}
