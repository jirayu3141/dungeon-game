package unsw.dungeon;

/**
 * The player entity
 * 
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;
    private GameController gameController;
    private int weaponUses;
    private Entity[] playerSwords;
    private int invincibilityState;

    /**
     * Create a player positioned in square (x,y)
     * 
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y, GameController gameController) {
        super(x, y);
        this.dungeon = dungeon;
        name = "player";
        weaponUses = 0;
        playerSwords = new Entity[4];
        invincibilityState = 0;
        this.gameController = gameController;
        gameController.setPlayer(this);
    }

    public void moveUp() {
        if (getY() > 0)
            gameController.move(getX(), getY() - 1);
    }

    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1)
            gameController.move(getX(), getY() + 1);
    }

    public void moveLeft() {
        if (getX() > 0)
            gameController.move(getX() - 1, getY());
    }

    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1)
            gameController.move(getX() + 1, getY());
    }

    public int getWeaponUses() {
        return weaponUses;
    }

    public void setWeaponUses(int weaponUses) {
        this.weaponUses = weaponUses;
    }

    public void useWeapon() {
        if (weaponUses > 0) {
            gameController.useWeapon(getX(), getY());
        }
        weaponUses--;
    }

    public void setPlayerSwords(Entity[] swords) {
        playerSwords = swords;
    }

    public Entity[] getPlayerSwords() {
        return this.playerSwords;
    }

    public boolean getInvincibilityState() {
        return (invincibilityState > 0);
    }

    public void setInvincibilityState() {
        invincibilityState += 5;
    }

    public void reduceInvincibilityState() {
        if (invincibilityState > 0)
            invincibilityState--;
    }

}
