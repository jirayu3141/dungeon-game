package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Jirayu Sirivorawong & Yazed Al-Falhi
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * 
     * @return dungeon object
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");
        String type = json.getString("type");
        
        GameController gameController = new GameController();

        Dungeon dungeon = new Dungeon(width, height, type, gameController);

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Darkness darkness = new Darkness(x, y, 0);
                onLoad(darkness);
                dungeon.addDarkness(darkness);
            }
        }

        JSONObject jsonGoals = json.getJSONObject("goal-condition");
        loadGoals(dungeon.getGameController(), jsonGoals);
        
        return dungeon;
    }

    
    /** 
     * load goals from json file 
     * 
     * @param gameController
     * @param json files that contain the goals
     */
    private void loadGoals(GameController gameController, JSONObject json) {
        String goal = json.getString("goal");
        int width = this.json.getInt("width");
        int height = this.json.getInt("height");
        Dungeon dungeon = gameController.getDungeon();

        if (goal.equals("AND")) {
            JSONArray goals = json.getJSONArray("subgoals");
            for (int i = 0; i < goals.length(); i++) {
                String goalType = goals.getJSONObject(i).getString("goal");
                gameController.setGameGoals(goalType);

                if(goalType.equals("treasure")){
                    Treasure treasure = new Treasure(i, height - 1);
                    goalLoadTresure(treasure);
                } else if (goalType.equals("enemies")) {
                    RegularEnemy enemy = new RegularEnemy(i, height - 1);
                    goalLoadEnemy(enemy);
                } else if (goalType.equals("boulders")) {
                    Boulder boulder = new Boulder(i, height - 1);
                    goalLoadBoulders(boulder);
                } else if (goalType.equals("exit")) {
                    Exit exit = new Exit(i, height - 1);
                    goalLoadExit(exit);
                }

            }
        } else {
            gameController.setGameGoals(goal);
            
            if(goal.equals("treasure")){
                Treasure treasure = new Treasure(0, height - 1);
                goalLoadTresure(treasure);
            } else if (goal.equals("enemies")) {
                RegularEnemy enemy = new RegularEnemy(0, height - 1);
                goalLoadEnemy(enemy);
            } else if (goal.equals("boulders")) {
                Boulder boulder = new Boulder(0, height - 1);
                goalLoadBoulders(boulder);
            } else if (goal.equals("exit")) {
                Exit exit = new Exit(0, height - 1);
                goalLoadExit(exit);
            }
            
        }

    }

    
    /** 
     * load entities from json file 
     * 
     * @param dungeon
     * @param json object conitaining the entities 
     */
    private void loadEntity(Dungeon dungeon, JSONObject json) {
        int x = json.getInt("x");
        int y = json.getInt("y");
        String type = json.getString("type");
        int i = 0;

        switch (type) {
            case "player":
                GameController gameController = dungeon.getGameController();
                Player player = new Player(dungeon, x, y, gameController);
                dungeon.setPlayer(player);
                onLoad(player);
                Sword playerSword1 = new Sword(0, 0);
                Sword playerSword2 = new Sword(0, 0);
                Sword playerSword3 = new Sword(0, 0);
                Sword playerSword4 = new Sword(0, 0);
                onLoad(playerSword1);
                onLoad(playerSword2);
                onLoad(playerSword3);
                onLoad(playerSword4);
                Entity[] swords = { (Entity) playerSword1, (Entity) playerSword2, (Entity) playerSword3,
                        (Entity) playerSword4 };
                player.setPlayerSwords(swords);
                break;
            case "wall":
                Wall wall = new Wall(x, y);
                onLoad(wall);
                dungeon.addMapElement(wall);
                break;
            case "exit":
                Exit exit = new Exit(x, y);
                onLoad(exit);
                dungeon.addMapElement(exit);
                break;
            case "treasure":
                Treasure treasure = new Treasure(x, y);
                onLoad(treasure);
                dungeon.addItem(treasure);
                break;
            case "door":
                i = json.getInt("index");
                Door openDoor = new Door(x, y, i);
                onLoad(openDoor, 0);
                Door door = new Door(x, y, i);
                onLoad(door, 1);
                dungeon.addMapElement(door);
                break;
            case "key":
                i = json.getInt("index");
                Key key = new Key(x, y, i);
                onLoad(key);
                dungeon.addItem(key);
                break;
            case "boulder":
                Boulder boulder = new Boulder(x, y);
                onLoad(boulder);
                dungeon.addMapElement(boulder);
                break;
            case "switch":
                FloorSwitch floorSwitch = new FloorSwitch(x, y);
                onLoad(floorSwitch);
                dungeon.addMapElement(floorSwitch);
                break;
            case "portal":
                i = json.getInt("index");
                Portal portal = new Portal(x, y, i);
                onLoad(portal);
                dungeon.addMapElement(portal);
                break;
            case "enemy":
                RegularEnemy regularEnemy = new RegularEnemy(x, y);
                onLoad(regularEnemy);
                dungeon.addEnemy(regularEnemy);
                break;
            case "sword":
                Sword sword = new Sword(x, y);
                onLoad(sword);
                dungeon.addWeapon(sword);
                break;
            case "invincibility":
                InvincibilityPotion invincibilityPotion = new InvincibilityPotion(x, y);
                onLoad(invincibilityPotion);
                dungeon.addItem(invincibilityPotion);
                break;
            default:
                break;

        }
    }

    // -- connecting entities with images -- //


    public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);

    public abstract void onLoad(Exit exit);

    public abstract void onLoad(Treasure treasure);

    public abstract void onLoad(Door door, int type);

    public abstract void onLoad(Key key);

    public abstract void onLoad(Boulder boulder);

    public abstract void onLoad(FloorSwitch floorSwitch);

    public abstract void onLoad(Portal portal);

    public abstract void onLoad(RegularEnemy regularEnemy);

    public abstract void onLoad(Sword sword);

    public abstract void onLoad(InvincibilityPotion invincibilityPotion);

    public abstract void onLoad(Darkness dark);

    public abstract void goalLoadTresure(Treasure e);
    public abstract void goalLoadExit(Exit e);
    public abstract void goalLoadEnemy(RegularEnemy e);
    public abstract void goalLoadBoulders(Boulder e);
}
