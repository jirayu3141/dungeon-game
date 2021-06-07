package unsw.dungeon;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class MenuController {
    @FXML
    private Button playBtn;

    @FXML
    private Button controlBtn;

    @FXML
    private Button exitBtn;

    /**
     * when play button is pressed, bring the game menu to level select screen
     */
    @FXML
    private void handlePlayBtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("levelSelect.fxml"));
        loader.setController(new LvSelectController());
        Parent lvSelectParent = loader.load();
        Scene lvSelectScene = new Scene(lvSelectParent);
        // get the stage info
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(lvSelectScene);
        window.show();

    }

    /**
     * when control button is clicked, bring to control screen
     */
    @FXML
    public void handleControlBtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("controls.fxml"));
        loader.setController(new ControlController());
        Parent lvSelectParent = loader.load();
        Scene lvSelectScene = new Scene(lvSelectParent);
        // get the stage info
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(lvSelectScene);
        window.show();

    }

    /**
     * when exit button is clicked, exit the application
     */
    @FXML
    private void handleExitBtn(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }
}