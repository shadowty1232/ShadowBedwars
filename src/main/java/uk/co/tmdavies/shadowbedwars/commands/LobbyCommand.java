package uk.co.tmdavies.shadowbedwars.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import uk.co.tmdavies.shadowbedwars.ShadowBedwars;
import uk.co.tmdavies.shadowbedwars.game.objects.Lobby;
import uk.co.tmdavies.shadowbedwars.utils.Config;
import uk.co.tmdavies.shadowbedwars.utils.Utils;

import java.util.HashMap;

public class LobbyCommand implements CommandExecutor {

    private final ShadowBedwars plugin;
    private Config lang;
    private HashMap<Integer, Lobby> lobbyStorage;

    public LobbyCommand(ShadowBedwars plugin) {

        this.plugin = plugin;
        this.lang = plugin.lang;
        this.lobbyStorage = plugin.api.lobbyStorage;

        plugin.getCommand("lobby").setExecutor(this);

    }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {

        if (!(sender instanceof Player)) {

            sender.sendMessage(Utils.Chat("&cOnly players may execute this command."));
            return true;

        }

        Player p = (Player) sender;

        if (!p.hasPermission("shadowbedwars.lobby")) {

            p.sendMessage(Utils.Chat(lang.getString("Invalid-Permission")
                    .replace("%prefix%", Utils.Chat(lang.getString("Prefix")))));

        }

        if (args.length == 1) {

            switch (args[0].toLowerCase()) {

                case "join":

                    if (lobbyStorage.isEmpty()) {

                        Lobby lobby = new Lobby(10.0);

                        lobbyStorage.put(lobbyStorage.size() + 1, lobby);

                        lobby.addPlayer(p);

                    } else {

                        lobbyStorage.get(1).addPlayer(p);

                    }

            }
        }
        invalidArgs(p);

        return true;

    }

    public void invalidArgs(Player p) {

        Utils.sendPlayerCenteredMessage(p, "");
        Utils.sendPlayerCenteredMessage(p, "&7&lShadow&4&lBedwars");
        Utils.sendPlayerCenteredMessage(p, "");
        Utils.sendPlayerCenteredMessage(p, "&cInvalid Args: /lobby <join|leave>");
        Utils.sendPlayerCenteredMessage(p, "");

    }

}
