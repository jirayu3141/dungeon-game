package unsw.dungeon;

public class FloorSwitch extends Entity implements MapElement {
    public FloorSwitch(int x, int y) {
        super(x, y);
        name = "switch";
    }

    /**
     * method for interating player with switch
     * 
     * @param player
     * @param dungeon
     */
    public void interact(Player player, Dungeon dungeon) {
        player.move(this.getX(), this.getY());
    }
}