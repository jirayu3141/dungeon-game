package unsw.dungeon;

public class Wall extends Entity implements MapElement {

    public Wall(int x, int y) {
        super(x, y);
        name = "wall";
    }

    public void interact(Player player, Dungeon dungeon) {
        // do nothing
    }
}
