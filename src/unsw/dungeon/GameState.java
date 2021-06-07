package unsw.dungeon;
/**
 * States of the game (state pattern);
 */
public interface GameState {

    public void move(int x, int y, Player player, Dungeon dungeon);
    public void useWeapon(int x, int y, Player player, Dungeon dungeon);
    public void removeWeapons(Player player);
    public void moveEnemies(Player player);
    public void pause();
    public String getGameState();
}