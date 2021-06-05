package uk.co.tmdavies.shadowbedwars.game.events.customevents;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import uk.co.tmdavies.shadowbedwars.game.objects.Lobby;

public class GameLobbyEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private Lobby lobby;
    private Player player;

    public GameLobbyEvent(Lobby lobby, Player player) {

        this.lobby = lobby;
        this.player = player;

    }

    public Lobby getLobby() {

        return lobby;

    }

    public Player getPlayer() {

        return player;

    }

    public static HandlerList getHandlersList() {

        return handlers;

    }

    @Override
    public HandlerList getHandlers() {

        return handlers;

    }

}
