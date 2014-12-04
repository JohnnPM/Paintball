/*
 * Author: 598Johnn897
 * 
 * Date: Dec 3, 2014
 * Package: paintball.bungee
 *
 */
package paintball.bungee;

import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import paintball.lib.References;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

/**
 * 
 */
public class PluginMessaging implements PluginMessageListener
{
	@Override public void onPluginMessageReceived(String channel, Player player,
			byte[] message)
	{
		if (!channel.equals(References.PB_MSG_CHANNEL))
		{
			return;
		}
		
		ByteArrayDataInput in = ByteStreams.newDataInput(message);
		String subchannel = in.readUTF();
		
		if (subchannel.equals(""))
		{
			
		}
	}

}
