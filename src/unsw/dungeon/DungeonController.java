package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.util.Duration;

/**
 * A JavaFX controller for the dungeon.
 * 
 * @author Jirayu Sirivorawong & Yazed Al-Falhi
 *
 */
public class DungeonController {

    @FXML
    private GridPane squares;

    private List<ImageView> initialEntities;

    private Player player;
    private Dungeon dungeon;
    private GameController gameController;
    private Timeline timeline;
    private Timeline secondTimeline;
    private String action;
    private int level;

    public DungeonController(Dungeon dungeon) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.gameController = dungeon.getGameController();
        this.action = "none";
    }

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities, int level) {
        this(dungeon);
        this.initialEntities = new ArrayList<>(initialEntities);
        this.level = level;
        timeline = new Timeline(new KeyFrame(Duration.millis(500), e -> {
            gameController.moveEnemies();
            player.reduceInvincibilityState();
            gameController.checkGameState();
            if (gameController.getGameState().equals("Victory")) {
                timeline.stop();
                secondTimeline.stop();

                try {

                    File myObj = new File("currentLv.txt");
                    Scanner myReader = new Scanner(myObj);
                    String data = myReader.nextLine();
                    int beatenLv = Integer.parseInt(data);
                    myReader.close();

                    if (beatenLv <= this.level) {
                        System.out.println(beatenLv + " : " + this.level);
                        FileWriter myWriter = new FileWriter("currentLv.txt");
                        myWriter.write(Integer.toString(this.level + 1));
                        myWriter.close();
                    }

                } catch (IOException err) {
                    return;
                }

                try {
                    loadVicScreen("victory.fxml");
                } catch (IOException e1) {
                    System.out.println("can't load victory.fxml");
                }

            }
            if (gameController.getGameState().equals("Defeat")) {
                timeline.stop();
                secondTimeline.stop();
                try {
                    loadDefeatScreen("defeat.fxml");
                } catch (IOException e1) {
                    System.out.println("can't load defeat.fxml");
                }
            }

        }));

        secondTimeline = new Timeline(new KeyFrame(Duration.millis(250), e -> {
            gameController.removeWeapons();
            switch (action) {
                case "up":
                    player.moveUp();
                    break;
                case "down":
                    player.moveDown();
                    break;
                case "left":
                    player.moveLeft();
                    break;
                case "right":
                    player.moveRight();
                    break;
                case "space":
                    player.useWeapon();
                    break;
                default:
                    break;
            }
            gameController.setDarkness();
            this.action = "none";
        }));
        // timeline.setCycleCount(Animation.INDEFINITE);

        timeline.setCycleCount(Animation.INDEFINITE);
        secondTimeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        secondTimeline.play();
    }

    private void loadDefeatScreen(String name) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
        loader.setController(new VictoryController());
        Parent defeatParent = loader.load();
        Scene defeatScene = new Scene(defeatParent);
        // get the stage info and show it
        Stage window = (Stage) squares.getScene().getWindow();
        window.setScene(defeatScene);
        window.show();
    }

    private void loadVicScreen(String name) throws IOException {
        // check lv we are up to
        Scanner in = new Scanner(new FileReader("currentLv.txt"));
        StringBuilder sb = new StringBuilder();
        while (in.hasNext()) {
            sb.append(in.next());
        }
        int lvNow = Integer.parseInt(sb.toString());
        in.close();
        // update level
        if (level >= lvNow) {
            String text = Integer.toString(level + 1);
            System.out.println("path is :" + Paths.get("currentLv.txt"));
            Files.write(Paths.get("currentLv.txt"), text.getBytes());
        }

        loadDefeatScreen(name);

    }

    @FXML
    public void initialize() {
        Image ground = new Image((new File("images/dirt_0_new.png")).toURI().toString());

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities) {
            squares.getChildren().add(entity);
        }
    }

    /**
     * specified what to do when a key from a keyboard is pressed
     * 
     * @param event from the keyboard
     */
    @FXML
    public void handleKeyPress(KeyEvent event) throws IOException {
        switch (event.getCode()) {
            case W: case UP:
                action = "up";
                // player.moveUp();
                break;
            case S: case DOWN:
                action = "down";
                // player.moveDown();
                break;
            case A: case LEFT:
                action = "left";
                // player.moveLeft();
                break;
            case D: case RIGHT:
                action = "right";
                // player.moveRight();
                break;
            case SPACE:
                action = "space";
                // player.useWeapon();
                break;
            case P:
                gameController.pause();
                break;
            case ESCAPE:
                gameController.defeat();
                break;
            default:
                break;
        }
    }

    /**
     * any functions that has to do with the time geos in here
     * 
     * @return the state of the game
     */
    public String timedFunctions() {
        gameController.removeWeapons();
        gameController.moveEnemies();
        player.reduceInvincibilityState();
        gameController.checkGameState();
        return gameController.getGameState();
    }
}
