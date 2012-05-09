package com.kinnunennetwork.road;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Road extends JavaPlugin
{	
	static File configFile;
	static FileConfiguration config;
	static int htop;

	
    public Road()
    {
    }
    @EventHandler(priority = EventPriority.LOW)
    public void onEnable()
    {	
    	
        getServer().getPluginManager().registerEvents(playerListener, this);
        PluginDescriptionFile pdfFile = getDescription();
        
        //final FileConfiguration config = this.getConfig();
        configFile = new File(getDataFolder(), "config.yml");
        try {
            firstRun();
        } catch (Exception e) {
            e.printStackTrace();
        }
        config = new YamlConfiguration();
        
        System.out.println((new StringBuilder(String.valueOf(pdfFile.getName()))).append(" version ").append(pdfFile.getVersion()).append(" is enabled!").toString());
        
    
    }
    public void initialize() throws Exception {
    	config.load(configFile);
    	htop = config.getInt("highspeed.top");
    	
    }
    public void firstRun() throws Exception {
    	if(!configFile.exists()) {
    		configFile.getParentFile().mkdirs();
    		copy(getResource("config.yml"), configFile);
    	/*config.options().header("Top... is not the block you will stand on. ");
        config.addDefault("Road.Highspeed.top", 20);
        config.addDefault("Road.Highspeed.bottom", 41);
        config.addDefault("Road.Booster.top", 57);
        config.addDefault("Road.Booster.middle", 20);
        config.addDefault("Road.Booster.bottom", 22);
        config.addDefault("Road.Aerial.top", 45);
        config.addDefault("Road.Aerial.middle", 15);
        config.addDefault("Road.Aerial.bottom", 43);
        config.options().copyDefaults(true);
        config.save(configFile);*/
    	}
    }
    
    private void copy(InputStream in, File file) {
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while((len=in.read(buf))>0){
                out.write(buf,0,len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void onDisable()
    {
        System.out.println("[Road] Goodbye world!");
    }

    public boolean isDebugging(Player player)
    {
        if(debugees.containsKey(player))
            return ((Boolean)debugees.get(player)).booleanValue();
        else
            return false;
    }
    public void saveYamls() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadYamls() {
        try {
            config.load(configFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDebugging(Player player, boolean value)
    {
        debugees.put(player, Boolean.valueOf(value));
    }

    private final RoadPlayerListener playerListener = new RoadPlayerListener(this);
    private final HashMap<Player, Boolean> debugees = new HashMap<Player, Boolean>();
}
