package unsw.dungeon;

public class GoalHandler {
    private Dungeon dungeon;
    private Player player;
    private boolean goalExit;
    private boolean goalEnemies;
    private boolean goalBoulders;
    private boolean goalTreasure;

    public GoalHandler(GameController gameController) {
        this.dungeon = gameController.getDungeon();
        this.player = gameController.getPlayer();
        this.goalExit = false;
        this.goalEnemies = false;
        this.goalBoulders = false;
        this.goalTreasure = false;
    }

    /**
     * set goal of the map
     * 
     * @param goal
     */
    public void setGameGoals(String goal) {
        if (goal.equals("exit"))
            goalExit = true;
        if (goal.equals("enemies"))
            goalEnemies = true;
        if (goal.equals("boulders"))
            goalBoulders = true;
        if (goal.equals("treasure"))
            goalTreasure = true;
    }

    /**
     * @return return true of the goal is met
     */
    public boolean checkExitGoal() {
        if (!goalExit)
            return true;

        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                MapElement e = dungeon.getMapElements(x, y);
                if (e != null && e.getName().equals("exit") && (player.getX() == x && player.getY() == y)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * @return return true if all the enemies are killed
     */
    public boolean checkEnemiesGoal() {
        if (!goalEnemies)
            return true;

        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                Enemy e = dungeon.getEnemy(x, y);
                if (e != null) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * @return true if boulders are completed
     */
    public boolean checkBouldersGoal() {
        if (!goalBoulders)
            return true;

        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                MapElement e = dungeon.getMapElements(x, y);
                MapElement o = dungeon.getOriginalMapElements(x, y);
                if (o != null && o.getName().equals("switch") && (e == null || !e.getName().equals("boulder"))) {
                    return false;
                }

            }
        }

        return true;
    }

    /**
     * @return true if all trasures collected
     */
    public boolean checkTreasureGoal() {
        if (!goalTreasure)
            return true;

        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                Item e = dungeon.getItem(x, y);
                if (e != null && e.getName().equals("treasure")) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean goalsComplete() {
        if (checkExitGoal() && checkEnemiesGoal() && checkBouldersGoal() && checkTreasureGoal()) {
            return true;
        }
        return false;
    }

}