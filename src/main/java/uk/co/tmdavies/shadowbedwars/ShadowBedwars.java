package uk.co.tmdavies.shadowbedwars;

import org.bukkit.plugin.java.JavaPlugin;
import uk.co.tmdavies.shadowbedwars.commands.BedwarsAdminCommand;
import uk.co.tmdavies.shadowbedwars.commands.LobbyCommand;
import uk.co.tmdavies.shadowbedwars.game.ShadowGame;
import uk.co.tmdavies.shadowbedwars.utils.Config;

public final class ShadowBedwars extends JavaPlugin {

    public Config data;
    public Config config;
    public Config lang;

    public double langVer = 1.0;

    public String pluginDir = "./plugins/ShadowBedwars";

    public ShadowGame api;

    @Override
    public void onEnable() {

        registerConfigs();
        setUpLang();

        registerCommands();

        api = new ShadowGame(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void registerCommands() {

        new BedwarsAdminCommand(this);
        new LobbyCommand(this);

    }

    public void registerConfigs() {

        data = new Config(this, pluginDir + "/data.yml");
        config = new Config(this, pluginDir + "/config.yml");
        lang = new Config(this, pluginDir + "/lang.yml");

    }

    public void setUpLang() {

        if (lang.getVersion() != langVer) {

            lang.set("Version", langVer);
            lang.set("Prefix", "&7&lShadow&4&lBedwars &8»&7");
            lang.set("Invalid-Permission", "%prefix% &cYou do not have permission to execute this command.");

            lang.saveConfig();

        }

    }

}
