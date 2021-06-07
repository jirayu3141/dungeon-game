package unsw.dungeon;

public class Sword extends Entity implements Weapon {

    public Sword(int x, int y) {
        super(x, y);
        name = "sword";
    }

    /**
     * method for picking up weapon
     * 
     */
    public void pickup(Player player, Dungeon dungeon) {
        player.move(this.getX(), this.getY());
        player.setWeaponUses(5 + player.getWeaponUses());
        dungeon.removeWeapon(this.getX(), this.getY());
        this.move(-1, -1);
    }
}