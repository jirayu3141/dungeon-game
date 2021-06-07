package unsw.dungeon;

public class Boulder extends Entity implements MapElement {

    /**
     * Constructor method for boulder
     * 
     * @param x coordinate
     * @param y coordinate
     */
    public Boulder(int x, int y) {
        super(x, y);
        name = "boulder";
    }

    /**
     * method to move boulder when the player is pushing it
     * 
     * @param player  in the dungeon
     * @param dungeon object
     */
    public void interact(Player player, Dungeon dungeon) {
        int x = getX();
        int y = getY();
        int pX = player.getX();
        int pY = player.getY();
        // might need to change this to map element
        
        // player pushing from right
        if (pX > x) {
            moveBoulder(player, dungeon, x - 1, y);
        }
        // player pushing from left
        if (pX < x) {
            moveBoulder(player, dungeon, x + 1, y);
        }
        // player pushing from bottom
        if (pY > y) {
            moveBoulder(player, dungeon, x, y - 1);
        }
        // player pushing from top
        if (pY < y) {
            moveBoulder(player, dungeon, x, y + 1);
        }
    }

    /**
     * move boulder to desired coordinate
     * 
     * @param player  in the game
     * @param dungeon map
     * @param x       coordinate
     * @param y       coordinate
     */
    private void moveBoulder(Player player, Dungeon dungeon, int x, int y) {
        MapElement entity;
        if (dungeon.inbound(x, y)) {
            entity = dungeon.getMapElements(x, y);
            if (entity == null || entity.getName() == "switch") {
                player.move(this.getX(), this.getY());
                dungeon.updateMapElements(this.getX(), this.getY(), x, y);
                this.move(x, y);
            }
        }
    }
}