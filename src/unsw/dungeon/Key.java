package unsw.dungeon;

/**
 * Key of index x opens the door of index x. Specify the index in json file
 * ie.  {
      "index" : 1,
      "x": 11,
      "y": 1,
      "type": "key" / door
    },
 */
public class Key extends Entity implements Item {

    public Key(int x, int y, int index) {
        super(x, y, index);
        name = "key";
    }

    public void consume(Player player, Dungeon dungeon) {
        player.move(this.getX(), this.getY());
        dungeon.removeItem(this.getX(), this.getY());
        //dungeon.openDoor(this.getIndex());
        openDoor(dungeon);
        this.move(-1, -1);
    }

    public void openDoor(Dungeon dungeon) {
        int index = getIndex();
        for (int i = 0; i < dungeon.getWidth(); i++) {
            for (int j = 0; j < dungeon.getHeight(); j++) {
                MapElement e = dungeon.getMapElements(i, j);
                if (e != null && e.getName() == "door" && e.getIndex() == index) {
                    e.move(-1, -1);
                    dungeon.removeMapElement(i, j);
                }
            }
        }
    }

}