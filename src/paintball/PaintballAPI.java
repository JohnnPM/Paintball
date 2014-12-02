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

import paintball.manager.GameManager;
import paintball.manager.PlayerManager;
import paintball.manager.TeamManager;
import paintball.manager.TeamManager.Team;

/**
 * 
 */
public class PaintballAPI
{
	private static PlayerManager playerManager;
	private static TeamManager teamManager;
	private static GameManager gameManager;
	
	private static boolean initialized = false;
	
	public void init(Paintball plugin)
	{
		playerManager = new PlayerManager(plugin);
		teamManager = new TeamManager(plugin);
		gameManager = new GameManager(plugin);
		
		initialized = true;
	}
	
	public boolean isInitialized()
	{
		return initialized;
	}
	
	@Deprecated public PlayerManager getPlayerManager()
	{
		return playerManager;
	}
	
	@Deprecated public TeamManager getTeamManager()
	{
		return teamManager;
	}
	
	@Deprecated public GameManager getGameManager()
	{
		return gameManager;
	}
	
	// #### Player Manager #### //
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
	// #### Player Manager End #### //
	
	// #### Team Manager ####//
	public Team getTeamOne()
	{
		return teamManager.getTeamOne();
	}
	
	public Team getTeamTwo()
	{
		return teamManager.getTeamTwo();
	}
	
	public void addPlayerToTeamOne(Player player)
	{
		teamManager.getTeamOne().addPlayer(player);
	}
	
	public void addPlayerToTeamTwo(Player player)
	{
		teamManager.getTeamTwo().addPlayer(player);
	}
	
	public ArrayList<String> getPlayersOnTeamOne()
	{
		return teamManager.getTeamOne().getPlayers();
	}
	
	public ArrayList<String> getPlayersOnTeamTwo()
	{
		return teamManager.getTeamTwo().getPlayers();
	}
	// #### Team Manager End #### //
	
	// #### Game Manager #### //
	// #### Game Manager End #### //
}
