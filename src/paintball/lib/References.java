/*
 * Author: 598Johnn897
 * 
 * Date: Nov 27, 2014
 * Package: paintball.lib
 *
 */
package paintball.lib;

import paintball.PaintballUtil.ColorUtil;

/**
 * 
 */
public class References
{
	public static final String NAME = "Paintball";
	public static final String VERSION = "0.0.1-SNAPSHOT";
	public static final String AUTHOR = "Johnn (598Johnn897)";
	public static final String CREDITS = "Phil999999999";
	public static final String DESCRIPTION = "A majestic game where you throw paintballs at other people and they die.";
	
	public static final String CHAT_PREFIX = "PB";
	
	public static final String LOADING_MSG = "Paintball is now magestically loading for all to play...";
	public static final String ENABLED_MSG = "The beautiful Paintball minigame plugin has enabled!";
	public static final String DISBALED_MSG = "Paintball has disabled! But why would you do this to such a smexy minigame?";
	
	public static final String PB_CMD_MSG_1 = ColorUtil.formatString("<aqua>----------------------------------");
	public static final String PB_CMD_MSG_2 = ColorUtil.formatString("<gold>   %s", NAME);
	public static final String PB_CMD_MSG_3 = ColorUtil.formatString("<gold>   Version: <aqua>%s", VERSION);
	public static final String PB_CMD_MSG_4 = ColorUtil.formatString("<gold>   Author: <aqua>%s", AUTHOR);
	public static final String PB_CMD_MSG_5 = ColorUtil.formatString("<gold>   Credits: <aqua>%s", CREDITS);
	public static final String PB_CMD_MSG_6 = ColorUtil.formatString("<gold>   Description: <aqua>%s", DESCRIPTION);
	public static final String PB_CMD_MSG_7 = ColorUtil.formatString("<aqua>----------------------------------");
	
	public static final String[] PB_CMD_MSG = {PB_CMD_MSG_1, PB_CMD_MSG_2, PB_CMD_MSG_3, PB_CMD_MSG_4, PB_CMD_MSG_5, PB_CMD_MSG_6, PB_CMD_MSG_7};
}
