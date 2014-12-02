/*
 * Author: 598Johnn897
 * 
 * Date: Nov 27, 2014
 * Package: paintball.manager
 *
 */
package paintball.manager;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import paintball.Paintball;

/**
 * 
 */
public class TeamManager
{
	private Paintball plugin;
	
	public Team team1;
	public Team team2;
	
	public TeamManager(Paintball plugin)
	{
		this.plugin = plugin;
		this.team1 = new Team("moose", "[ONE]", ChatColor.WHITE);
		this.team2 = new Team("llama", "[TWO]", ChatColor.BLACK);
	}
	
	public Team getTeamOne()
	{
		return team1;
	}
	
	public Team getTeamTwo() 
	{
		return team2;
	}
	
	public class Team 
	{
		private ArrayList<String> players = new ArrayList<String>();
		
		private String name;
		private String prefix;
		private ChatColor color;
		
		public Team(String name, String prefix, ChatColor color)
		{
			this.name = name;
			this.prefix = prefix;
			this.color = color;
		}
		
		public String getName()
		{
			return name;
		}
		
		public void setName(String name)
		{
			this.name = name;
		}
		
		public String getPrefix()
		{
			return prefix;
		}
		
		public void setPrefix(String prefix)
		{
			this.prefix = prefix;
		}
		
		public ChatColor getColor()
		{
			return color;
		}
		
		public void setColor(ChatColor color)
		{
			this.color = color;
		}
		
		public void addPlayer(Player player)
		{
			if (players.contains(player.getName()))
				return;
			else
				players.add(player.getName());
		}
		
		public void removePlayer(Player player)
		{
			if (players.contains(player.getName()))
				players.remove(player.getName());
			else
				return;
		}
		
		public ArrayList<String> getPlayers()
		{
			return players;
		}
	}
	
}
