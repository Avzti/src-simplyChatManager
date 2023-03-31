package simplychatmanager.listener;

import org.bukkit.ChatColor;
import simplychatmanager.other.MainSimplyChat;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;

import static simplychatmanager.cmds.ChatCommand.muted;

public class OnPlayerChat implements Listener {

    MainSimplyChat plugin;

    public OnPlayerChat(MainSimplyChat M) {
        plugin = M;
    }

    @EventHandler
    public void onPlayerChat(PlayerChatEvent e) {
        Player p = (Player) e.getPlayer();

        String messagefail = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("chat-config.message-fail"));

        if(!p.hasPermission(plugin.getConfig().getString("chat-config.permission-write-when-off"))) {
            if (muted) {
                e.setCancelled(true);
                p.sendMessage(messagefail);
            }
        }
    }
}
