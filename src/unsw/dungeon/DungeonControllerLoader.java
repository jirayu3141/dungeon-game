package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.File;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * 
 * @author Jirayu Sirivorawong & Yazed Al-Falhi
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities;

    // Images
    private Image playerImage;
    private Image wallImage;
    private Image exitImage;
    private Image treasureImage;
    private Image openDoorImage;
    private Image doorImage;
    private Image keyImage;
    private Image boulderImage;
    private Image floorSwitchImage;
    private Image portalImage;
    private Image regularEnemyImage;
    private Image swordImage;
    private Image invincibilityPotionImage;
    private Image darkness;

    public DungeonControllerLoader(String filename) throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        playerImage = new Image((new File("images/human_new.png")).toURI().toString());
        wallImage = new Image((new File("images/brick_brown_0.png")).toURI().toString());
        exitImage = new Image(new File("images/exit.png").toURI().toString());
        treasureImage = new Image(new File("images/gold_pile.png").toURI().toString());
        doorImage = new Image(new File("images/closed_door.png").toURI().toString());
        openDoorImage = new Image(new File("images/open_door.png").toURI().toString());
        keyImage = new Image(new File("images/key.png").toURI().toString());
        boulderImage = new Image(new File("images/boulder.png").toURI().toString());
        floorSwitchImage = new Image(new File("images/pressure_plate.png").toURI().toString());
        portalImage = new Image(new File("images/portal.png").toURI().toString());
        regularEnemyImage = new Image(new File("images/deep_elf_master_archer.png").toURI().toString());
        swordImage = new Image(new File("images/greatsword_1_new.png").toURI().toString());
        invincibilityPotionImage = new Image(new File("images/brilliant_blue_new.png").toURI().toString());
        darkness = new Image(new File("images/black.png").toURI().toString());

    }

    
    /** 
     * associate player with player image
     * @param player object from the game
     */
    @Override
    public void onLoad(Entity player) {
        ImageView view = new ImageView(playerImage);
        addEntity(player, view);
    }

    
    /** 
     * associate wall object with wall image
     * @param wall object of the game
     */
    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        addEntity(wall, view);
    }

    
    /** 
     * associate exit with exit image
     * @param exit of the dungeon
     */
    @Override
    public void onLoad(Exit exit) {
        ImageView view = new ImageView(exitImage);
        addEntity(exit, view);
    }

    
    /** 
     * associte treasure object with tressure image
     * @param treasure object to be collected in the game
     */
    @Override
    public void onLoad(Treasure treasure) {
        ImageView view = new ImageView(treasureImage);
        addEntity(treasure, view);
    }

    
    /** 
     * associate door object with door image
     * @param door object of the dungeon
     * @param type type of door (ie. opened, close)
     */
    @Override
    public void onLoad(Door door, int type) {
        ImageView view;
        if (type == 0) {
            view = new ImageView(openDoorImage);
        } else {
            view = new ImageView(doorImage);
        }
        addEntity(door, view);
    }

    
    /** 
     * associate key image with key object
     * @param key object
     */
    @Override
    public void onLoad(Key key) {
        ImageView view = new ImageView(keyImage);
        addEntity(key, view);
    }

    
    /** 
     * associate boulder with boulder image
     * @param boulder
     */
    @Override
    public void onLoad(Boulder boulder) {
        ImageView view = new ImageView(boulderImage);
        addEntity(boulder, view);
    }

    
    /** 
     * associate floor switch with floor switch image
     * @param floorSwitch
     */
    @Override
    public void onLoad(FloorSwitch floorSwitch) {
        ImageView view = new ImageView(floorSwitchImage);
        addEntity(floorSwitch, view);
    }

    
    /** 
     * associate portal with portal image
     * @param portal
     */
    @Override
    public void onLoad(Portal portal) {
        ImageView view = new ImageView(portalImage);
        addEntity(portal, view);
    }

    
    /** 
     * associate enemy with enemy iamge
     * @param regularEnemy
     */
    @Override
    public void onLoad(RegularEnemy regularEnemy) {
        ImageView view = new ImageView(regularEnemyImage);
        addEntity(regularEnemy, view);
    }

    
    /** 
     * associate sword with sword image
     * @param sword
     */
    @Override
    public void onLoad(Sword sword) {
        ImageView view = new ImageView(swordImage);
        addEntity(sword, view);
    }

    
    /** 
     * associate invincibility potion with the invinc image
     * @param invincibilityPotion
     */
    @Override
    public void onLoad(InvincibilityPotion invincibilityPotion) {
        ImageView view = new ImageView(invincibilityPotionImage);
        addEntity(invincibilityPotion, view);
    }

    @Override
    public void onLoad(Darkness dark){
        ImageView view = new ImageView(darkness);
        addEntity(dark, view);
    }

    @Override
    public  void goalLoadTresure(Treasure e){
        ImageView view = new ImageView(treasureImage);
        view.setScaleX(0.5);
        view.setScaleY(0.5);
        addEntity(e, view);
    }

    @Override
    public  void goalLoadExit(Exit e){
        ImageView view = new ImageView(exitImage);
        view.setScaleX(0.5);
        view.setScaleY(0.5);
        addEntity(e, view);
    }

    @Override
    public  void goalLoadEnemy(RegularEnemy e){
        ImageView view = new ImageView(regularEnemyImage);
        view.setScaleX(0.5);
        view.setScaleY(0.5);
        addEntity(e, view);
    }

    @Override
    public  void goalLoadBoulders(Boulder e){
        ImageView view = new ImageView(boulderImage);
        view.setScaleX(0.5);
        view.setScaleY(0.5);
        addEntity(e, view);
    }

    
    /** 
     * method for associating entity with image
     * @param entity to be associated with an image
     * @param view image 
     */
    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entities.add(view);
    }

    /**
     * Set a node in a GridPane to have its position track the position of an entity
     * in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the model
     * will automatically be reflected in the view.
     * 
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());

        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() == -1) {
                    node.toBack();
                } else {
                    GridPane.setColumnIndex(node, newValue.intValue());
                    node.toFront();
                }
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() != -1) {
                    GridPane.setRowIndex(node, newValue.intValue());
                }
            }
        });

        entity.o().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                node.setOpacity(newValue.doubleValue());
                node.toFront();
            }
        });
    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * 
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController(int level) throws FileNotFoundException {
        return new DungeonController(load(), entities, level);
    }
}
