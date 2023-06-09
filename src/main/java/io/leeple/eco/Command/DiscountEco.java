package io.leeple.eco.Command;

import io.leeple.eco.Data.PlayerData;
import io.leeple.eco.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DiscountEco implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (args.length > 0) {
                YamlConfiguration config = PlayerData.Config(args, sender);
                int ecoToAdd = Integer.parseInt(args[2]);
                int configValue = Integer.parseInt(config.getString("eco"));
                int result = configValue - ecoToAdd;
                config.set("eco", result);
                Main.getPlugin().saveYamlConfiguration(player);
            }
        }
        return false;
    }
}
