package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuScreen {
    
    private Stage stage;
    private String title;
    private MenuController controller;
    private Scene scene;

    public MenuScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Menu Screen";

 
        // create a controller so it can take control of the application
        controller = new MenuController();
        // grab the visual look of it into loader
        // getClass().getReseource will pass the location of the fxml to the
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
        
        /**
         * Later try the following to load "grade2.fxml", 
         * for 'live' (on key-release) update of grade.
         * FXMLLoader loader = new FXMLLoader(getClass().getResource("grade2.fxml"));

         */
        
        // loader require a controller to be able to control
        loader.setController(controller);

        // Load the scene into the root node 
        Parent root = loader.load();
        scene = new Scene(root, 600, 400);
    }

    public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
}