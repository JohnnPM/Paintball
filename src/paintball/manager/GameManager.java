/*
 * Author: 598Johnn897
 * 
 * Date: Nov 30, 2014
 * Package: paintball.manager
 *
 */
package paintball.manager;

import paintball.Paintball;

/**
 * 
 */
public class GameManager
{
	public boolean gameInProgress = false;
	
	public GameManager(Paintball plugin)
	{
		
	}
	
	public boolean isGameInProgress()
	{
		return gameInProgress;
	}
	
	public void setGameInProgress(boolean progress)
	{
		this.gameInProgress = progress;
	}
	
	public void startGame()
	{
		
	}
	
	public void endGame()
	{
		
	}
}
