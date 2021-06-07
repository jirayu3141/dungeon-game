/**
 *
 */
package unsw.dungeon;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private String type;
    private Player player;
    private GameController gameController;
    private MapElement[][] originalMapElements;
    private MapElement[][] mapElements;
    private Item[][] items;
    private Weapon[][] weapons;
    private Enemy[][] enemies;
    private Entity[][] darkness;

    /**
     * Costructor method for the dungeon
     * 
     * @param width  of the dungeon
     * @param height of the dungeon
     */
    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.player = null;
        this.mapElements = new MapElement[width][height];
        this.originalMapElements = new MapElement[width][height];
        this.items = new Item[width][height];
        this.weapons = new Weapon[width][height];
        this.enemies = new Enemy[width][height];
        this.darkness = new Entity[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                this.mapElements[x][y] = null;
                this.originalMapElements[x][y] = null;
                this.items[x][y] = null;
                this.weapons[x][y] = null;
                this.enemies[x][y] = null;
                this.darkness[x][y] = null;
            }
        }
    }

    /**
     * Constructor to add game controlling element into the dungeon
     * 
     * @param width          of the dungeon
     * @param height         of the dungeon
     * @param gameController elements to control the game
     */
    public Dungeon(int width, int height, String type, GameController gameController) {
        this(width, height);
        this.type = type;
        this.gameController = gameController;
        gameController.setDungeon(this);
    }

    public Dungeon(int width, int height, GameController gameController) {
        this(width, height);
        this.type = "normal";
        this.gameController = gameController;
        gameController.setDungeon(this);
    }

    /**
     * getter method for width
     * 
     * @return width of the dungeon
     */
    public int getWidth() {
        return width;
    }

    /**
     * getter method for height
     * 
     * @return height of the dungeon
     */
    public int getHeight() {
        return height;
    }

    /**
     * getter method for type
     * 
     * @return type of the dungeon
     */
    public String getType() {
        if(type == null) {
            return "normal";
        }
        return type;
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
     * getter method for gameController
     * 
     * @return GameController
     */
    public GameController getGameController() {
        return gameController;
    }

    /**
     * setter method for player
     * 
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * method to add map element to the dungeon
     * 
     * @param entity (ie door, walls)
     */
    public void addMapElement(MapElement entity) {
        this.mapElements[entity.getX()][entity.getY()] = entity;
        if (this.originalMapElements[entity.getX()][entity.getY()] == null) {
            this.originalMapElements[entity.getX()][entity.getY()] = entity;
        }
    }

    /**
     * method for adding collectible item
     * 
     * @param entity (ie. keys)
     */
    public void addItem(Item entity) {
        this.items[entity.getX()][entity.getY()] = entity;
    }

    /**
     * method for adding weapons to the dungeon
     * 
     * @param entity (ie. swords)
     */
    public void addWeapon(Weapon entity) {
        this.weapons[entity.getX()][entity.getY()] = entity;
    }

    /**
     * method for adding enemy to the dungeon
     * 
     * @param entity
     */
    public void addEnemy(Enemy entity) {
        this.enemies[entity.getX()][entity.getY()] = entity;
    }

    public void addDarkness(Entity darkness) {
        this.darkness[darkness.getX()][darkness.getY()] = darkness;
    }

    /**
     * 
     * method for removing map element from the specified coordinate
     * 
     * @param x coordinate
     * @param y coordinate
     */
    public void removeMapElement(int x, int y) {
        mapElements[x][y] = null;
    }

    /**
     * 
     * method for removing collectible item from the specified coordinate
     * 
     * @param x coordinate
     * @param y coordinate
     */
    public void removeItem(int x, int y) {
        items[x][y] = null;
    }

    /**
     * 
     * method for removing weapon from the specified coordiate
     * 
     * @param x
     * @param y
     */
    public void removeWeapon(int x, int y) {
        weapons[x][y] = null;
    }

    /**
     * method for removing enemy from the specified coordinate
     * 
     * @param x
     * @param y
     */
    public void removeEnemy(int x, int y) {
        enemies[x][y] = null;
    }

    /**
     * return what the map element is from the specified coordinate
     * 
     * @param x coordinate
     * @param y cooridate
     * @return MapElement
     */
    public MapElement getMapElements(int x, int y) {
        return this.mapElements[x][y];
    }

    /**
     * return the element for when the map is first specified (input from json)
     * 
     * @param x coordinate
     * @param y coordinate
     * @return MapElement
     */
    public MapElement getOriginalMapElements(int x, int y) {
        return this.originalMapElements[x][y];
    }

    /**
     * getter method for item
     * 
     * @param x coordinate
     * @param y coordinate
     * @return Item
     */
    public Item getItem(int x, int y) {
        return this.items[x][y];
    }

    /**
     * getter method for weapon
     * 
     * @param x coordinate
     * @param y coordinate
     * @return Weapon
     */
    public Weapon getWeapon(int x, int y) {
        return this.weapons[x][y];
    }

    /**
     * getter method to get the enemy
     * 
     * @param x coordinate
     * @param y coordinate
     * @return Enemy
     */
    public Enemy getEnemy(int x, int y) {
        return this.enemies[x][y];
    }

    public Entity getDarkness(int x, int y) {
        return this.darkness[x][y];
    }

    /**
     * method for updating the map element from one coordinate to another
     * 
     * @param x1 original x cooridinate
     * @param y1 original y coordinate
     * @param x2 new x coordinate
     * @param y2 new y coordinate
     */
    public void updateMapElements(int x1, int y1, int x2, int y2) {
        this.mapElements[x2][y2] = this.mapElements[x1][y1];
        this.mapElements[x1][y1] = null;
    }

    /**
     * method to check if the element is from within the boundary of the dungeon
     * 
     * @param x
     * @param y
     * @return boolean
     */
    public boolean inbound(int x, int y) {
        return (x < getWidth() && y < getHeight());
    }

}
