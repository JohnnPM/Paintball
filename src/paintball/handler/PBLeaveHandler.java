/*
 * Author: 598Johnn897
 * 
 * Date: Nov 27, 2014
 * Package: paintball.handler
 *
 */
package paintball.handler;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import paintball.Paintball;

/**
 * 
 */
public class PBLeaveHandler implements Listener
{
	@EventHandler public void onLeave(PlayerQuitEvent event)
	{
		Player player = event.getPlayer();
		Paintball.get()
			.getPBAPI().removePlayer(player);
	}
}
