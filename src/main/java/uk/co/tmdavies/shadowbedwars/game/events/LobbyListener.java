package uk.co.tmdavies.shadowbedwars.game.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import uk.co.tmdavies.shadowbedwars.ShadowBedwars;
import uk.co.tmdavies.shadowbedwars.game.events.customevents.GameLobbyEvent;
import uk.co.tmdavies.shadowbedwars.game.events.customevents.GameStartEvent;
import uk.co.tmdavies.shadowbedwars.game.objects.Game;
import uk.co.tmdavies.shadowbedwars.game.objects.Lobby;
import uk.co.tmdavies.shadowbedwars.game.objects.Team;
import uk.co.tmdavies.shadowbedwars.utils.Config;

public class LobbyListener implements Listener {

    private Config data;

    public LobbyListener(ShadowBedwars plugin) {

        this.data = plugin.data;

        Bukkit.getPluginManager().registerEvents(this, plugin);

    }

    public void onLobbyJoin(GameLobbyEvent e) {

        Lobby lobby = e.getLobby();

        if (lobby.getLobby().size() >= data.getStringList("Data.Bed").size()) {

            lobby.startCountdown();

        }

    }

    public void onGameStart(GameStartEvent e) {

        Game game = e.getGame();

        for (Team team : game.getTeams()) {

            for (Player p : team.getTeamMembers()) {

                p.teleport(team.getSpawnLocation());

                team.getBedLocation().getBlock().setType(Material.BLACK_BED);

                JavaPlugin.getPlugin(ShadowBedwars.class).api.bedStorage.put(team.getName(), team.getBedLocation().getBlock());

            }

        }

    }


}
