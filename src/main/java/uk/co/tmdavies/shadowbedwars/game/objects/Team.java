package uk.co.tmdavies.shadowbedwars.game.objects;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import uk.co.tmdavies.shadowbedwars.ShadowBedwars;
import uk.co.tmdavies.shadowbedwars.utils.Config;
import uk.co.tmdavies.shadowbedwars.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private String teamName;
    private List<Player> teamMembers;
    private int teamPoints;
    private String teamPrefix;


    public Team(String name) {

        this.teamName = name;
        this.teamMembers = new ArrayList<>();
        this.teamPoints = 0;
        this.teamPrefix = name + " Team";

    }

    public String getName() {

        return teamName;

    }

    public List<Player> getTeamMembers() {

        return teamMembers;

    }

    public int getPoints() {

        return teamPoints;

    }

    public String getPrefix() {

        return teamPrefix;

    }

    public void addTeamMember(Player p) {

        if (!teamMembers.contains(p)) {

            teamMembers.add(p);

        }

    }

    public void removeTeamMember(Player p) {

        if (teamMembers.contains(p)) {

            teamMembers.remove(p);

        }

    }

    public void setTeamMembers(List<Player> players) {

        this.teamMembers = players;

    }

    public void addPoints(int amount) {

        teamPoints += amount;

    }

    public void removePoints(int amount) {

        teamPoints -= amount;

    }

    public void sendTeamMessage(String message) {

        if (teamMembers.isEmpty()) return;

        for (Player p : teamMembers) {

            p.sendMessage(Utils.Chat(message));

        }

    }

    public void sendTeamActionBar(String message) {

        if (teamMembers.isEmpty()) return;

        for (Player p : teamMembers) {

            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(Utils.Chat(message)));

        }

    }

    public Location getBedLocation() {

        Config data = JavaPlugin.getPlugin(ShadowBedwars.class).data;

        return new Location(Bukkit.getWorld(
                data.getString("Data.Bed." + teamName + ".World")),
                data.getDouble("Data.Bed." + teamName + ".X"),
                data.getDouble("Data.Bed." + teamName + ".Y"),
                data.getDouble("Data.Bed." + teamName + ".Z")
        );

    }

    public Location getSpawnLocation() {

        Config data = JavaPlugin.getPlugin(ShadowBedwars.class).data;

        return new Location(Bukkit.getWorld(
                data.getString("Data.Bed." + teamName + ".Spawn.World")),
                data.getDouble("Data.Bed." + teamName + ".Spawn.X"),
                data.getDouble("Data.Bed." + teamName + ".Spawn.Y"),
                data.getDouble("Data.Bed." + teamName + ".Spawn.Z")
        );
    }

}