package uk.co.tmdavies.shadowbedwars.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import uk.co.tmdavies.shadowbedwars.ShadowBedwars;
import uk.co.tmdavies.shadowbedwars.utils.Config;
import uk.co.tmdavies.shadowbedwars.utils.Utils;

import java.util.Locale;

public class BedwarsAdminCommand implements CommandExecutor {

    private final ShadowBedwars plugin;
    private Config data;
    private Config lang;

    public BedwarsAdminCommand(ShadowBedwars plugin) {

        this.plugin = plugin;
        this.data = plugin.data;
        this.lang = plugin.lang;

        plugin.getCommand("bedwarsadmin").setExecutor(this);

    }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {

        if (!(sender instanceof Player)) {

            sender.sendMessage(Utils.Chat("&cOnly players may execute this command."));
            return true;

        }

        Player p = (Player) sender;

        if (!p.hasPermission("shadowbedwars.admin")) {

            p.sendMessage(Utils.Chat(lang.getString("Invalid-Permission")
                .replace("%prefix%", Utils.Chat(lang.getString("Prefix")))));

        }

        switch(args.length) {

            case 1:

                switch(args[0].toLowerCase()) {

                    case "setlobby":

                        Location loc = p.getLocation();

                        data.set("Data.Lobby.World", loc.getWorld().getName());
                        data.set("Data.Lobby.X", loc.getX());
                        data.set("Data.Lobby.Y", loc.getY());
                        data.set("Data.Lobby.Z", loc.getZ());
                        data.saveConfig();

                        Utils.sendPlayerCenteredMessage(p, "&aLobby set!");

                        break;

                    case "lobby":

                        if (data.getString("Data.Lobby.World") == null) {

                            p.sendMessage(Utils.Chat("&cNo lobby location set."));
                            invalidArgs(p);

                        }

                        Location lobby = new Location(Bukkit.getWorld(
                                data.getString("Data.Lobby.World")),
                                data.getDouble("Data.Lobby.X"),
                                data.getDouble("Data.Lobby.Y"),
                                data.getDouble("Data.Lobby.Z")
                        );

                        Utils.sendPlayerCenteredMessage(p, "&aTeleport Successful");

                        p.teleport(lobby);

                        break;

                    case "clearbeds":

                        data.set("Data.Bed", null);
                        data.saveConfig();

                        Utils.sendPlayerCenteredMessage(p, "&aBeds cleared");

                        break;

                    case "clearspawns":

                        data.set("Data.Bed.Spawn", null);
                        data.saveConfig();

                        Utils.sendPlayerCenteredMessage(p, "&aBed spawns cleared");

                        break;

                    default:
                        invalidArgs(p);
                        break;
                }

                break;

            case 2:

                switch(args[0].toLowerCase()) {

                    case "setbed":

                        Location loc = p.getLocation();

                        int i = Integer.parseInt(args[1]);

                        data.set("Data.Bed." + i + ".World", loc.getWorld().getName());
                        data.set("Data.Bed." + i + ".X", loc.getX());
                        data.set("Data.Bed." + i + ".Y", loc.getY());
                        data.set("Data.Bed." + i + ".Z", loc.getZ());
                        data.saveConfig();

                        Utils.sendPlayerCenteredMessage(p, "&aBed " + i + " set.");

                        break;

                    case "setteamspawn":

                        Location spawn = p.getLocation();

                        int o = Integer.parseInt(args[1]);

                        data.set("Data.Bed." + o + ".Spawn.World", spawn.getWorld().getName());
                        data.set("Data.Bed." + o + ".Spawn.X", spawn.getX());
                        data.set("Data.Bed." + o + ".Spawn.Y", spawn.getY());
                        data.set("Data.Bed." + o + ".Spawn.Z", spawn.getZ());
                        data.saveConfig();

                        Utils.sendPlayerCenteredMessage(p, "&aBed " + o + " spawn set.");

                        break;

                    default:
                        invalidArgs(p);
                        break;
                }

                break;

            default:
                invalidArgs(p);
                break;
        }

        //bedwarsadmin setlobby|setteamspawn|setbed <#>

        return true;
    }

    public void invalidArgs(Player p) {

        Utils.sendPlayerCenteredMessage(p, "");
        Utils.sendPlayerCenteredMessage(p, "&7&lShadow&4&lBedwars");
        Utils.sendPlayerCenteredMessage(p, "");
        Utils.sendPlayerCenteredMessage(p, "&cInvalid Args: /bedwarsadmin <setlobby|setteamspawn|setbed|clearbeds|clearspawns|lobby> <#>");
        Utils.sendPlayerCenteredMessage(p, "");

    }

}
