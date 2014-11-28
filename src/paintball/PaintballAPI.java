/*
 * Author: 598Johnn897
 * 
 * Date: Nov 27, 2014
 * Package: paintball
 *
 */
package paintball;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import paintball.manager.PlayerManager;
import paintball.manager.TeamManager;

/**
 * 
 */
public class PaintballAPI
{
	private PlayerManager playerManager;
	private TeamManager teamManager;
	
	public PaintballAPI(Paintball plugin)
	{
		this.playerManager = new PlayerManager(plugin);
		this.teamManager = new TeamManager(plugin);
	}
	
	@Deprecated public PlayerManager getPlayerManager()
	{
		return playerManager;
	}
	
	@Deprecated public TeamManager getTeamManager()
	{
		return teamManager;
	}
	
	public void addPlayer(Player player) 
	{
		playerManager.addPlayer(player);
	}
	
	public void removePlayer(Player player)
	{
		playerManager.removePlayer(player);
	}
	
	public ArrayList<String> getPlayers() 
	{
		return playerManager.getPlayers();
	}
}
