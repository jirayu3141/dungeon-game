package unsw.dungeon;

public class GameController {
    private Player player;
    private Dungeon dungeon;
    private EnemyAI enemyAI;
    private GoalHandler goalHandler;
    private DarkControl darkControl;


    private GameState normalState;
    private GameState pauseState;
    private GameState victoryState;
    private GameState defeatState;


    private GameState state;

    public GameController() {
        this.enemyAI = new EnemyAI(this);
        this.darkControl = null;
        this.goalHandler = null;

        normalState = new NormalState(this);
        pauseState = new PauseState(this);
        victoryState = new VictoryState(this);
        defeatState = new DefeatState(this);

        state = getNormalState();
    }

    /**
     * setter method for dungeon
     * 
     * @param dungeon
     */
    public void setDungeon(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    /**
     * setter method for player
     * 
     * @param player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * getter method for player
     * 
     * @return Player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * getter method for dungeon
     * 
     * @return Dungeon
     */
    public Dungeon getDungeon() {
        return dungeon;
    }

    /**
     * getter method for enemyai
     * 
     * @return EnemyAI
     */
    public EnemyAI getEnemyAI() {
        return enemyAI;
    }

    /**
     * move player to coor x, y
     * 
     * @param x coor
     * @param y coor
     */
    public void move(int x, int y) {
        state.move(x, y, player, dungeon);
    }

    /**
     * use weapon at the location (x,y)
     * 
     * @param x index x in the dungeon
     * @param y index y in the dungeon
     */
    public void useWeapon(int x, int y) {
        state.useWeapon(x, y, player, dungeon);
    }

    public void removeWeapons() {
        state.removeWeapons(player);
    }

    public void checkGameState() {
        if(goalHandler.goalsComplete())
            state = getVictoryState(); 
    }

    public void defeat() {
        state = getDefeatState();
    }

    public void victory() {
        state = getVictoryState();
    }

    /**
     * set goal of the map
     * 
     * @param goal
     */
    public void setGameGoals(String goal) {
        if (goalHandler == null){
            goalHandler = new GoalHandler(this);
        }
        goalHandler.setGameGoals(goal);
    }



    public void moveEnemies() {
        state.moveEnemies(player);
    }

    public void pause() {
        state.pause();
    }

    public void setState(GameState state) {
        this.state = state;
    }

    // --getter methods--//

    public String getGameState() {
        return state.getGameState();
    }

    public GameState getNormalState() {
        return normalState;
    }

    public GameState getPauseState() {
        return pauseState;
    }

    public GameState getVictoryState() {
        return victoryState;
    }

    public GameState getDefeatState() {
        return defeatState;
    }

    public void setDarkness(){
        if (darkControl == null){
            darkControl = new DarkControl(dungeon, player);
        }
        darkControl.setDarkness();
    }

}