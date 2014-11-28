package paintball.manager;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import paintball.Paintball;

public class PlayerManager
{
	private Paintball plugin;

	public PlayerManager(Paintball plugin)
	{
		this.plugin = plugin;
	}

	private ArrayList<String> players = new ArrayList<String>();

	public void addPlayer(Player player)
	{
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
