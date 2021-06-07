package unsw.dungeon;

public class Door extends Entity implements MapElement {

    /**
     * Constructor method for door
     * 
     * @param x     coordinate
     * @param y     coordinate
     * @param index to connect the key to door (ie key 1 unlocks index 1)
     */
    public Door(int x, int y, int index) {
        super(x, y, index);
        name = "door";
    }

    /**
     * Since the player is not able to interact with door this method doesn't do
     * anything
     */
    public void interact(Player player, Dungeon dungeon) {
        // do nothing
    }

}