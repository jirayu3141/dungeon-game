package unsw.dungeon;
/**
 * Interface for all weapons
 */
public interface Weapon {
    public void pickup(Player player, Dungeon dungeon);

    public int getX();

    public int getY();

    public void move(int x, int y);

    public String getName();
}