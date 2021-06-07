package unsw.dungeon;

public class EnemyAI {
    private GameController gameController;

    public EnemyAI(GameController gameController){
        this.gameController = gameController;
    }

    
    /** 
     * method to move enemy when attacked and not
     * @param dungeon map
     * @param attack 
     */
    public void moveEnemies(Dungeon dungeon, boolean attack){
        int[][] enemiesArray = new int[dungeon.getWidth()][dungeon.getHeight()];
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                enemiesArray[x][y] = 0;
                Enemy e = dungeon.getEnemy(x, y);
                if (e != null) {
                    enemiesArray[x][y] = 1;
                }
            }

        }

        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                if(enemiesArray[x][y] == 1){
                    if(attack){
                        attack(dungeon, x, y);
                    }else{
                        run(dungeon, x, y);
                    }
                }
            }
        }
    }

    
    /** 
     * 
     * method for when the player attack the enemy
     * @param d dungeon
     * @param x x coor
     * @param y y coor
     */
    public void attack(Dungeon d, int x, int y) {
        Enemy e = d.getEnemy(x, y);
        Player player = d.getPlayer();
        int playerX = player.getX();
        int playerY = player.getY();


        if (x > 0 && d.getMapElements(x - 1, y) == null && x > playerX) {
            e.move(x - 1, y);
            d.addEnemy(e);
            d.removeEnemy(x, y);
        } else if(x < d.getWidth() - 1 && d.getMapElements(x + 1, y) == null && x < playerX ){
            e.move(x + 1, y);
            d.addEnemy(e);
            d.removeEnemy(x, y);
        } else if(y > 0 && d.getMapElements(x, y - 1) == null && y > playerY){
            e.move(x, y - 1);
            d.addEnemy(e);
            d.removeEnemy(x, y);
        } else if( y < (d.getHeight() - 1) && d.getMapElements(x, y + 1) == null && y < playerY){
            e.move(x, y + 1);
            d.addEnemy(e);
            d.removeEnemy(x, y);
        }

        if(playerX == e.getX() && playerY == e.getY()){
            gameController.defeat();
        }
    }

    
    /** 
     * function to move enemy towards the player
     */
    public void run(Dungeon d, int x, int y) {
        Enemy e = d.getEnemy(x, y);
        Player player = d.getPlayer();
        int playerX = player.getX();
        int playerY = player.getY();


        if (x > 0 && d.getMapElements(x - 1, y) == null && x < playerX) {
            e.move(x - 1, y);
            d.addEnemy(e);
            d.removeEnemy(x, y);
        } else if(x < d.getWidth() - 1 && d.getMapElements(x + 1, y) == null && x > playerX ){
            e.move(x + 1, y);
            d.addEnemy(e);
            d.removeEnemy(x, y);
        } else if(y > 0 && d.getMapElements(x, y - 1) == null && y < playerY){
            e.move(x, y - 1);
            d.addEnemy(e);
            d.removeEnemy(x, y);
        } else if(y < d.getHeight() - 1 && d.getMapElements(x, y + 1) == null && y > playerY){
            e.move(x, y + 1);
            d.addEnemy(e);
            d.removeEnemy(x, y);
        }

    }

    
}