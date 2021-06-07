package unsw.dungeon;

public class Darkness extends Entity implements MapElement {
    
    public Darkness(int x, int y, int index) {
        super(x, y, index);
        name = "darkness";
    }

    /**
     * Since the player is not able to interact with darkness this method doesn't do
     * anything
     * 
     * @param player
     * @param dungeon
     */
    public void interact(Player player, Dungeon dungeon) {
        // do nothing
    }
}