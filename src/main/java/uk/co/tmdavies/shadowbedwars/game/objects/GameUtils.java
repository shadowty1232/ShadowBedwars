package uk.co.tmdavies.shadowbedwars.game.objects;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameUtils {

    public static List<Team> makeTeams(int amount, List<Player> lobby) {

        List<Team> teamList = new ArrayList<>();
        List<Player> tempLobby = new ArrayList<>(lobby);

        for (int i = 1; i < amount; i++) teamList.add(new Team(String.valueOf(i)));

        int lobbyAmount = lobby.size();
        int teamAmount = (int) Math.floor(lobbyAmount/amount);
        int index = 0;

        while (teamList.get(index).getTeamMembers().size() != teamAmount) {

            for (int i = 0; i < teamAmount; i++) {

                if (tempLobby.get(i) == null) {

                    teamList.get(index).addTeamMember(tempLobby.get(i-1));

                    continue;

                }

                teamList.get(index).addTeamMember(tempLobby.get(i));

            }

            teamList.get(index).getTeamMembers().forEach(tempLobby::remove);

            if (index != lobbyAmount) index++;

        }

        for (Team team : teamList) {

            List<Player> playerList = new ArrayList<>();

            for (Player p : team.getTeamMembers()) {

                if (teamList.contains(p)) continue;

                playerList.add(p);

            }

            team.setTeamMembers(playerList);

        }

        return teamList;

    }

}
