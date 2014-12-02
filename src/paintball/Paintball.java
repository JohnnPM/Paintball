/*
 * Author: 598Johnn897
 * 
 * Date: Nov 27, 2014
 * Package: paintball
 *
 */
package paintball;

import java.util.logging.Level;

import lombok.Getter;

import org.apache.commons.lang.Validate;
import org.bukkit.plugin.java.JavaPlugin;

import paintball.commands.PBCommandFramework;
import paintball.lib.References;

/**
 * 
 */
public class Paintball extends JavaPlugin
{
	private static Paintball instance;
	public static Paintball get() 
	{
		Validate.notNull(instance);
		return instance;
	}
	
	@Getter private final PaintballLogger pBLogger =
			new PaintballLogger(this);
	
	@Getter private PBCommandFramework cmdFramework;
	
	@Getter public PaintballAPI PBAPI;
	
	@Override public void onLoad()
	{
		log(References.LOADING_MSG);
		instance = this; // KTHNXBAI
	}
	
	@Override public void onEnable()
	{
		try {
			cmdFramework = new PBCommandFramework(get());
			cmdFramework.registerCommands();
			cmdFramework.registerHelp();
			
			PaintballUtil.registerEvents(get());
			
			PBAPI = new PaintballAPI();
			if (!PBAPI.isInitialized())
			{
				PBAPI.init(get());
				getPBLogger().log(Level.INFO, "PaintballAPI Initialized!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			log(References.ENABLED_MSG);
		}
	}
	
	@Override public void onDisable()
	{
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			log(References.DISBALED_MSG);
		}
	}
	
	final void log(Object message)
	{
		pBLogger.log(Level.INFO, message.toString());
	}
}
