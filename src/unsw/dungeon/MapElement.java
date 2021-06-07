package unsw.dungeon;

/**
 * interface for interacting with map element such as walls
 */
public interface MapElement {
    public void interact(Player player, Dungeon dungeon);

    public int getX();

    public int getY();

    public void move(int x, int y);

    public int getIndex();

    public String getName();

}