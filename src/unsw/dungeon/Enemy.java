package unsw.dungeon;

/**
 * Enemy interface
 */
public interface Enemy {
    /**
     * function for when the enemy face the player
     */
    public void face(Player player, Dungeon dungeon);

    public int getX();

    public int getY();

    /**
     * Moving the entity to x,y
     * 
     * @param x
     * @param y
     */
    public void move(int x, int y);

    public String getName();
}