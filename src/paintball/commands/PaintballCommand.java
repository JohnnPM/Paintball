/*
 * Author: 598Johnn897
 * 
 * Date: Nov 27, 2014
 * Package: paintball.commands
 *
 */
package paintball.commands;

import paintball.commands.PBCommandFramework.Command;
import paintball.commands.PBCommandFramework.CommandArgs;
import paintball.commands.PBCommandFramework.CommandListener;

/**
 * 
 */
public class PaintballCommand implements CommandListener
{
	@Command(command = "paintball", permission = "paintball.admin", aliases = {"pb"})
	public void paintball(CommandArgs args)
	{
		
	}
}
