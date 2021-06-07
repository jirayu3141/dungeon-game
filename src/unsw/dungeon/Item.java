package unsw.dungeon;

public interface Item {
    public void consume(Player player, Dungeon dungeon);

    public int getX();

    public int getY();

    public void move(int x, int y);

    public int getIndex();

    public String getName();
}