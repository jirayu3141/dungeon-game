package unsw.dungeon;

/**
 * Portal class for portal element in the dungeon game
 */
public class Portal extends Entity implements MapElement {

    public Portal(int x, int y, int index) {
        super(x, y, index);
        name = "portal";
    }

    /**
     * interact with portal process Portal index n teleports to idex n+1 and vice
     * versa
     * 
     */
    public void interact(Player player, Dungeon dungeon) {
        int index = getIndex();
        for (int i = 0; i < dungeon.getWidth(); i++) {
            for (int j = 0; j < dungeon.getHeight(); j++) {
                MapElement e = dungeon.getMapElements(i, j);
                if (e != null && e.getName() == "portal") {
                    if (e.getIndex() % 2 == 0 && e.getIndex() == index + 1) {
                        player.move(i, j);
                    } else if (e.getIndex() % 2 != 0 && e.getIndex() == index - 1) {
                        player.move(i, j);
                    }

                }
            }
        }
    }
}