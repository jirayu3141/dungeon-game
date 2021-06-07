package unsw.dungeon;

public class InvincibilityPotion extends Entity implements Item {

    public InvincibilityPotion(int x, int y) {
        super(x, y);
        name = "invincibility";
    }

    
    /** 
     * Call this function for when you want to consume the potion.
     * 
     * @param player
     * @param dungeon
     */
    public void consume(Player player, Dungeon dungeon) {
        player.move(this.getX(), this.getY()); 
        player.setInvincibilityState();
        dungeon.removeItem(this.getX(), this.getY());
        this.move(-1, -1);
    }
}