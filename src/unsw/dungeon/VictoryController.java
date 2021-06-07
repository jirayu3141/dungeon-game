package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Node;

/**
 * Controlller for vicotry and defeat screen (javafx)
 * 
 * @author Jirayu Sirivorawong
 */
public class VictoryController {
    @FXML
    private Button backLvBtn;

    @FXML
    private Button menuBtn;

    @FXML
    public void handleLvBtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("levelSelect.fxml"));
        loader.setController(new LvSelectController());
        Parent lvSelectParent = loader.load();
        Scene lvSelectScene = new Scene(lvSelectParent);
        // get the stage info
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(lvSelectScene);
        window.show();

    }

    @FXML
    public void handleMenuBtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
        loader.setController(new MenuController());
        Parent menuParent = loader.load();
        Scene menuScene = new Scene(menuParent);
        // get the stage info
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(menuScene);
        window.show();
    }
}