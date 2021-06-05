package uk.co.tmdavies.shadowbedwars.game.objects;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import uk.co.tmdavies.shadowbedwars.ShadowBedwars;
import uk.co.tmdavies.shadowbedwars.game.events.customevents.GameLobbyEvent;
import uk.co.tmdavies.shadowbedwars.game.events.customevents.GameStartEvent;
import uk.co.tmdavies.shadowbedwars.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Lobby {

    double countdown;
    List<Player> lobby;
    final ShadowBedwars plugin = JavaPlugin.getPlugin(ShadowBedwars.class);

    public Lobby(double countdown) {

        this.countdown = countdown;
        this.lobby = new ArrayList<>();

    }

    public double getCountdown() {

        return countdown;

    }

    public List<Player> getLobby() {

        return lobby;

    }

    public void addPlayer(Player p) {

        this.lobby.add(p);

        Bukkit.getPluginManager().callEvent(new GameLobbyEvent(this, p));

    }

    public void startCountdown() {

        AtomicReference<Double> count = new AtomicReference<>(getCountdown());

        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {



            for (Player p : this.getLobby()) {

                p.sendTitle(Utils.Chat("&e&lStarting in"), Utils.Chat("&c" + count));

                if (count.get() == 0.0) {

                    int amount = plugin.data.getStringList("Data.Bed").size();

                    Bukkit.getPluginManager().callEvent(new GameStartEvent(new Game(GameUtils.makeTeams(amount, this.getLobby()), this.getLobby())));
                }

            }

            count.getAndSet(count.get() - 0.1);

        });

    }

}
