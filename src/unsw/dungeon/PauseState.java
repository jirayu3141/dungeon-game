package unsw.dungeon;

public class PauseState implements GameState{
    GameController gameController;

    public PauseState(GameController gameController) {
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
        //if(gameController.getInvincibility()){
        //    gameController.setState(gameController.getInvincibleState());
        //} else if(gameController.armed()){
        //    gameController.setState(gameController.getArmedState());
        //} else{
            gameController.setState(gameController.getNormalState());
        //}
    }

    public String getGameState(){
        return "Pause";
    }
    

}