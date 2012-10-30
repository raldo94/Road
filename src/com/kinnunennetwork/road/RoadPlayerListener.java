package com.kinnunennetwork.road;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;
import org.bukkit.event.EventHandler;

public class RoadPlayerListener implements Listener
{
	Road plugin;
	public boolean henabled, benabled, aenabled;
	public int htop, hbottom, btop, bmiddle, bbottom, athis, atop, abottom;
	public double speed, nspeed;
	public RoadPlayerListener(Road instance) {
	    plugin = instance;
	}
	
	public RoadPlayerListener(Road plugin, RoadPlayerListener playerListener, RoadBlockListener blockListener, String dir)
	{
        jump = new HashSet<Player>();
        speed = plugin.getConfig().getDouble("highway.speed");
        henabled = plugin.getConfig().getBoolean("highway.enabled");
        htop = plugin.getConfig().getInt("highway.top");
        hbottom = plugin.getConfig().getInt("highway.bottom");
        benabled = plugin.getConfig().getBoolean("boosterl.enabled");
        btop = plugin.getConfig().getInt("boosterl.top");
        bmiddle = plugin.getConfig().getInt("boosterl.middle");
        bbottom = plugin.getConfig().getInt("boosterl.bottom");
        aenabled = plugin.getConfig().getBoolean("Aerialfp.enabled");
        athis = plugin.getConfig().getInt("Aerialfp.this");
        atop = plugin.getConfig().getInt("Aerialfp.top");
        abottom = plugin.getConfig().getInt("Aerialfp.bottom");
    }
	
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerMove(PlayerMoveEvent event){
        Player player = event.getPlayer();
        int materialThis = player.getWorld().getBlockAt(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ()).getTypeId();
        int materialTop = player.getWorld().getBlockAt(player.getLocation().getBlockX(), player.getLocation().getBlockY() - 2, player.getLocation().getBlockZ()).getTypeId();
        int materialBottom = player.getWorld().getBlockAt(player.getLocation().getBlockX(), player.getLocation().getBlockY() - 3, player.getLocation().getBlockZ()).getTypeId();
        int materialVeryBottom = player.getWorld().getBlockAt(player.getLocation().getBlockX(), player.getLocation().getBlockY() - 4, player.getLocation().getBlockZ()).getTypeId();
        
    	
        if(henabled == true) {
        	if(materialTop == htop && materialBottom == hbottom && player.isSneaking())
        	{	
        		nspeed = (speed * 0.44);
        		Vector dir2 = player.getLocation().getDirection().setY(0).multiply(nspeed);
        		player.setVelocity(dir2);
        	}
        }
        if(benabled == true) {
        	if(materialTop == btop && materialBottom == bmiddle && materialVeryBottom == bbottom)
            {
                Vector up = new Vector(0, 5, 0);
                player.setVelocity(up);
                if(jump.contains(player))
                    jump.remove(player);
                jump.add(player);
            }	
        }
        if (aenabled == true) {

        	if(materialTop == atop && materialBottom == abottom && materialThis == athis)
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
        
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onInteract(PlayerInteractEvent e) {

    	
    }
    private Set<Player> jump;
}
