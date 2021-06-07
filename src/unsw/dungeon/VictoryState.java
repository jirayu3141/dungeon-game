package unsw.dungeon;

/**
 * Victory state (state pattern)
 */
public class VictoryState implements GameState{
    GameController gameController;

    public VictoryState(GameController gameController) {
		this.gameController = gameController;
	}
    
    public void move(int x, int y, Player player, Dungeon dungeon){
        // do nothing
    }

    public void useWeapon(int x, int y, Player player, Dungeon dungeon){
        // do nothing
    }

    public void removeWeapons(Player player){
        // do nothing
    }

    public void moveEnemies(Player player){
        // do nothing
    }

    public void pause(){
        // do nothing
    }

    public String getGameState(){
        return "Victory";
    }


}