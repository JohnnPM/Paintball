/*
 * Author: 598Johnn897
 * 
 * Date: Nov 27, 2014
 * Package: paintball.commands
 *
 */
package paintball.commands;

import org.bukkit.entity.Player;

import paintball.commands.PBCommandFramework.Command;
import paintball.commands.PBCommandFramework.CommandArgs;
import paintball.commands.PBCommandFramework.CommandListener;
import paintball.lib.References;

/**
 * 
 */
public class PaintballCommand implements CommandListener
{
	@Command(command = "paintball", permission = "paintball.admin", aliases =
	{ "pb" })
	public void paintball(CommandArgs info)
	{
		if (info.isPlayer())
		{
			Player player = info.getPlayer();
			player.sendMessage(References.PB_CMD_MSG);
		} 
		else
		{
			
		}
	}
}
