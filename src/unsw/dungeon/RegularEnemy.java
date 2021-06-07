package unsw.dungeon;

public class RegularEnemy extends Entity implements Enemy {

    public RegularEnemy(int x, int y) {
        super(x, y);
        name = "regular enemy";
    }

    public void face(Player player, Dungeon dungeon) {
        if (player.getInvincibilityState()) {
            int x = this.getX();
            int y = this.getY();
            player.move(x, y);
            this.move(-1, -1);
            dungeon.removeEnemy(x, y);
        } else {
            GameController gameController = dungeon.getGameController();
            gameController.defeat();
        }
    }
}