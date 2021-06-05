package uk.co.tmdavies.shadowbedwars.game.events.customevents;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import uk.co.tmdavies.shadowbedwars.game.objects.Game;

public class GameStartEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private Game game;

    public GameStartEvent(Game game) {

        this.game = game;

    }

    public Game getGame() {

        return game;

    }

    public static HandlerList getHandlersList() {

        return handlers;

    }

    @Override
    public HandlerList getHandlers() {

        return handlers;

    }
}
