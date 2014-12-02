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
import org.bukkit.event.player.PlayerJoinEvent;

import paintball.Paintball;

/**
 * 
 */
public class PBLoginHandler implements Listener
{
	@EventHandler public void onJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		Paintball.get()
			.getPBAPI().addPlayer(player);
	}
}
