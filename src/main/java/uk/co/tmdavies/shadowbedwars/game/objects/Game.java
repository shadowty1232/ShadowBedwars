package uk.co.tmdavies.shadowbedwars.game.objects;

import org.bukkit.entity.Player;

import java.util.List;

public class Game {

    private List<Player> players;
    private List<Team> teams;

    public Game(List<Team> teamList, List<Player> playerList) {

        this.players = playerList;
        this.teams = teamList;

    }

    public List<Player> getPlayers() {

        return players;

    }

    public List<Team> getTeams() {

        return teams;

    }
}
