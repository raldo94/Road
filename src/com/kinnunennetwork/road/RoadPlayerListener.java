package com.kinnunennetwork.road;

import java.util.HashSet;
import java.util.Set;
//import org.bukkit.Location;
//import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;
import org.bukkit.event.EventHandler;

public class RoadPlayerListener implements Listener
{

	public RoadPlayerListener(Road instance)
    {
        jump = new HashSet<Player>();
        
    }
	
	 public void loadYamls() {
	    try {
	        Road.config.load(Road.configFile);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


	/**@return 
	 * @EventHandler(priority = EventPriority.LOW)
	public void onBlock(BlockDamageEvent event) throws FileNotFoundException, IOException, InvalidConfigurationException {
	final static Road plugin1 = new Road();
    final  FileConfiguration config = plugin1.getConfig();
    int hsMt = config.getInt("Road.Highspeed.top"); 
    int hsMb = config.getInt("Road.Highspeed.bottom");
    int bt = config.getInt("Road.Booster.top");
    int bm = config.getInt("Road.Booster.middle");
    int bb = config.getInt("Road.Booster.bottom");
    int at = config.getInt("Road.Aerial.top");
    int am = config.getInt("Road.Aerial.middle");
    int ab = config.getInt("Road.Aerial.bottom");
    
	Road.config.load(Road.configFile);
	int test = Road.config.getInt("highspeed.top");
	System.out.println(test);
	if(test == 20) {
		event.getPlayer().sendMessage("YEH!");
		
	{}
	}**/

	
	
    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerMove(PlayerMoveEvent event)
    {
    	System.out.println(Road.htop);
    	
    	
        Player player = event.getPlayer();
        int materialThis = player.getWorld().getBlockAt(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ()).getTypeId();
        int materialTop = player.getWorld().getBlockAt(player.getLocation().getBlockX(), player.getLocation().getBlockY() - 2, player.getLocation().getBlockZ()).getTypeId();
        int materialBottom = player.getWorld().getBlockAt(player.getLocation().getBlockX(), player.getLocation().getBlockY() - 3, player.getLocation().getBlockZ()).getTypeId();
        int materialVeryBottom = player.getWorld().getBlockAt(player.getLocation().getBlockX(), player.getLocation().getBlockY() - 4, player.getLocation().getBlockZ()).getTypeId();
        player.setFireTicks(0);
        if(materialTop == 20 && materialBottom == 41 && player.isSneaking())
        {
            Vector dir2 = player.getLocation().getDirection().setY(0).normalize().multiply(4);
            player.setVelocity(dir2);
        } else
        if(materialTop == 57 && materialBottom == 20 && materialVeryBottom == 22)
        {
            Vector up = new Vector(0, 50, 0);
            player.setVelocity(up);
            if(jump.contains(player))
                jump.remove(player);
            jump.add(player);
        } else
        if(materialTop == 45 && materialBottom == 15 && materialThis == 43)
        {
            Block b = player.getWorld().getBlockAt(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ());
            byte data = b.getData();
            Vector up;
            if(data == 0)
                up = (new Vector(0.0D, 0.10000000000000001D, -3D)).normalize().multiply(100);
            else
            if(data == 1)
                up = (new Vector(3D, 0.10000000000000001D, 0.0D)).normalize().multiply(100);
            else
            if(data == 2)
                up = (new Vector(0.0D, 0.10000000000000001D, 3D)).normalize().multiply(100);
            else
            if(data == 3)
                up = (new Vector(-3D, 0.10000000000000001D, 0.0D)).normalize().multiply(100);
            else
                up = new Vector(0, 0, 0);
            player.setVelocity(up);
        }
    }

    private Set<Player> jump;
}
