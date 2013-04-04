package com.kinnunennetwork.road;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Road extends JavaPlugin
{	
    private String dataDir;
    public RoadPlayerListener playerListener;
	
    public Road()
    {
    }
    public void onEnable()
    {	
        this.playerListener = new RoadPlayerListener(this, this.playerListener, this.dataDir);
        getServer().getPluginManager().registerEvents(playerListener, this);
        PluginDescriptionFile pdfFile = getDescription();
        System.out.println("[Road] v"+pdfFile.getVersion()+" Enabled");
        final FileConfiguration config = this.getConfig();
        config.addDefault("highway.enabled", true);
        config.addDefault("highway.speed", 4);
        config.addDefault("highway.top", 20);
        config.addDefault("highway.bottom", 41);
        config.addDefault("boosterl.enabled", true);
        config.addDefault("boosterl.pwr", 5);
        config.addDefault("boosterl.top", 57);
        config.addDefault("boosterl.middle", 20);
        config.addDefault("boosterl.bottom", 22);
        config.addDefault("Aerialfp.enabled", true);
        config.addDefault("Aerialfp.speed", 2);
        config.addDefault("Aerialfp.this", 93);
        config.addDefault("Aerialfp.top", 45);
        config.addDefault("Aerialfp.bottom", 15); 
        config.options().copyDefaults(true);
        saveConfig();
    }
    
    public void onDisable()
    {
        System.out.println("[Road] Road Disabled!");
    }
    
}
