package simplychatmanager.other;

import simplychatmanager.cmds.ChatCommand;
import simplychatmanager.listener.OnPlayerChat;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public final class MainSimplyChat extends JavaPlugin implements CommandExecutor {

    MainSimplyChat plugin;

    @Override
    public void onEnable() {
        getLogger().info("Plugin is running");

        getConfig().options().copyDefaults(true);
        saveConfig();

        new ChatCommand(this);

        getServer().getPluginManager().registerEvents(new OnPlayerChat(this), this);
    }
}
