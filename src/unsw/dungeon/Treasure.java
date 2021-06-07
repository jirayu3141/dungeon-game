package unsw.dungeon;

public class Treasure extends Entity implements Item {

    public Treasure(int x, int y) {
        super(x, y);
        name = "treasure";
    }

    /**
     * method for consuming invisibility potion
     * 
     */
    public void consume(Player player, Dungeon dungeon) {
        player.move(this.getX(), this.getY());
        dungeon.removeItem(this.getX(), this.getY());
        this.move(-1, -1);
    }
}