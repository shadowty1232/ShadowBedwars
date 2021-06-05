package uk.co.tmdavies.shadowbedwars.game;

import org.bukkit.block.Block;
import uk.co.tmdavies.shadowbedwars.ShadowBedwars;
import uk.co.tmdavies.shadowbedwars.game.events.LobbyListener;
import uk.co.tmdavies.shadowbedwars.game.objects.Lobby;

import java.util.HashMap;

public class ShadowGame {

    public HashMap<Integer, Lobby> lobbyStorage;
    public HashMap<String, Block> bedStorage;

    public ShadowGame(ShadowBedwars plugin) {

        onEnable(plugin);

    }

    public void onEnable(ShadowBedwars plugin) {

        lobbyStorage = new HashMap<>();
        bedStorage = new HashMap<>();

        new LobbyListener(plugin);

    }

}
