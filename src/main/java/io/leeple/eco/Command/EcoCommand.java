package io.leeple.eco.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class EcoCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (args.length > 0) {
                String arg = args[1];
                switch (arg) {
                    case "add", "추가", "count" -> {
                        CountEco CE = new CountEco();
                        CE.onCommand(sender, command, label, args);
                    }

                    case "정보", "info", "확인" -> {
                        EcoInfo EI = new EcoInfo();
                        EI.onCommand(sender, command, label, args);
                    }
                }
            }
        }
        return false;
    }
}
