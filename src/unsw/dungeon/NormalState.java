package unsw.dungeon;

public class NormalState implements GameState{
    GameController gameController;

    public NormalState(GameController gameController) {
		this.gameController = gameController;
	}

    public void move(int x, int y, Player player, Dungeon dungeon){
        MapElement element = dungeon.getMapElements(x, y);
        Item item = dungeon.getItem(x, y);
        Weapon weapon = dungeon.getWeapon(x, y);
        Enemy enemy = dungeon.getEnemy(x, y);
        if (element != null) {
            element.interact(player, dungeon);
        } else if (item != null) {
            item.consume(player, dungeon);
        } else if (weapon != null) {
            weapon.pickup(player, dungeon);
        } else if (enemy != null) {
            enemy.face(player, dungeon);
        } else {
            player.move(x, y);
        }
    }

    /** 
     * method to use weapon (ie. sword) at desired coordinate while possessing weapon
     * 
     * @param x coordinate to use weapon
     * @param y coordinate to use weapon
     * @param player object
     * @param dungeon object
     */
    public void useWeapon(int x, int y, Player player, Dungeon dungeon) {
        if (x > 0)
            attack(x - 1, y, 0, player, dungeon);
        if (x < dungeon.getWidth() - 1)
            attack(x + 1, y, 1, player, dungeon);
        if (y > 0)
            attack(x, y - 1, 2, player, dungeon);
        if (y < dungeon.getHeight() - 1)
            attack(x, y + 1, 3, player, dungeon);
    }

    
    /** 
     * @param x coordinate of the attack
     * @param y coordinate of the attack
     * @param i index of the sword
     * @param player object
     * @param dungeon object
     */
    public void attack(int x, int y, int i, Player player, Dungeon dungeon) {
        Entity[] playerSwords = player.getPlayerSwords();
        if (dungeon.getEnemy(x, y) != null) {
            dungeon.getEnemy(x, y).move(-1, -1);
            dungeon.removeEnemy(x, y);
        }
        playerSwords[i].move(x, y);
    }

    public void removeWeapons(Player player){
        Entity[] playerSwords = player.getPlayerSwords();
        for (int i = 0; i < 4; i++) {
            playerSwords[i].move(-1, -1);
        }
    }

    public void moveEnemies(Player player){
        EnemyAI ai = gameController.getEnemyAI();
        ai.moveEnemies(gameController.getDungeon(), !player.getInvincibilityState());
    }

    public void pause(){
        gameController.setState(gameController.getPauseState());
    }

    public String getGameState(){
        return "Normal";
    }

}