package io.leeple.eco;

import io.leeple.eco.Command.EcoCommand;
import io.leeple.eco.Listener.CreateFile;
import io.leeple.eco.Utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static io.leeple.eco.Data.PlayerData.config;
import static io.leeple.eco.Data.PlayerData.playerFile;

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

    public void saveYamlConfiguration(Player player) {
        try {
            config.save(playerFile);
            player.sendMessage(ColorUtils.chat("&c설정이 저장 되었습니다"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createPlayerDefaults(Player player) {
        YamlConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);
        playerConfig.addDefault("eco", "0");
        playerConfig.options().copyDefaults(true);
        saveYamlConfiguration(player);
    }

    public File getUuidFolder() {
        return uuidFolder;
    }

    public static Main getPlugin() {
        return plugin;
    }
}
