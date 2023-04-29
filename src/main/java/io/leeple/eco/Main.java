package io.leeple.eco;

import io.leeple.eco.Command.EcoCommand;
import io.leeple.eco.Listener.CreateFile;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public final class Main extends JavaPlugin {

    private File uuidFolder;
    public static Main plugin;

    public void Plugins() {
        Bukkit.getPluginManager().registerEvents(new CreateFile(), this);
    }

    public void Command() {
        Bukkit.getPluginCommand("Eco").setExecutor(new EcoCommand());
    }

    public void Config() { saveConfig(); }

    @Override
    public void onEnable() {
        plugin = this;
        Config();
        Command();
        Plugins();
    }

    @Override
    public void onDisable() {
        saveDefaultConfig();
    }

    public void saveYamlConfiguration(YamlConfiguration config, YamlConfiguration file) {
        try {
            config.save(String.valueOf(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createPlayerDefaults(UUID uuid) {
        File playerFile = new File(uuidFolder, uuid.toString() + ".yml");
        YamlConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);
        playerConfig.addDefault("eco", "0");
        playerConfig.options().copyDefaults(true);
        saveYamlConfiguration(playerConfig, YamlConfiguration.loadConfiguration(playerFile));
    }

    public File getUuidFolder() {
        return uuidFolder;
    }

    public static Main getPlugin() {
        return plugin;
    }
}
