package unsw.dungeon;

public class Exit extends Entity implements MapElement {

    public Exit(int x, int y) {
        super(x, y);
        name = "exit";
    }

    /**
     * method for interating exit with player
     * 
     * @param player
     * @param dungeon
     */
    public void interact(Player player, Dungeon dungeon) {
        player.move(this.getX(), this.getY());
    }
}