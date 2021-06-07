package unsw.dungeon;

public class DefeatState implements GameState{
    GameController gameController;

    public DefeatState(GameController gameController) {
		this.gameController = gameController;
	}
    
    
    /** 
     * This function doesn't allow player to move in this state
     * 
     * @param x
     * @param y
     * @param player
     * @param dungeon
     */
    public void move(int x, int y, Player player, Dungeon dungeon){
        // do nothing
    }

    
    /** 
     * This method doesn't allow the player to use a weapon in this state
     * @param x
     * @param y
     * @param player
     * @param dungeon
     */
    public void useWeapon(int x, int y, Player player, Dungeon dungeon){
        // do nothing
    }

    
    /** 
     * This method doesn't allow the player to remove weapon in this state
     * @param player
     */
    public void removeWeapons(Player player){
        // do nothing
    }

    public void moveEnemies(Player player){
        // do nothing
    }

    public void pause(){
        // do nothing
    }

    
    /** 
     * @return "Defeat" state string
     */
    public String getGameState(){
        return "Defeat";
    }

}