package com.kinnunennetwork.road;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;
import org.bukkit.event.EventHandler;

public class RoadPlayerListener implements Listener
{
	Road plugin;
	public boolean henabled, benabled, aenabled;
	public int htop, hbottom, btop, bmiddle, bbottom, aspeed, athis, atop, abottom;
	public double speed, nspeed, bpwr;
	public RoadPlayerListener(Road instance) {
	    plugin = instance;
	}
	
	public RoadPlayerListener(Road plugin, RoadPlayerListener playerListener, String dir)
	{
        speed = plugin.getConfig().getDouble("highway.speed");
        henabled = plugin.getConfig().getBoolean("highway.enabled");
        htop = plugin.getConfig().getInt("highway.top");
        hbottom = plugin.getConfig().getInt("highway.bottom");
        benabled = plugin.getConfig().getBoolean("boosterl.enabled");
        bpwr = plugin.getConfig().getDouble("boosterl.pwr");
        btop = plugin.getConfig().getInt("boosterl.top");
        bmiddle = plugin.getConfig().getInt("boosterl.middle");
        bbottom = plugin.getConfig().getInt("boosterl.bottom");
        aenabled = plugin.getConfig().getBoolean("Aerialfp.enabled");
        aspeed = plugin.getConfig().getInt("Aerialfp.speed");
        athis = plugin.getConfig().getInt("Aerialfp.this");
        atop = plugin.getConfig().getInt("Aerialfp.top");
        abottom = plugin.getConfig().getInt("Aerialfp.bottom");
    }
		
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerMove(PlayerMoveEvent event){
        Player player = event.getPlayer();
        
        Location bpos = player.getLocation();
        int materialThis = player.getWorld().getBlockAt(bpos).getTypeId();
        bpos.setY(bpos.getY()-2);
        int materialTop = player.getWorld().getBlockAt(bpos).getTypeId();
        bpos.setY(bpos.getY()-1);
        int materialBottom = player.getWorld().getBlockAt(bpos).getTypeId();
        bpos.setY(bpos.getY()-1);
        int materialVeryBottom = player.getWorld().getBlockAt(bpos).getTypeId();
        
        if(henabled == true && player.hasPermission("road.highway")) {
        	if(materialTop == htop && materialBottom == hbottom && player.isSneaking())
        	{	
        		nspeed = (speed * 0.44);
        		Vector dir2 = player.getLocation().getDirection().setY(0).multiply(nspeed);
        		player.setVelocity(dir2);
        	}
        }
        if(benabled == true && player.hasPermission("road.boosterl")) {
        	if(materialTop == btop && materialBottom == bmiddle && materialVeryBottom == bbottom)
            {
                Vector up = new Vector(0, bpwr, 0);
                player.setVelocity(up);
            }	
        }
        if (aenabled == true && player.hasPermission("road.aerialfp")) {

        	if(materialTop == atop && materialBottom == abottom && materialThis == athis)
            {
                Block b = player.getWorld().getBlockAt(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ());
                byte data = b.getData();
                Vector up;
                switch (data) {
                case 0:
                	up = (new Vector(0.0, 1.80000000000000001, -10)).normalize().multiply(aspeed);
                	break;
                case 1:
                	up = (new Vector(10, 1.80000000000000001, 0.0)).normalize().multiply(aspeed);
                	break;
                case 2:
                	up = (new Vector(0.0, 1.80000000000000001, 10)).normalize().multiply(aspeed);
                	break;
                case 3:
                	up = (new Vector(-10, 1.80000000000000001, 0.0)).normalize().multiply(aspeed);
                	break;
                default:
                	up = new Vector(0, 0, 0);
                	break;
                }             
                player.setVelocity(up);
            }
        }
        
    }
}
