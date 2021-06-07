/**
 * MAIN APPLICATION FILE. START GAME HERE! :D
 */

package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Setup javafx to start menu sceen
 * 
 */
public class DungeonApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        MenuScreen menuScreen = new MenuScreen(primaryStage);
        menuScreen.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
