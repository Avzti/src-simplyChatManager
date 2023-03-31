package simplychatmanager.cmds;

import simplychatmanager.other.MainSimplyChat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ChatCommand implements CommandExecutor {

    MainSimplyChat plugin;

    public ChatCommand(MainSimplyChat m) {
        plugin = m;
        m.getCommand("chat").setExecutor(this);
    }

    public static boolean muted = false;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        String clear = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("chat-config.chat-clear-message"));
        String off = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("chat-config.chat-off-message"));
        String on = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("chat-config.chat-on-message"));

        String alreadyoff = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("chat-config.chat-already-off"));
        String alreadyon = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("chat-config.chat-already-on"));
        String use = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("chat-config.message-use"));
        String perm = plugin.getConfig().getString("chat-config.permission");
        String permissionmess = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("chat-config.permission-message"));

        if (sender.hasPermission(perm)) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("off")) {
                    if (!muted) {
                        muted = true;

                        plugin.getConfig().set("chat-config.is-muted", true);
                        plugin.saveConfig();

                        Bukkit.broadcastMessage(off);
                    } else if (muted) {
                        sender.sendMessage(alreadyoff);
                    }
                } else if (args[0].equalsIgnoreCase("on")) {
                    if (muted) {
                        muted = false;

                        plugin.getConfig().set("chat-config.is-muted", false);
                        plugin.saveConfig();

                        Bukkit.broadcastMessage(on);
                    } else if (!muted) {
                        sender.sendMessage(alreadyon);
                    }
                } else if (args[0].equalsIgnoreCase("clear")) {
                    for (Player ps : Bukkit.getOnlinePlayers()) {
                        for (int i = 1; i < 101; i++) {
                            ps.sendMessage(" ");
                        }
                        Bukkit.broadcastMessage(clear);
                    }
                } else {
                    sender.sendMessage(use);
                }
            } else {
                sender.sendMessage(use);
            }
        } else {
            sender.sendMessage(permissionmess);
        }
        return false;
    }
}
